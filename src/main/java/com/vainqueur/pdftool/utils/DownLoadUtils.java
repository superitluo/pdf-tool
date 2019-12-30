package com.vainqueur.pdftool.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

public class DownLoadUtils {
    static Logger  logger=LoggerFactory.getLogger(DownLoadUtils.class);

    /**
     * 返回待下载文件值给客户端
     * @param response
     * @param data
     * @param fileName
     * @author superitluo
     * @return
     */
    public static Boolean responseFile(HttpServletResponse response,byte[] data,String fileName){
        String name = (fileName == null) ? "未命名" : fileName;
        OutputStream outputStream=null;
        try {
            fileName = URLEncoder.encode(name, "UTF-8");
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.addHeader("Content-Length", "" + data.length);
            response.setContentType("application/octet-stream;charset=UTF-8");
            outputStream = new BufferedOutputStream(response.getOutputStream());
            outputStream.write(data);
            return true;
        } catch (Exception e) {
            //print download failed info
            logger.error(e.getMessage());
            return false;
        }finally {
            //close stream
            if(outputStream!=null){
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
