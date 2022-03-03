package sist.com.model;

import javax.servlet.http.*;

import sist.com.controller.RequestMapping;
import sist.com.dao.*;
import sist.com.vo.*;

public class UsersModel {

    @RequestMapping("users/login.do")
    public String login_page(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("main_jsp", "../login/login.jsp");
        return "../main/main.jsp";
    }

    @RequestMapping("users/login_result.do")
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
        return "../login/login_result.jsp";
    }

    @RequestMapping("users/logout.do")
    public String memberLogout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate(); // session해제
        return "redirect:../main/main.do";
    }

    @RequestMapping("users/mypage.do")
    public String mypage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        UsersDAO dao = new UsersDAO();

        UsersVO user = dao.userDetail(id);

        request.setAttribute("user", user);

        request.setAttribute("main_jsp", "../login/mypage.jsp");
        return "../main/main.jsp";
    }

    @RequestMapping("login/join.do")
    public String loginJoin(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("main_jsp", "../login/join.jsp");
        return "../main/main.jsp";
    }

    @RequestMapping("login/idcheck.do")
    public String loginIdCheck(HttpServletRequest request, HttpServletResponse response) {
        return "../login/idcheck.jsp";
    }

    @RequestMapping("login/idcheck_result.do")
    public String loginIdCheckResult(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        UsersDAO dao = new UsersDAO();
        int count = dao.loginIdcheck(id);
        request.setAttribute("count", count);
        return "../login/idcheck_result.jsp";
    }

    @RequestMapping("login/join_ok.do")
    public String loginJoinOk(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (Exception ex) {
        } 
        String id = request.getParameter("u_id");
        String password = request.getParameter("u_password");
        String name = request.getParameter("u_name");
        String birthday = request.getParameter("u_birthday");
        String gender = request.getParameter("u_gender");
        String email = request.getParameter("u_email");
        String post = request.getParameter("u_post");
        String address1 = request.getParameter("u_address1");
        String address2 = request.getParameter("u_address2");
        String question = request.getParameter("u_question");
        String answer = request.getParameter("u_answer");


        UsersVO vo = new UsersVO();
        vo.setU_id(id);
        vo.setU_password(password);
        vo.setU_name(name);
        vo.setU_birthday(birthday);
        vo.setU_gender(gender);
        vo.setU_email(email);
        vo.setU_post(post);
        vo.setU_address1(address1);
        vo.setU_address2(address2);
        vo.setU_question(question);
        vo.setU_answer(answer);
        UsersDAO dao = new UsersDAO();
        
        dao.loginJoin(vo);
        return "redirect:../main/main.do";
    }
}
