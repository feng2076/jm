package jmeter;

import org.apache.commons.net.util.Base64;

public  class PictureBase64 {
    public static String getBase64(byte[] bytes){
        return byteArrayToStr(Base64.encodeBase64(bytes));
    }
    public static String byteArrayToStr(byte[] byteArray) {
        if (byteArray == null) {
            return null;
        }
        String str = new String(byteArray);
        return str;
    }
}
