package sist.com.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import sist.com.dao.CompanyDAO;
import sist.com.dao.ReviewDAO;
import sist.com.dao.UsersDAO;
import sist.com.vo.CompanyVO;
import sist.com.vo.UsersVO;

public class UsersModel {
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
        
        
        