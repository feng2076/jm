package jmeter;

import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import javax.servlet.http.Cookie;


public class CleanCookie extends AbstractJavaSamplerClient {

    private SampleResult results;
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
        //清空Cookie操作

        results.setSuccessful(true);
        results.sampleEnd();
        return results;
    }
}
