package jmeter;

import java.io.FileNotFoundException;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BiduiJson extends AbstractJavaSamplerClient{

    private SampleResult results;//在结果树中返回的true/false
    private static final Logger log = LoggerFactory.getLogger(BiduiJson.class);
    private String fileA;
    private String fileB;
/* 
 * @Description:  
 * @Param:  
 * @return:  
 * @Author: xufeng 
 * @Date: 2018/11/5 
 */
    public void setupTest(JavaSamplerContext jsc) {
    }

/* 
 * @Description:  
 * @Param:  
 * @return:  
 * @Author: xufeng 
 * @Date: 2018/11/5 
 */
    public Arguments getDefaultParameters() {
        Arguments params = new Arguments();
        params.addArgument("fileA", "json文件A的地址，被比对的对象,输出在B中不存在的字段");
        params.addArgument("fileB", "json文件B的地址，比对的对象");
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
        results = new SampleResult();
        results.sampleStart();
        fileA = jsc.getParameter("fileA");
        fileB = jsc.getParameter("fileB");
        try {
            String result=tools.JsonCheck.json(fileA, fileB);
            log.info(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        };
        results.setSuccessful(true);
        results.sampleEnd();
        return results;
    }

    public void teardownTest(JavaSamplerContext arg0) {
    }
}
