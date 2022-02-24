package sist.com.model;

import java.util.StringTokenizer;

import javax.servlet.http.*;
import javax.servlet.http.HttpSession;

import sist.com.controller.RequestMapping;
import sist.com.dao.*;

public class UsersModel {
    
    @RequestMapping("users/loginpage.do")
    public String login_page(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("main_jsp", "../login/login.jsp");
        return "../main/main.jsp";
    }
    
    @RequestMapping("users/login.do")
    public String memberLogin(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");
        UsersDAO dao = new UsersDAO();
        String result = dao.isLogin(id, pwd);
        request.setAttribute("result", result);
        if (!(result.equals("FAIL"))) {
            HttpSession session = request.getSession();
            session.setAttribute("id", id);
            session.setAttribute("name", result);
        }
        return "../login/login_ok.jsp";
    }

    @RequestMapping("users/logout.do")
    public String memberLogout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate(); //session해제
        return "redirect:../main/main.do";
    }
}
