package jmeter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URLEncoder;

import org.apache.jmeter.processor.PostProcessor;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.testelement.AbstractScopedTestElement;
import org.apache.jmeter.threads.JMeterContext;
import org.apache.jmeter.threads.JMeterVariables;
import org.slf4j.LoggerFactory;
import tools.AuthService;
import tools.OCR.OCR;


public class VodeExtractor extends AbstractScopedTestElement implements PostProcessor, Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(AbstractScopedTestElement.class);
    ;

    @Override
    public void process() {

        // TODO Auto-generated method stub
        JMeterContext context = getThreadContext();
        SampleResult previousResult = context.getPreviousResult();
        if (previousResult == null) {
            return;
        }
        log.debug("VcodeExtractor processing result");
        String status = previousResult.getResponseCode();
        int id = context.getThreadNum();
//        String imageName = id + ".jpg";
        String path = "D://" + id + ".png";
        System.out.println("path:"+path);
        if (status.equals("200")) {
            byte[] buffer = previousResult.getResponseData();
            //"data:image/png;base64,"+
            String picture=PictureBase64.getBase64(buffer);
            FileOutputStream out = null;
            File file = null;
            try {
                file = new File(path);
                out = new FileOutputStream(file);
                out.write(buffer);
                out.flush();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            try {
//              String vcode = new OCR().recognizeText(file, "png");
//              vcode = vcode.replace(" ", "").trim();
                JMeterVariables var = context.getVariables();
                String access_token = AuthService.getAuth();
                log.info("picture:" + picture);
                picture = URLEncoder.encode(picture, "UTF-8");
                var.put("access_token", access_token);
                var.put("picture", picture);//
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}