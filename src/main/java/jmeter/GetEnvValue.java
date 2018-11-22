package jmeter;

import org.apache.jmeter.threads.JMeterContext;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.JMeterVariables;

public class GetEnvValue  {
/* 
 * @Description:  
 * @Param:  
 * @return:  
 * @Author: xufeng 
 * @Date: 2018/11/5 
 */
    public static String getvalue(){
        JMeterContext jmcx = JMeterContextService.getContext();
        JMeterVariables vars = jmcx.getVariables();
        String isrun=vars.get("isrun");
        return isrun;
    }

}
