package jmeter;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.threads.JMeterContext;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.JMeterVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public  class ShowInfoDialog extends AbstractJavaSamplerClient{


    private SampleResult results;
    private static final Logger log = LoggerFactory.getLogger(ShowInfoDialog.class);
    private String info;
    private String status;
    private String userid;

  
    public void setupTest(JavaSamplerContext jsc) {
    }
    
    /**
     * 设置传入参数
     * 可以设置多个，已设置的参数会显示到Jmeter参数列表中
     */
    public Arguments getDefaultParameters() {
        Arguments params = new Arguments();
        params.addArgument("info", "提示的信息(默认输出订单状态)");
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
    	info = jsc.getParameter("info", "");
    	results = new SampleResult();
    	results.sampleStart();
    	JMeterContext jmcx = JMeterContextService.getContext();
 	    JMeterVariables vars = jmcx.getVariables();
 	    String type=vars.get("type");
 	    if(info.contains("提示的信息")||info.contains("订单的最新状态")){
 	    	status=vars.get("status");
 	    	userid=vars.get("user_id");
 	    	System.out.println("状态为"+status+"用户id"+userid);
 	    	if(status.equals("0")){
 	    		info="订单的最新状态是--待初审--";
 	    	}else if(status.equals("8")||status.equals("2")){
 	    		info="订单的最新状态是--待打款--";
 	    	}else if(status.equals("7")){
				info="订单的最新状态是--待复审--";
			}else if(status.equals("3")){
 	    		info="订单的最新状态是--待还款--";
 	    	}else if(status.equals("6")){
 	    		info="订单的最新状态是--已还款--";
 	    	}else if(status.equals("5")){
 	    		info="订单的最新状态是--部分还款--";
 	    	}else if(status.equals("-1")){
 	    		info="订单的最新状态是--已逾期--";
 	    	}else if(status.equals("-3")){
 	    		info="订单的最新状态是--已驳回--";
 	    	}else if(status.equals("空值")&&!userid.equals("空值")){
 	    		info="订单的最新状态是--暂无相关订单--";
 	    	}else if(status.equals("空值")&&userid.equals("空值")){
 	    		info="订单的最新状态是--用户还未注册，请注册--";
 	    	}else{
 	    		info="订单的最新状态是--未确定--";
 	    	}
 	    }
 	    System.out.println("info为："+info);
 	    if(type.equals("全模块")){
 	    	JOptionPane jp=new JOptionPane(info, JOptionPane.INFORMATION_MESSAGE); 
 	    	 final JDialog dialog = jp.createDialog("自动关闭");
 	    	 Timer timer = new Timer();  
 	        // 0.8秒 后执行该任务  
 	        timer.schedule(new TimerTask() {  
 	            public void run() {  
 	            	dialog.setVisible(false);  
 	            	dialog.dispose();  
 	            }  
 	        }, 800); 
 	        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);  
 	        dialog.setAlwaysOnTop(true);  
 	        dialog.setModal(false);  
 	        dialog.setVisible(true);
	    }else{
	    	JOptionPane.showMessageDialog(null, info);
	    }
  	    results.setSuccessful(true);
        results.sampleEnd();  
		return results;
	}
    public void teardownTest(JavaSamplerContext arg0) {
    }
}
