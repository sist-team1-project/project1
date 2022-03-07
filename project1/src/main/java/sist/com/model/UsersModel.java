package sist.com.model;

import javax.servlet.http.*;



import sist.com.dao.UsersDAO;
import sist.com.vo.UsersVO;

import sist.com.dao.UsersDAO;

import sist.com.controller.RequestMapping;
import sist.com.dao.*;
import sist.com.vo.*;

public class UsersModel {

    @RequestMapping("member/loginpage.do")
    public String login_page(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("main_jsp", "../member/login.jsp");
        return "../main/main.jsp";
    }

    @RequestMapping("member/login.do")
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
        return "../member/login_result.jsp";
    }

    @RequestMapping("member/logout.do")
    public String memberLogout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate(); // session해제
        return "redirect:../main/main.do";
    }

    @RequestMapping("member/mypage.do")
    public String mypage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        UsersDAO dao = new UsersDAO();

        UsersVO user = dao.userDetail(id);

        request.setAttribute("user", user);

        request.setAttribute("main_jsp", "../member/mypage.jsp");
        return "../main/main.jsp";
    }

    @RequestMapping("member/join.do")
    public String loginJoin(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("main_jsp", "../member/join.jsp");
        return "../main/main.jsp";
    }

    @RequestMapping("member/idcheck.do")
    public String loginIdCheck(HttpServletRequest request, HttpServletResponse response) {
        return "../member/idcheck.jsp";
    }

    @RequestMapping("member/idcheck_result.do")
    public String loginIdCheckResult(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        UsersDAO dao = new UsersDAO();
        int count = dao.loginIdcheck(id);
        request.setAttribute("count", count);
        return "../member/idcheck_result.jsp";
    }

    @RequestMapping("member/join_ok.do")
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
    @RequestMapping("member/idfind.do")
    public String idfind(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("main_jsp", "../member/idfind.jsp");
        return "../main/main.jsp";
    }
    @RequestMapping("member/idfind_result.do")
    public String memberIdFindResult(HttpServletRequest request,
            HttpServletResponse response)
    {
        String name=request.getParameter("name");
        UsersDAO dao=new UsersDAO();
        String result=dao.idfind_name(name);
        request.setAttribute("result", result);
        return "../member/idfind_result.jsp";
    }
    @RequestMapping("member/email_result.do")
    public String memberEmailResult(HttpServletRequest request,
            HttpServletResponse response)
    {
        String email=request.getParameter("email");
        UsersDAO dao=new UsersDAO();
        String result=dao.idfind_email(email);
        request.setAttribute("result", result);
        return "../member/idfind_result.jsp";
    }
    @RequestMapping("member/join_update.do")
    public String joinUpdate(HttpServletRequest request,
            HttpServletResponse response)
    {
        // 정보를 출력해 준다
        HttpSession session=request.getSession();
        // id,name가 저장 
        String id=(String)session.getAttribute("id");
        // DAO연동 => id에 해당되는 데이터를 읽어 온다 
        UsersDAO dao=new UsersDAO();
        UsersVO vo=dao.memberUpdateData(id);
        
        request.setAttribute("vo", vo); //DTO 
        // vo=> JSP에서 출력에 필요한 데이터를 전송하는 목적으로 모아서 처리 
        request.setAttribute("main_jsp", "../member/join_update.jsp");
        return "../main/main.jsp";
    }
    @RequestMapping("member/join_delete.do")
    public String memberjoinDelete(HttpServletRequest request,
            HttpServletResponse response)
    {
        
        request.setAttribute("main_jsp", "../member/join_delete.jsp");
        return "../main/main.jsp";
    }
    @RequestMapping("member/join_delete_ok.do")
    public String memberJoinDeleteOk(HttpServletRequest request,
            HttpServletResponse response)
    {
        String pwd=request.getParameter("pwd");
        HttpSession session=request.getSession(); // id,name,admin
        String id=(String)session.getAttribute("id");
        UsersDAO dao=new UsersDAO();
        // 결과값 
        String result=dao.memberJoinDelete(pwd, id);
        if(result.equals("yes"))
        {
            session.invalidate(); // 세션 해제 
        }
        request.setAttribute("result", result);
        return "../member/join_delete_ok.jsp";//ajax => _ok
    }
}
