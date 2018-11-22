package tools;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;


public class JsonCheck {

    public static String allkey="";

    public static String json(String ischeck,String check) throws Exception, FileNotFoundException{
        InputStreamReader isr1 = null;
        InputStreamReader isr2 = null;
        //被比对对象
        isr1 = new InputStreamReader(new FileInputStream(ischeck), "GBK");
        //比对对象
        isr2 = new InputStreamReader(new FileInputStream(check), "GBK");
        BufferedReader br1 = null;
        BufferedReader br2 = null;
        br1 = new BufferedReader(isr1);
        br2 = new BufferedReader(isr2);
        String s1 = null;
        String s2 = null;
        String bqs = "";
        String jxl = "";
        while ((s1 = br1.readLine()) != null) {
            bqs = bqs + s1;
        }
        while ((s2 = br2.readLine()) != null) {
            jxl = jxl + s2;
        }
        //System.out.println(bqs + "---\n" + jxl);
        br1.close();
        br2.close();
        isr1.close();
        isr2.close();
        JSONObject jsonjxl = JSONObject.fromObject(jxl);
        JSONObject jsonbqs = JSONObject.fromObject(bqs);
        String result=getKeys(jsonjxl);
        String[] key=allkey.split(",");
        String exitst="";
        for(int i=0;i<key.length;i++){
            if(!key[i].equals("")){
                //System.out.println(key[i]);
                if(!jsonbqs.toString().contains("\""+key[i]+"\"")&&!exitst.contains(key[i])){
                    exitst=exitst+"\n"+key[i];
                }
            }
        }
        System.out.println(exitst);
        return exitst;
    }
    public static  String getKeys(JSONObject jsonjxl) throws JSONException{
        String result = null;
        Iterator keys = jsonjxl.keys();
        JSONObject js;
        while(keys.hasNext()){
            try{
                String key = keys.next().toString();
                allkey=allkey+","+key;
                String value = jsonjxl.optString(key);
                int i = testIsArrayORObject(value);
                if(result == null || result.equals("")){
                    if(i == 0){
                        result=key + ",";
                    }else if( i == 1){
                        result=key + ",";
                        js=JSONObject.fromObject(value);
                        result=getKeys(js)+",";
                    }else if( i == 2){
                        result=key + ",";
                        JSONArray arrays = JSONArray.fromObject(value);
                        for(int k =0;k<arrays.size();k++){
                            //JSONObject array = new JSONObject(arrays.get(k));
                            try {
                                JSONObject array =JSONObject.fromObject(arrays.get(k));
                                result=getKeys(array) + ",";
                            } catch (Exception e) {
                            }

                        }
                    }
                    //   allkey=allkey+result;
                }else{
                    if(i == 0){
                        result=result+key + ",";
                    }else if( i == 1){
                        result=result+key + ",";
                        // js=new JSONObject(value);
                        js=JSONObject.fromObject(value);
                        result=result+getKeys(js);
                    }else if( i == 2){
                        result=result+key + ",";
                        //JSONArray arrays = new JSONArray(value);
                        JSONArray arrays =JSONArray.fromObject(value);
                        for(int k =0;k<arrays.size();k++){
                            // JSONObject array = new JSONObject(arrays.get(k));
                            try {
                                JSONObject array =JSONObject.fromObject(arrays.get(k));
                                result=result+getKeys(array) + ",";
                            } catch (Exception e) {
                                result=result;
                            }
                        }
                    }
                    //   allkey=allkey+result;
                }


            }catch(JSONException e){
                e.printStackTrace();
            }
        }


        return result;
    }
    public static int testIsArrayORObject(String sJSON){
		    /*
		     * return 0:既不是array也不是object
		     * return 1：是object
		     * return 2 ：是Array
		     */
        try {
            //JSONArray array = new JSONArray(sJSON);
            JSONArray array = JSONArray.fromObject(sJSON);;
            return 2;
        } catch (JSONException e) {// 抛错 说明JSON字符不是数组或根本就不是JSON
            try {
                //JSONObject object = new JSONObject(sJSON);
                JSONObject object =JSONObject.fromObject(sJSON);
                return 1;
            } catch (JSONException e2) {// 抛错 说明JSON字符根本就不是JSON
                return 0;
            }
        }

    }
}
