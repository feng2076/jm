package appium;


import org.testng.annotations.Test;
@Test
public class Xyjk {

	public void UseAll() {
		StartApp xyjk=new StartApp();
		String branch="xyjk";
		xyjk.OpenApp(branch);//打开app
		RunAdb.run("adb shell monkey -p com.kdlc.xyjk 10000");

  }
}
