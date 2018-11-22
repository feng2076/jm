package appium;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//运行adb命令
public class RunAdb {
    /* 
     * @Description:  
     * @Param:  
     * @return:  
     * @Author: xufeng 
     * @Date: 2018/11/5 
     */
    public static void run(String adbcommand){
        BufferedReader br = null;
        try {
            Process p= Runtime.getRuntime().exec(adbcommand);
            WaitTime.wait(2000);
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            StringBuilder sb=new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line+"\n");
            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
