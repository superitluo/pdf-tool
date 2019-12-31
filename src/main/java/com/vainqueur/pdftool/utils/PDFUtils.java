package com.vainqueur.pdftool.utils;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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

    /**
     * doc1=doc1+doc2
     * @param doc1
     * @param doc2
     * @throws IOException
     */
    public static void mergePDF(PDDocument doc1,PDDocument doc2) throws IOException {
        PDFMergerUtility PDFmerger = new PDFMergerUtility();
        PDFmerger.appendDocument(doc1,doc2);
    }
    public static Boolean splitPDF(int sheetNum,String sourcePath,String desPath) {
        PDDocument document = null;
        PDDocument pdDocument = null;
        try {
            File file = new File(sourcePath);
            document = PDDocument.load(file);
            Splitter splitter = new Splitter();
            List<PDDocument> pages = splitter.split(document);
            int size = pages.size();
            int num = size / sheetNum;
            int nowNum = 1;
            pdDocument = new PDDocument();
            PDFMergerUtility merger = new PDFMergerUtility();
            int partNum = 1;
            for (PDDocument data : pages) {
                merger.appendDocument(pdDocument, data);
                if (num == nowNum || size == 0) {//保存
                    FileUtils.forceMkdir(new File(desPath));
                    pdDocument.save(desPath + "\\part" + partNum + ".pdf");
                    pdDocument.close();//关闭
                    partNum++;//下一部分
                    pdDocument = new PDDocument();//清空pdDocument

                    nowNum=1;
                }
                size--;
                nowNum++;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            try{
                if(document!=null){
                    document.close();
                    pdDocument.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        PDFUtils.splitPDF(3,"F:\\桑小宇办公\\file\\法语修订本教学辅导参考书 .pdf","F:\\桑小宇办公\\file\\分割后");
    }
}
