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

import javax.swing.*;
/* 
 * @Description:  
 * @Param:  
 * @return:  
 * @Author: xufeng 
 * @Date: 2018/12/5 
 */
public class ToMd5 extends AbstractJavaSamplerClient {
    private SampleResult results;//在结果树中返回的true/false
    private static final Logger log = LoggerFactory.getLogger(ToMd5.class);
    private String esign;

    public void setupTest(JavaSamplerContext jsc) {
    }

    /**
     * 设置传入参数 可以设置多个，已设置的参数会显示到Jmeter参数列表中
     */
    public Arguments getDefaultParameters() {
        Arguments params = new Arguments();
        params.addArgument("esign", "被转换的字符串");
        return params;
    }

    @Override
    public SampleResult runTest(JavaSamplerContext jsc) {
        esign = jsc.getParameter("esign");
        results = new SampleResult();
        results.sampleStart();
        JMeterContext jmcx = JMeterContextService.getContext();
        JMeterVariables vars = jmcx.getVariables();
        log.info("MD5加密前的为："+esign);
        String sign=tools.Codes.getMd5(esign);
        vars.put("sign",sign);
        log.info("MD5加密后的为："+sign);
        results.setSuccessful(true);
        results.sampleEnd();
        return results;
    }

    public void teardownTest(JavaSamplerContext arg0) {
    }
}
