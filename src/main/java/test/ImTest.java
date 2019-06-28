package test;

import zhaogang.Login;
import zhaogang.SendMessage;

public class ImTest {
    public static void main(String[] args) {
        String a=Login.Login(2);
        String b= SendMessage.SendMessage(2,1,"11111");
        System.out.println(b);
    }
}
