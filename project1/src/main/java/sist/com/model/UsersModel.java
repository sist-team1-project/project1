package sist.com.model;

import sist.com.controller.RequestMapping;
import java.util.*;
import javax.servlet.http.*;

import sist.com.vo.*;
import sist.com.dao.*;

public class UsersModel {
    
    @RequestMapping("login/login.do")
    public void login(HttpServletRequest request) {
     
        String usersid = request.getParameter("id");
        String userspassword = request.getParameter("password");
        UsersDAO usersDao = new UsersDAO();
        UsersVO vo=usersDao.isLogin(usersid, userspassword);
        if(vo == null) {
            request.setAttribute("result", null);
        }
        else {
            HttpSession session=request.getSession();
            session.setAttribute("result", vo);
            request.setAttribute("result", vo);
        }
    }
}