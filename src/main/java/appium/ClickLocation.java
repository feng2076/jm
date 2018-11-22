package appium;

import io.appium.java_client.android.AndroidDriver;

public class ClickLocation {
    /* 
     * @Description:  
     * @Param:  
     * @return:  
     * @Author: xufeng 
     * @Date: 2018/11/5 
     */
    public static void keyclick(AndroidDriver driver,String phonenum,String appname){
          Driver.DriverClick(driver,"com.kdlc."+appname+":id/et_phone_number");
          //driver.findElementById("com.kdlc.xyjk:id/et_phone_number").click();
          WaitTime.wait(1000);
          char[] num=phonenum.toCharArray();
          for(int i=0;i<num.length;i++){
             // driver.findElementById("com.kdlc.xyjk:id/btGlkNum"+num[i]+"").click();
              Driver.DriverClick(driver,"com.kdlc."+appname+":id/btGlkNum"+num[i]+"");
          }
    }
}
