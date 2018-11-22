package test;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import com.alibaba.fastjson.JSONObject;
public class Pay {
    // private static Logger logger = Logger.getLogger(Pay.class);
    public static void main(String[] args) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("project_name", "jsqb_lakala");
        map.put("pwd", "123456");
        map.put("yur_ref", "20180105161250_75a0d6b206abc01");
        map.put("user_id", "5053589");
        map.put("real_name", "许枫");
        map.put("bank_id", "8");
        map.put("card_no", "6214850214904853");
        map.put("money", "1");
        map.put("fee", "0");
        map.put("pay_summary", "测试放款");
        // 加签
        String sign = createSign(map, "**kdpay_jsqb_lakala**");//**kdpay_open_test**
        map.put("sign", sign);
        System.out.println("加签后字符串：" + sign);
        String param = reqStrUtil(map);
        System.out.println("请求参数：" + param);
        String result = sendPostRequestBody("http://paymax.test.withdraw.koudailc.com:8081/withdraw/withdraw-one-v2", param);
        System.out.println("返回结果：" + result);
    }
    /**
     * 生成签名字符串
     * @param reqMap
     * @param key
     * @return
     */
    public static String createSign(Map<String,String> reqMap,String key){
        //加签原串
        String str ="";
        try {
            //对请求参数进行urlencode加密
            for (Map.Entry<String, String> entry : reqMap.entrySet()) {
                reqMap.put(entry.getKey(), URLEncoder.encode(entry.getValue(),"utf-8"));
            }
        }catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //对参数进行升序排序
        List<Map.Entry<String, String>> sortMap = Pay.argSort(reqMap);
        for (int i = 0; i < sortMap.size(); i++) {
            str += sortMap.get(i).getKey() + "=" + sortMap.get(i).getValue() + "&";
        }
        //清除最后一个&
        if(str.length() > 1){
            str = str.substring(0,str.length()-1);
        }
        str+=key;
        // 获取MD5算法实例 得到一个md5的消息摘要
//        MessageDigest md = null;
//        try {
//            md = MessageDigest.getInstance("MD5");
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        //添加要进行计算摘要的信息
//        md.update(Base64.encodeBase64(str.getBytes()));
        byte[] bytes=Base64.encodeBase64(str.getBytes());
        //得到该摘要
//        byte[] bytes = md.digest();
        return new String(bytes);
    }
    /**
     * 对请求参数进行处理
     * @param reqMap
     * @return
     */
    public static String reqStrUtil(Map<String,String> reqMap){
        String reqStr = "";
        for (Map.Entry<String, String> entry : reqMap.entrySet()) {
            reqStr+=entry.getKey()+"="+entry.getValue()+"&";
        }
        return reqStr.substring(0,reqStr.length()-1);
    }
    /**
     * 排序 a-z 根据键排序
     * @param map
     * @return
     */
    public static List<Map.Entry<String, String>> argSort(Map<String, String> map) {
        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(map.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            //升序排序
            public int compare(Map.Entry<String, String> o1,
                               Map.Entry<String, String> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        return list;
    }
    /**
     * 向指定 URL 发送POST方法的请求
     * @param requestUrl 发送请求的 URL
     * @param param 请求参数，请求参数是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPostRequestBody(String requestUrl, String param) throws Exception {
        // logger.info("=======调用口袋理财请求参数：" + param);
        OutputStreamWriter out = null;
        InputStream inputStream = null;
        String result = "";
        try {
            URL url = new URL(requestUrl);
            URLConnection urlConnection = url.openConnection();
            // 设置doOutput属性为true表示将使用此urlConnection写入数据
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
            // 得到请求的输出流对象
            out = new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8");
            // 把数据写入请求的Body
            out.write(param);
            out.flush();
            // 从服务器读取响应
            inputStream = urlConnection.getInputStream();
            result = IOUtils.toString(inputStream, "utf-8");
            // logger.info("=======口袋理财返回结果：" + result);
        } catch (Exception e) {
            // logger.error("=======调用口袋理财出现异常：" + e);
        } finally {
            if (out != null) {
                out.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return result;
    }
    /**
     * 口袋回调验签
     * @param params
     * @param securtyKey
     * @return
     */
    public static boolean verifySign(JSONObject params, String securtyKey){
        if(params.getString("sign") == null){
            return false;
        }
        String resultSign = params.getString("sign");
        Map<String,String> resultMap = new HashMap<String, String>();
        resultMap = (Map)params;
        resultMap.remove("sign");
        resultMap.put("code",params.get("code")+"");
        String sign = createSign(resultMap,securtyKey);
        return resultSign.equals(sign);
    }
}
