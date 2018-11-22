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
import ssh.Database;

import javax.swing.*;


public class NewConDatebase extends AbstractJavaSamplerClient{
	
	    private SampleResult results;
	    private static final Logger log = LoggerFactory.getLogger(NewConDatebase.class);
	    private String lport;
	    private String filepath;
	    private String sql;
	    private String dbclass;
	    private String dburl;
	    private String dbev;
	    private String evalue;
	    String text="执行失败";
	  
	    /**
	     * 设置传入参数
	     * 可以设置多个，已设置的参数会显示到Jmeter参数列表中
	     */
	    public Arguments getDefaultParameters() {
	        Arguments params = new Arguments();
	        params.addArgument("sql", "sql语句(必写)");
	        params.addArgument("evalue", "环境参数名称(查询语句必写，把查询的值传到jmeter环境变量中)");
	        params.addArgument("lport","本地端口(环境变量已配置并同名可不写)");
	        params.addArgument("filepath", "私钥存放的路径(环境变量已配置并同名可不写)");
	        params.addArgument("dbclass", "数据库驱动，如：com.mysql.jdbc.Driver(环境变量已配置并同名可不写)");
	        params.addArgument("dburl", "数据库连接，如：url：jdbc:mysql://localhost:3306/jsde(环境变量已配置并同名可不写)");
	        params.addArgument("dbev", "连接数据信息，如：xufeng:123456:139.224.22.30:1022;jsde_user:jsqb@Arse5LJq:jsde-dev-service_mysql-dev_1:3306(环境变量已配置并同名可不写)");
	        return params;
	    }
	    
	    public void setupTest(JavaSamplerContext jsc) {
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
		    lport = jsc.getParameter("lport");
		    filepath = jsc.getParameter("filepath");
		    sql = jsc.getParameter("sql");
		    System.out.println("setupTest" + sql);
		    dbclass = jsc.getParameter("dbclass");
		    dburl = jsc.getParameter("dburl");
		    evalue = jsc.getParameter("evalue");
		    dbev = jsc.getParameter("dbev");
	    	results = new SampleResult();
	    	results.sampleStart(); 
	    	sql=sql.toLowerCase();
	    	JMeterContext jmcx = JMeterContextService.getContext();
      	    JMeterVariables vars = jmcx.getVariables();
      	    String prints=vars.get("prints");
		if (lport.contains("可不写")) {
			lport = vars.get("lport");
		}
		if (filepath.contains("可不写")) {
			filepath = vars.get("filepath");
		}
		if (dbclass.contains("可不写")) {
			dbclass = vars.get("dbclass");
		}
		if (dburl.contains("可不写")) {
			dburl = vars.get("dburl");
		}
		if (dbev.contains("可不写")) {
			dbev = vars.get("dbev");
		}
      	    if(prints==null){
      	    	prints="yes";
      	    }
	    	try {
	    		String[] ech=dbev.split(";");
	        	int num=ech.length;
	        	String[] sshev=new String[num];
	        	for(int i=0;i<num;i++){
	        		sshev[i]=ech[i];
	        		if(prints.equals("yes")){
	        		  log.info("数据库配置为："+ech[i]);
	        		}
	        	}
	        	if(sql.contains("必写")||sql.equals("")){
	        		sql= (String) JOptionPane.showInputDialog(null, "没有配置sql，请输入sql", "输入sql", JOptionPane.PLAIN_MESSAGE, null, null,"");
	        	}
	        	if(sql.equals("")){
	        		log.info("sql和evalue都不能为空，执行失败");
	        	}else{
	        		if(sql.contains("空值")){
	        			text="空值";
	        		}else{
	        			text=Database.database(Integer.parseInt(lport), filepath, sql, dbclass,dburl,evalue,sshev);
	        		}
	        	}
	        	if(!evalue.contains("环境参数名称")){
	      	           vars.put(evalue, text);
	        	}
	        	vars.put("prints", "no");
	        	log.info("sql语句为："+sql);
	    		if(sql.contains("select id from tb_loan_person".toLowerCase())){
	    			log.info("用户编号id："+text);
	    		}else if(sql.contains("select id from tb_user_loan_order".toLowerCase())){
					log.info("订单编号id："+text);
				}else if(sql.contains("select STATUS from tb_user_loan_order".toLowerCase())){
					log.info("订单状态status："+text);
				}else if(sql.contains("SELECT order_uuid FROM tb_user_credit_money_log".toLowerCase())){
					log.info("还款回调的订单id："+text);
				}else if(sql.contains("SELECT operator_money FROM tb_user_credit_money_log".toLowerCase())){
					log.info("还款回调金额:"+text);
				}else if(sql.contains("select id from tb_card_info".toLowerCase())){
					log.info("还款银行卡id："+text);
				}else if(sql.contains("SELECT total_money FROM tb_user_loan_order_repayment".toLowerCase())){
					log.info("还款回调的金额："+text);
				}else if(sql.contains("select money from tb_financial_loan_record".toLowerCase())){
					log.info("打款回调的订单金额："+text);
				}else if(sql.contains("UPDATE tb_user_loan_order_repayment set plan_repayment_time".toLowerCase())){
					log.info("修改数据返回："+text);
				}else if(sql.contains("select order_id from tb_financial_loan_record".toLowerCase())){
					log.info("订单打款回调id："+text);
				}else if(sql.contains("select order_type from tb_user_loan_order".toLowerCase())){
					log.info("订单状态order_type："+text);
				}else{
					log.info("------------:"+text+":-------------");
				}
		    	results.setSuccessful(true);  
			} catch (Exception e) {
				results.setSuccessful(false);
			}
	         try {  
	             Thread.sleep(1000);  
	         } catch (InterruptedException e) {  
	             // TODO Auto-generated catch block  
	             e.printStackTrace();  
	         }  
	         results.sampleEnd();  
			return results;
		}

     
	    public void teardownTest(JavaSamplerContext arg0) {
	    }

}
