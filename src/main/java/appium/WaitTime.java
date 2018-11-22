package appium;

public class WaitTime {
    /* 
     * @Description:  
     * @Param:  
     * @return:  
     * @Author: xufeng 
     * @Date: 2018/11/5 
     */
    public static void wait(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
