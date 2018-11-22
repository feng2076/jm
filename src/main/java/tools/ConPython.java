package tools;

import java.io.BufferedReader;
import java.io.IOException;

import org.python.jline.internal.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConPython {
	private static final Logger log = LoggerFactory.getLogger(ConPython.class);

	public static String runpy(String[] args) {
		
		String result = "";
		String rl=null;
		try {
			//args为数值如{ "python", "C:\\Users\\user\\Desktop\\py\\py.py", "88","99"}
			Process process = Runtime.getRuntime().exec(args);
			// process.waitFor();
			BufferedReader in  =new BufferedReader(new InputStreamReader(process.getInputStream()));
			  while ((rl = in.readLine()) != null) {
				  if("".equals(result)){
					  result=rl;
				  }else{
					  result=rl+";"+result; 
				  }
	            }
			in.close();
			// process.waitFor();
		} catch (IOException e) {
			log.info("调用python脚本并读取结果时出错：" + e.getMessage());
		}
		return result;
	}
}
