package appium;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

//创建一个driver
public class StartApp {
    AndroidDriver driver = null;
/* 
 * @Description:  
 * @Param:  
 * @return:  
 * @Author: xufeng 
 * @Date: 2018/11/5 
 */
    //打开app
    public AndroidDriver OpenApp(String appname) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("unicodeKeyboard", true);//使用 Unicode 输入法
        capabilities.setCapability("resetKeyboard", true);  //重置输入法到原有状态
        capabilities.setCapability("platformVersion", "7.1");//系统版本
        //包名
        capabilities.setCapability("appPackage", "com.kdlc." + appname);
        capabilities.setCapability("appActivity", "com.kdlc.mcc.component.SelectURLActivity");
        try {
            driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        SetBranch(appname);
        return driver;
    }
/* 
 * @Description:  
 * @Param:  
 * @return:  
 * @Author: xufeng 
 * @Date: 2018/11/5 
 */
    //设置分支页面
    public void SetBranch(String appname) {
        try {
            boolean isdisplay = driver.findElementById("com.kdlc." + appname + ":id/et_git").isDisplayed();
            if (isdisplay) {
                //driver.findElementById("com.kdlc."+appname+":id/et_git").clear();
                //这边一定要注意不同的手机要打开模拟点击权限
                Driver.DriverSetValue(driver, "com.kdlc." + appname + ":id/et_git", "xyjk-app-shen-h5-credit.dev.");
                Driver.DriverClick(driver, "com.kdlc." + appname + ":id/tv_git");
            } else {
                System.out.println("元素不存在");
            }
        } catch (Exception e) {
            driver.quit();
            e.printStackTrace();
        }
        SkipBanner(appname);
    }
/* 
 * @Description:  
 * @Param:  
 * @return:  
 * @Author: xufeng 
 * @Date: 2018/11/5 
 */
    //处理banner页
    public void SkipBanner(String appname) {
        //app第一次打开会有banner图，后面再次打开只有app打开界面，有所区别
        if (CheckElement.isAlive("android.widget.ImageView", driver)) {
            List<WebElement> items = driver.findElements(By.className("android.widget.ImageView"));
            int le = items.size();
            System.out.println(le);
            for (int i = 0; i < le; i++) {
                Swipe.Swipe(driver, 3);
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            login(appname);
        } else {
            login(appname);
        }
    }
/* 
 * @Description:  
 * @Param:  
 * @return:  
 * @Author: xufeng 
 * @Date: 2018/11/5 
 */
    //登陆账号
    public void login(String appname) {
        WaitTime.wait(5000);//主页面要等待3秒我们这边设置5秒
        Driver.DriverClick(driver, "com.kdlc." + appname + ":id/rb_account");
        //登陆时可能有2种可能，一种是登录之前登陆过的账号
        if (Driver.DriverIsDisplayed(driver, "com.kdlc." + appname + ":id/tv_title_more_item")) {
            System.out.println("已登录状态");
        } else {
            if (Driver.DriverIsDisplayed(driver, "com.kdlc." + appname + ":id/tv_user_name")) {
                Driver.DriverSetValue(driver, "com.kdlc." + appname + ":id/et_password", "123456");
                Driver.DriverClick(driver, "com.kdlc." + appname + ":id/tv_user_name");
                Driver.DriverClick(driver, "com.kdlc." + appname + ":id/tv_login");
            } else {
                try {
                    ClickLocation.keyclick(driver, "15995106013", appname);//此处因为是app自带键盘，不能使用赋值的功能，单独写一个
                    Driver.DriverClick(driver, "com.kdlc." + appname + ":id/tv_next");
                    Driver.DriverSetValue(driver, "com.kdlc." + appname + ":id/et_password", "123456");
                    Driver.DriverClick(driver, "com.kdlc." + appname + ":id/tv_user_name");
                    Driver.DriverClick(driver, "com.kdlc." + appname + ":id/tv_login");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
    /* 
     * @Description:
     * @Param:  
     * @return:  
     * @Author: xufeng 
     * @Date: 2018/11/5 
     */
    public void execute(String appname){
         WebElement web=Driver.DriverFindElement(driver,appname);

    }
}
