package com.vainqueur.pdftool.utils;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PDFUtils {
    /**
     * 获取空白PDF byte数组
     * @return
     */
    public static byte[] createEmptyPDFByteArray(){
        PDDocument document = new PDDocument();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            document.save(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
                if (document != null) {
                    document.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
