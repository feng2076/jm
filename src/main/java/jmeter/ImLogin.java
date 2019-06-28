package jmeter;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.threads.JMeterContext;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.JMeterVariables;
import org.python.jline.internal.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zhaogang.Login;

public class ImLogin extends AbstractJavaSamplerClient {
    private SampleResult results;//在结果树中返回的true/false
    private static final Logger log = LoggerFactory.getLogger(ImLogin.class);
    private String userId;
    public void setupTest(JavaSamplerContext jsc) {
    }

    /**
     * 设置传入参数 可以设置多个，已设置的参数会显示到Jmeter参数列表中
     */
    public Arguments getDefaultParameters() {
        Arguments params = new Arguments();
        params.addArgument("userId", "用户ID");
        return params;
    }
    @Override
    public SampleResult runTest(JavaSamplerContext jsc) {
        userId = jsc.getParameter("userId");
        Log.info(userId);
        results = new SampleResult();
        results.sampleStart();
        JMeterContext jmcx = JMeterContextService.getContext();
        JMeterVariables vars = jmcx.getVariables();
        String loginToken=Login.Login(Integer.parseInt(userId));
        vars.put("loginToken",loginToken);
        log.info("当前登录加密信息为："+loginToken);
        results.setSuccessful(true);
        results.sampleEnd();
        return results;
    }

    public void teardownTest(JavaSamplerContext arg0) {
    }
}

