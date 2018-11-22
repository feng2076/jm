package jmeter;

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

public class ShowSelectDialog extends AbstractJavaSamplerClient {

	private SampleResult results;//在结果树中返回的true/false
	private static final Logger log = LoggerFactory.getLogger(ShowSelectDialog.class);
	private String options;

	public void setupTest(JavaSamplerContext jsc) {
	}

	/**
	 * 设置传入参数 可以设置多个，已设置的参数会显示到Jmeter参数列表中
	 */
	public Arguments getDefaultParameters() {
		Arguments params = new Arguments();
		params.addArgument("options", "选择框中的选项，已“;”隔开");
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
		options = jsc.getParameter("options");
		results = new SampleResult();
		results.sampleStart();
		JMeterContext jmcx = JMeterContextService.getContext();
		JMeterVariables vars = jmcx.getVariables();
		String[] option=options.split(";");
		String type = "未选择";
		Object[] possibleValues=option;
		Object selectedValue = JOptionPane.showInputDialog(null, "请选择执行脚本",
				"请选择选择", JOptionPane.INFORMATION_MESSAGE, null, possibleValues,
				possibleValues[0]);
		if (selectedValue == null) {
			vars.put("type", type);
		} else {
			type = selectedValue.toString();
			vars.put("type", type);
		}
		results.setSuccessful(true);
		results.sampleEnd();
		return results;
	}

	public void teardownTest(JavaSamplerContext arg0) {
	}
}
