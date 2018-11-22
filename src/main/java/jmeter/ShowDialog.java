package jmeter;

import javax.swing.JOptionPane;

import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.threads.JMeterContext;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.JMeterVariables;

public class ShowDialog extends AbstractJavaSamplerClient{

	private SampleResult results;
	/* 
	 * @Description:  
	 * @Param:  
	 * @return:  
	 * @Author: xufeng 
	 * @Date: 2018/11/5 
	 */
	@Override
	public SampleResult runTest(JavaSamplerContext arg0) {
		 results = new SampleResult();
		 results.sampleStart();
		 JMeterContext jmcx = JMeterContextService.getContext();
 	     JMeterVariables vars = jmcx.getVariables();
 	     String goon="未选择";
 	     String type=vars.get("type");
 	     if(type.equals("全模块")){
 	    	vars.put("goon", "0");
 	     }else{
 	    	Object[] options = { "是", "取消" }; 
 			 Object selectValue=JOptionPane.showOptionDialog(null, "准备执行<"+type+">,点击以继续", "警告", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
 			 if(selectValue==null){
 				  vars.put("goon",goon);
 			  }else{
 				  goon=selectValue.toString();
 				  vars.put("goon", goon);
 			  }
 	     }
		 results.setSuccessful(true);
	     results.sampleEnd();  
		 return results;
	}
}
