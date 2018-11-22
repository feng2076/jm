package tools;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UseCsv {
    public static int readcsvline(String filepath){
        File csv = new File(filepath);  // CSV文件路径
        BufferedReader br = null;
        int row = 0;
        try {
            br = new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = "";
        String everyLine = "";
        try {
            List<String> allString = new ArrayList();
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
                everyLine = line;
              //  System.out.println(everyLine);
                allString.add(everyLine);
            }
            System.out.println("csv表格中所有行数：" + allString.size());
            row=allString.size()-1;
            System.out.println("csv表格中有效行数：" + row);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return row;
    }
    public void writecsv(){

    }
}
