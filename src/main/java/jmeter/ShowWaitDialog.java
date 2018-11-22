package jmeter;

import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.threads.JMeterContext;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.JMeterVariables;

import javax.swing.*;

public class ShowWaitDialog extends AbstractJavaSamplerClient {

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
        JMeterContext jmcx= JMeterContextService.getContext();
        JMeterVariables vars= jmcx.getVariables();
        String isrun=vars.get("isrun");
        JOptionPane jp=new JOptionPane("处理中。。请稍后", JOptionPane.INFORMATION_MESSAGE);
        final JDialog dialog = jp.createDialog("处理中");
        while (isrun.equals("-1")){
            jmcx= JMeterContextService.getContext();
            vars= jmcx.getVariables();
            isrun=vars.get("isrun");
            if(isrun.equals("0")){
                System.out.println("当前状态为："+isrun);
                dialog.setVisible(false);
                dialog.dispose();
                System.out.println("执行窗口关闭");
            }
        }
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setAlwaysOnTop(true);
        dialog.setModal(false);
        dialog.setVisible(true);
        results.setSuccessful(true);
        results.sampleEnd();
        return results;
    }
}
