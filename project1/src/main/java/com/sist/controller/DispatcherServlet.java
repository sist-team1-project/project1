package com.sist.controller;

import java.io.*;
import java.lang.reflect.Method;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.util.*;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    List<String> modelList = new ArrayList<String>();

    public void init(ServletConfig config) throws ServletException {
        modelList.add("com.sist.model.MainModel");
        modelList.add("com.sist.model.AdModel");
        modelList.add("com.sist.model.CompanyModel");
        modelList.add("com.sist.model.FreeboardModel");
        modelList.add("com.sist.model.SearchModel");
        modelList.add("com.sist.model.UsersModel");
        modelList.add("com.sist.model.ReplyModel");
        modelList.add("com.sist.model.FavoriteModel");
        modelList.add("com.sist.model.ReviewModel");
        modelList.add("com.sist.model.CalendarModel");
        modelList.add("com.sist.model.MypageModel");
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String uri = request.getRequestURI();
            
            uri = uri.substring(request.getContextPath().length() + 1);
            
            for (String cls : modelList) {
                Class clsName = Class.forName(cls);
                Object obj = clsName.getConstructor().newInstance();
                
                Method[] methods = clsName.getDeclaredMethods();
                for (Method m : methods) {
                    RequestMapping rm = m.getAnnotation(RequestMapping.class);
                    if (rm.value().equals(uri)) {
                        String jsp = (String) m.invoke(obj, request, response);
                        if (jsp.startsWith("redirect:")) {
                            String s = jsp.substring(jsp.indexOf(":") + 1);
                            response.sendRedirect(s);
                        } else {
                            RequestDispatcher rd = request.getRequestDispatcher(jsp);
                            rd.forward(request, response);
                        }
                        return;
                    }
                }
            }
        } catch (Exception ex) {
        }
    }
}