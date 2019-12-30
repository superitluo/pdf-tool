package com.vainqueur.pdftool.common;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseController {
    /**
     * 获取request
     * @return
     */
    public HttpServletRequest getRequest(){
        return getServletRequestAttributes().getRequest();
    }

    /**
     * 获取response
     * @return
     */
    public HttpServletResponse getResponse(){
        return getServletRequestAttributes().getResponse();
    }
    public ServletRequestAttributes getServletRequestAttributes(){
        return (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
    }

    /**
     * 获取session
     * @return
     */
    public HttpSession getSession(){
        return getRequest().getSession();
    }
}
