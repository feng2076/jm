package jmeter;

import javax.swing.JOptionPane;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.threads.JMeterContext;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.JMeterVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ssh.Getssh;
/* 
 * @Description:  
 * @Param:  
 * @return:  
 * @Author: xufeng 
 * @Date: 2018/12/5 
 */
public class ConSsh extends AbstractJavaSamplerClient {
	
	
    private SampleResult results;
    private static final Logger log = LoggerFactory.getLogger(ConSsh.class);
    private String filepath;
    private String command;
    private String password;
    private String sshev;


  
    public void setupTest(JavaSamplerContext jsc) {
        
    }
    
    /**
     * 设置传入参数
     * 可以设置多个，已设置的参数会显示到Jmeter参数列表中
     */
    public Arguments getDefaultParameters() {
        Arguments params = new Arguments();
        params.addArgument("filepath", "私钥存放的路径(环境变量已配置并同名可不写)");
        params.addArgument("command", "SSH执行脚本，如：cd /code/dev-0-app-init-jn6d56;php yii overdue-calculate/test-calculate;exit(环境变量已配置并同名可不写)");
        params.addArgument("password", "SSH连接密码(环境变量已配置并同名可不写)");
        params.addArgument("sshev", "SSH服务器配置，如：xufeng:123456:139.224.22.30:1022;test:123456:jsde-dev-app_cron_1:22(环境变量已配置并同名可不写) ");

        return params;
    }
    /* 
     * @Description:
     * @Param:  
     * @return:  
     * @Author: xufeng 
     * @Date: 2018/11/5 
     */
    @Override
    public SampleResult runTest(JavaSamplerContext jsc) {
    	filepath = jsc.getParameter("filepath");
        command = jsc.getParameter("command");
        password = jsc.getParameter("password");
        sshev = jsc.getParameter("sshev");
    	results = new SampleResult();
    	results.sampleStart(); 
    	JMeterContext jmcx = JMeterContextService.getContext();
  	    JMeterVariables vars = jmcx.getVariables();
		if (filepath.contains("可不写")) {
			filepath = vars.get("filepath");
		}
		if (password.contains("可不写")) {
			password = vars.get("password");
		}
		if (command.contains("可不写")) {
			command = vars.get("command");
		}
		if (sshev.contains("可不写")) {
			sshev = vars.get("sshev");
		}
    	try {
    		String[] ech=sshev.split(";");
        	int num=ech.length;
        	String[] sshhost=new String[num];
        	for(int i=0;i<num;i++){
        		sshhost[i]=ech[i];
        		log.info("host配置为："+ech[i]);
        	}
        	if(command.equals("")||command.contains("可不写")){
        		command = (String) JOptionPane.showInputDialog(null, "没有配置脚本，请输入执行脚本", "输入脚本", JOptionPane.PLAIN_MESSAGE, null, null,"请输入执行脚本");
			}
        	String text="command为空，未执行";
        	log.info("command："+command);
        	if(!command.equals("")){
				Getssh g=new Getssh();
        		text=g.get(filepath, password, command, sshhost);
        	}
        	log.info("执行脚本返回的信息为："+text);
        	results.setSuccessful(true);  
		} catch (Exception e) {
			log.info("脚本出错");
			results.setSuccessful(true);  
		}
         try {  
             Thread.sleep(1000);  
         } catch (InterruptedException e) {  
             // TODO Auto-generated catch block  
             e.printStackTrace();  
         }  
         results.sampleEnd();  
		return results;
	}

    public void teardownTest(JavaSamplerContext arg0) {
    }

}
