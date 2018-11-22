package test;
import tools.OCR.OCR;

import java.io.File;

public class TestOCR {

    public static void main(String[] args) {
        String path = "D://0.jpg";
        System.out.println("ORC Test Begin......");
        try {
            String valCode = new OCR().recognizeText(new File(path), "PNG");
            System.out.println("2222"+valCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ORC Test End......");
    }
}