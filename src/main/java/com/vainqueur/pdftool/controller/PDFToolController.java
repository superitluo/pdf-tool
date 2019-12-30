package com.vainqueur.pdftool.controller;

import com.vainqueur.pdftool.common.BaseController;
import com.vainqueur.pdftool.utils.DownLoadUtils;
import com.vainqueur.pdftool.utils.PDFUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("PDF")
public class PDFToolController extends BaseController {

    @GetMapping("/new")
    public void  createPDF(){
        DownLoadUtils.responseFile(getResponse(), PDFUtils.createEmptyPDFByteArray(),"新建文件.pdf");
    }

}
