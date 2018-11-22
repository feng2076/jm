package ssh;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args) {
       // String dbev="super_test:guI8jFbIMB2OWKqQ:rm-uf6nk81i4p1bk1whn.mysql.rds.aliyuncs.com:3306";
//        String[] ech={"root:123456a:service.dev.jisuqianbao.com:2201","super_test:guI8jFbIMB2OWKqQ:rm-uf6nk81i4p1bk1whn.mysql.rds.aliyuncs.com:3306"};
//
//       String a= Database.database(9106,"D:\\\\xshell\\\\id_rsa_2048-private","select id from tb_loan_person where phone='15995106013'",
//                "com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/xyjk_app","user_id",ech);
//        System.out.println("编号是："+a);

//                String[] ech={"root:123456a:service.dev.jisuqianbao.com:2201","root:123456a:172.20.32.4:22"};
//
//       String a= Database.database(9106,"D:\\\\xshell\\\\id_rsa_2048-private","select id from tb_loan_person where phone='15995106013'",
//                "com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/xyjk_app","user_id",ech);
//        String b=Getssh.get("D:\\\\\\\\xshell\\\\\\\\id_rsa_2048-private","123456a","cd /code/xyjk/app:exit",ech);
//        System.out.println("信息"+b);
//        String res;
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = null;
//        try {
//            date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        long ts = date.getTime();
//        res = String.valueOf(ts);
//        int a=Integer.parseInt(res)/1000;
        int seconds = (int) (System.currentTimeMillis() / 1000);
        System.out.println(seconds);
    }
}
