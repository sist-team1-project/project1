package sist.com.controller;

import java.io.*;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sist.com.model.*;

import java.util.*;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    List<String> modelList = new ArrayList<String>();

    public void init(ServletConfig config) throws ServletException {
        modelList.add("sist.com.model.MainModel");
        modelList.add("sist.com.model.AdModel");
        modelList.add("sist.com.model.CompanyModel");
        modelList.add("sist.com.model.FreeboardModel");
        modelList.add("sist.com.model.MainModel");
        modelList.add("sist.com.model.SearchModel");
        modelList.add("sist.com.model.UsersModel");
        modelList.add("sist.com.model.ReplyModel");
        modelList.add("sist.com.model.FavoriteModel");
        modelList.add("sist.com.model.ReviewModel");
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String uri = request.getRequestURI();

            uri = uri.substring(request.getContextPath().length() + 1);

            for (String cls : modelList) {
                //1. 메모리 할당
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