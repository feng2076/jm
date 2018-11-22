package jmeter;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.threads.JMeterContext;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.JMeterVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ToDate extends AbstractJavaSamplerClient {
    private SampleResult results;//在结果树中返回的true/false
    private static final Logger log = LoggerFactory.getLogger(ToDate.class);
    private String yuqitianshu;

    public void setupTest(JavaSamplerContext jsc) {
    }

    /**
     * 设置传入参数 可以设置多个，已设置的参数会显示到Jmeter参数列表中
     */
    public Arguments getDefaultParameters() {
        Arguments params = new Arguments();
        params.addArgument("yuqitianshu", "逾期天数");
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
        yuqitianshu = jsc.getParameter("yuqitianshu");
        int currentTime=(int) (System.currentTimeMillis() / 1000);
        int yuqi=Integer.parseInt(yuqitianshu)*86400;
        String yuqitime=String.valueOf(currentTime-yuqi);
        results = new SampleResult();
        results.sampleStart();
        JMeterContext jmcx = JMeterContextService.getContext();
        JMeterVariables vars = jmcx.getVariables();
        log.info("当前时间戳为："+yuqitime);
        vars.put("plan_fee_time", yuqitime);
        results.setSuccessful(true);
        results.sampleEnd();
        return results;
    }

    public void teardownTest(JavaSamplerContext arg0) {
    }
}
