package ssh;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import javax.swing.*;

public class Getssh {
	/* 
	 * @Description:  
	 * @Param:  
	 * @return:  
	 * @Author: xufeng 
	 * @Date: 2018/11/12 
	 */
	public static String get(String filepath,String password,String command,String[] Serverinformation) {
//		String user = "xufeng";
//		password = "123456";
//		String host = "139.224.22.30";
//		int port = 1022;
		String result = null;
		try {
			//"xufeng:123456:139.224.22.30:1022"
			JSch jsch = new JSch();
			jsch.addIdentity(filepath, password);
			int num=Serverinformation.length;
			if(num<1){
				return "远程配置错误，请检查";
			}
			String[] main=Serverinformation[0].split(":");
			String[] other=null;
			Session[] sessions = new Session[num];
			Session session = null;
			//root:123456a:service.dev.jisuqianbao.com:2201;
			sessions[0]=session= jsch.getSession(main[0], main[2], Integer.parseInt(main[3]));//先连跳板机
			session.setPassword(main[1]);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();
			System.out.println(session.getServerVersion());// 这里打印SSH服务器版本信息
			for(int i=1;i<num;i++){
				other=Serverinformation[i].split(":");
				//root:123456a:xyjk-stage_app_1:22
				//此处lport为0表示系统随机分配端口
				int assinged_port = session.setPortForwardingL(0,other[2], Integer.parseInt(other[3]));//再把本机和远程机端口映射
			    System.out.println("portforwarding: "+"localhost:"+assinged_port+" -> "+other[2]+":"+1022);
			    sessions[i]=session =jsch.getSession(other[0], "127.0.0.1", assinged_port);
			    session.setPassword(other[1]);
			    session.setHostKeyAlias(other[2]);
			    session.setConfig("StrictHostKeyChecking", "no");
				session.connect();
			}
			ChannelShell channel = (ChannelShell) session.openChannel("shell");
			channel.connect();
			//连接成功后进行流处理
			Thread.sleep(3000);
			InputStream inputStream = channel.getInputStream();
			OutputStream outputStream = channel.getOutputStream();// ssh
			String[] com = command.split(";");
			int a = com.length;
			for (int i = 0; i < a; i++) {
				String cmd = com[i]+" \n\r";
				System.out.println(cmd);
				outputStream.write(cmd.getBytes());
			}
			outputStream.flush();
			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
			String msg = null;
			String overmsg = null;
			long beginTime = System.currentTimeMillis();
			long overTime = 10;  //10秒进入人工选择，点击是继续进行，点击取消跳出循环
            long nowTime;
            long dataTime;
			try {
				while ((msg = in.readLine()) != null) {
					nowTime=System.currentTimeMillis();
					dataTime=(nowTime - beginTime)/1000;
					System.out.println("已运行："+dataTime+"秒--info:"+msg);
					if(result==null){
						result=msg;
					}else{
						result = result + "\n" + msg;
					}
					if(msg.equals(overmsg)&&!msg.equals("")){
						break;
					}
					if(dataTime>overTime){
						Object[] options = { "是", "取消" };
						Object selectValue= JOptionPane.showOptionDialog(null, "运行已经超过<"+dataTime+"S>,点击以继续", "警告", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
						System.out.println(selectValue);
						if(selectValue.equals(0)){
							overTime=overTime+5+(System.currentTimeMillis()-nowTime)/1000;
							System.out.println("-------------------------------------当前超时时间变更为"+overTime+"秒");
						}else{
							break;
						}
					}
					overmsg=msg;
				}
			}catch (Exception e){
				e.printStackTrace();
			}
			channel.disconnect();
			inputStream.close();
			outputStream.close();
			in.close();
			for (int i = sessions.length - 1; i >= 0; i--) {
				sessions[i].disconnect();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "ssh连接失败";
		}
		return result;
	}
}
