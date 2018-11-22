package tools;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Codes {
	public static String getMd5(String plainText) {  
        try {  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            md.update(plainText.getBytes());  
            byte b[] = md.digest();  
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {  
                i = b[offset];  
                if (i < 0)  
                    i += 256;  
                if (i < 16)  
                    buf.append("0");  
                buf.append(Integer.toHexString(i));  
            }  
            //32位加密  
            return buf.toString();  
            // 16位的加密  
            //return buf.toString().substring(8, 24);  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
            return null;  
        }  

    }
    /*
    base64加密
     */
    public static String getEbase64(String plainText){
        byte[] input = plainText.getBytes();
        Base64.Encoder encoder = Base64.getEncoder();
        String data = encoder.encodeToString(input);
        return data;
    }
    /*
    base64解密
     */
    public static String getDbase64(String plainText){
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] output = decoder.decode(plainText);
        String data=new String(output);
        return data;
    }
}
