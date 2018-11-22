package ssh;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Newdatabase {

	public static String database(int lport, String filepath, String sql,
			String dbclass,String dburl,String evalue, String[] sshev) {
		// sshev={"test:123456:dev.kdqugou.com:1022","jsqb_user:jsqb@ArEe4LJq:mysql_dev:3306"};
		// lport = 3306;//本地端口
		// rhost = "mysql_dev";//远程MySQL服务器
		// rport = 3306;//远程MySQL服务端口
		// filepath="D:\\xshell\\jsqb";//私钥存放的路径
		// user = "test";//SSH连接用户名
		// password = "123456";//SSH连接密码
		// host = "dev.kdqugou.com";//SSH服务器
		// port = 1022;//SSH访问端口
		// sql="";
		// int num=1;
		// dburl="jdbc:mysql://localhost:3306/jsqb";
		int len = sshev.length;
		String result = "空值";
		Session session = null;
		String[] ssh = null;
		String[] db = null;
		if (len > 1) {
			ssh = sshev[0].split(":");
			db = sshev[1].split(":");
		} else if (len == 1) {
			db = sshev[0].split(":");
			dburl="jdbc:mysql://"+db[2]+":"+db[3];
		} else{
			return	result = "连接信息错误，请检查";
		}
		if (len > 1) {
			try {
				JSch jsch = new JSch();
				jsch.addIdentity(filepath, ssh[1]);
				session = jsch.getSession(ssh[0], ssh[2],Integer.parseInt(ssh[3]));
				session.setPassword(ssh[1]);
				session.setConfig("StrictHostKeyChecking", "no");
				session.connect();
				System.out.println(session.getServerVersion());// 这里打印SSH服务器版本信息
				int assinged_port = session.setPortForwardingL(lport, db[2],Integer.parseInt(db[3]));
				System.out.println("localhost:" + assinged_port + " -> "+ db[2] + ":" + Integer.parseInt(db[3]));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		try {
			Class.forName(dbclass);
			conn = DriverManager.getConnection(dburl, db[0], db[1]);
			st = conn.createStatement();
			if (sql.contains("select")) {
				rs = st.executeQuery(sql);
				while (rs.next()) {
					result = rs.getString(1);
					break;
				}
				rs.close();
			} else {
				int num = st.executeUpdate(sql);
				if (num == 1) {
					result = "执行成功";
				} else {
					result = "执行失败参数："+num;
				}
			}
			st.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			result = "语句执行错误，请检查sql语句，或者配置";
		}
		if (len > 1) {
			try {
				session.disconnect();
			} catch (Exception e) {
				result = "数据库ssh关闭异常";
			}
		}
		return result;
	}

}
