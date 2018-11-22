package jmeter;

import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.threads.JMeterContext;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.JMeterVariables;
import tools.JinDuTiao;

public class ShowJinDu extends AbstractJavaSamplerClient {

    private SampleResult results;//在结果树中返回的true/false
    /* 
     * @Description:  
     * @Param:  
     * @return:  
     * @Author: xufeng 
     * @Date: 2018/11/5 
     */
    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        results = new SampleResult();
        results.sampleStart();
        JMeterContext jmcx = JMeterContextService.getContext();
        JMeterVariables vars = jmcx.getVariables();
        String isrun=vars.get("isrun");
        JinDuTiao jdt = new JinDuTiao(100);//创建进度条
        new Thread(jdt).start();//开启线程，刷新进度条
        int size = 0;
        while(isrun.equals("1")){//循环读取
            isrun=GetEnvValue.getvalue();
            System.out.println(isrun);
            jdt.updateProgress(size);//写完一次，更新进度条
            try {
                wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            size++;
        }
        jdt.finish();//
        results.setSuccessful(true);
        results.sampleEnd();
        return results;
    }
}
