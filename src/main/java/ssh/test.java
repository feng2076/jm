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

                String[] ech={"root:123456:jump.test.ykwlkj.com:22","root:123456:172.19.98.53:22"};
//
//       String a= Database.database(9106,"D:\\\\xshell\\\\id_rsa_2048-private","select id from tb_loan_person where phone='15995106013'",
//                "com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/xyjk_app","user_id",ech);
       // GetSshReady.get("D:\\xshell\\jsqb","123456","cd /data/www/jsyq;php yii-test channel-order/handle-event;exit",ech);
        GetSshReady g=new GetSshReady();
        String b=g.get("D:\\xshell\\jsqb","123456","cd /data/www/jsyq;ls;php yii-test dai-chao/event-handle",ech);
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
//        int seconds = (int) (System.currentTimeMillis() / 1000);
        System.out.println(b);
    }
}
