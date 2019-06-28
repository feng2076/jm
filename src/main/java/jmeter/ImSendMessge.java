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
import zhaogang.SendMessage;

public class ImSendMessge extends AbstractJavaSamplerClient {
    private SampleResult results;//在结果树中返回的true/false
    private static final Logger log = LoggerFactory.getLogger(ImSendMessge.class);
    private String fromUserId;
    private String toUserId;
    private String text;

    public void setupTest(JavaSamplerContext jsc) {
    }

    /**
     * 设置传入参数 可以设置多个，已设置的参数会显示到Jmeter参数列表中
     */
    public Arguments getDefaultParameters() {
        Arguments params = new Arguments();
        params.addArgument("fromUserId", "来源用户ID");
        params.addArgument("toUserId", "目标用户ID");
        params.addArgument("text", "文本信息");

        return params;
    }
    @Override
    public SampleResult runTest(JavaSamplerContext jsc) {
        fromUserId = jsc.getParameter("fromUserId");
        toUserId = jsc.getParameter("toUserId");
        text = jsc.getParameter("text");
        Log.info("fromUserId:"+fromUserId);
        Log.info("toUserId:"+toUserId);
        Log.info("text:"+text);
        results = new SampleResult();
        results.sampleStart();
        JMeterContext jmcx = JMeterContextService.getContext();
        JMeterVariables vars = jmcx.getVariables();
        String MessageToken= SendMessage.SendMessage(Integer.parseInt(fromUserId),Integer.parseInt(toUserId),text);
        vars.put("MessageToken",MessageToken);
        log.info("当前消息加密信息为："+MessageToken);
        results.setSuccessful(true);
        results.sampleEnd();
        return results;
    }

    public void teardownTest(JavaSamplerContext arg0) {
    }
}
