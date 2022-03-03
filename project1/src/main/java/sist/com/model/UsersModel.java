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
    public String memberIdCheck(HttpServletRequest request, HttpServletResponse response) {
        return "../member/idcheck.jsp";
    }

    @RequestMapping("login/idcheck_result.do")
    public String memberIdCheckResult(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        UsersDAO dao = new UsersDAO();
        int count = dao.loginIdcheck(id);
        request.setAttribute("count", count);
        return "../login/idcheck_result.jsp";
    }

    @RequestMapping("login/join_ok.do")
    public String memberJoinOk(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (Exception ex) {
        }
        // 입력값 받기 
        String id = request.getParameter("u_id");
        String password = request.getParameter("u_password");
        String name = request.getParameter("u_name");
        String profile = request.getParameter("u_profile");
        String birthday = request.getParameter("u_birthday");
        String gender = request.getParameter("u_gender");
        String email = request.getParameter("u_email");
        String address = request.getParameter("u_address");
        String question = request.getParameter("u_question");
        String answer = request.getParameter("u_answer");


        UsersVO vo = new UsersVO();
        vo.setU_id(id);
        vo.setU_password(password);
        vo.setU_name(name);
        vo.setU_profile(profile);
        vo.setU_birthday(birthday);
        vo.setU_gender(gender);
        vo.setU_email(email);
        vo.setU_address(address);
        vo.setU_question(question);
        vo.setU_answer(answer);
        UsersDAO dao = new UsersDAO();
        //메소드 (INSERT)
        dao.loginJoin(vo);
        return "redirect:../main/main.do";
    }
}
