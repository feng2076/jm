package tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KillPort {
    public static String getPID(String port) {
        InputStream is = null;
        BufferedReader br = null;
        String pid = null;
        try {
            String[] args = new String[]{"cmd.exe", "/c", "netstat -aon|findstr", port};
            is = Runtime.getRuntime().exec(args).getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            String temp = br.readLine();
            if (temp != null) {
                String[] strs = temp.split("\\s");
                pid = strs[strs.length - 1];
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return pid;
    }

    public static String killport(String port) {
        KillPort kp = new KillPort();
        String pids = kp.getPID(port);
        if (pids == null) {
            return "0";
        }else{
            return "1";
        }
    }

}
