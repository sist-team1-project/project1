package com.sist.model;

import javax.servlet.http.*;

import com.sist.dao.*;
import com.sist.vo.*;

import com.sist.controller.*;

public class UsersModel {
    
    // 헤더 - 로그인 페이지 이동
    @RequestMapping("users/login.do")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        
        request.setAttribute("main_jsp", "../users/login.jsp");
        return "../main/main.jsp";
    }
    
    // 로그인 - 로그인 결과
    @RequestMapping("users/login_ok.do")
    public String login_ok(HttpServletRequest request, HttpServletResponse response) {
        
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
        return "../users/login_result.jsp";
    }
    
    // 헤더 - 로그아웃
    @RequestMapping("users/logout_ok.do")
    public String logout_ok(HttpServletRequest request, HttpServletResponse response) {
        
        HttpSession session = request.getSession();
        session.invalidate(); // session 해제
        return "redirect:../main/main.do";
    }
    
    // 로그인 - 회원가입 페이지 이동
    @RequestMapping("users/join.do")
    public String join(HttpServletRequest request, HttpServletResponse response) {
        
        request.setAttribute("main_jsp", "../users/join.jsp");
        return "../main/main.jsp";
    }
    
    // 로그인 - 아이디 찾기 쉐도우박스
    @RequestMapping("users/idfind.do")
    public String idfind(HttpServletRequest request, HttpServletResponse response) {
        
        return "../users/idfind.jsp";
    }
    
    // 로그인 - 비밀번호 찾기 쉐도우박스
    @RequestMapping("users/pwfind.do")
    public String qfind(HttpServletRequest request, HttpServletResponse response) {
        
        return "../users/pwfind.jsp";
    }
    
    // 로그인 - 비밀번호 찾기 쉐도우박스
    @RequestMapping("users/pwfind2.do")
    public String pwfind(HttpServletRequest request, HttpServletResponse response) {
        
        String id = request.getParameter("id");
        String question = request.getParameter("question");
        
        request.setAttribute("id", id);
        request.setAttribute("question", question);
        return "../users/pwfind2.jsp";
    }
    
    // 로그인 - 아이디 찾기 결과
    @RequestMapping("users/idfind_result.do")
    public String idfind_result(HttpServletRequest request, HttpServletResponse response) {
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        UsersDAO dao = new UsersDAO();
        String result = dao.idFind(name, email);
        request.setAttribute("result", result);
        return "../users/result.jsp";
    }
    
    // 로그인 - 비밀번호 찾기 - 아이디/이름 입력하여 질문찾기
    @RequestMapping("users/pwfind_result.do")
    public String questionfind_result(HttpServletRequest request, HttpServletResponse response) {
        
        String name = request.getParameter("name");
        String id = request.getParameter("id");
        
        UsersDAO dao = new UsersDAO();
        String result = dao.pwFind1(name, id);
        
        request.setAttribute("result", result);
        return "../users/result.jsp";
    }
    
    // 로그인 - 비밀번호 찾기 - 답변 일치 여부
    @RequestMapping("users/pwfind2_result.do")
    public String pwfind_result(HttpServletRequest request, HttpServletResponse response) {
        
        String id = request.getParameter("id");
        String answer = request.getParameter("answer");
        
        UsersDAO dao = new UsersDAO();
        String result = dao.pwFind2(id, answer);
        
        request.setAttribute("result", result);
        return "../users/result.jsp";
    }
    
    // 회원가입 - 아이디 중복 확인
    @RequestMapping("users/idcheck.do")
    public String idcheck(HttpServletRequest request, HttpServletResponse response) {
        
        return "../users/idcheck.jsp";
    }
    
    // 회원가입 - 이메일 중복 확인
    @RequestMapping("users/emailcheck.do")
    public String emailcheck(HttpServletRequest request, HttpServletResponse response) {
        
        return "../users/emailcheck.jsp";
    }
    
    // 회원가입 - 아이디 중복 확인 결과
    @RequestMapping("users/idcheck_result.do")
    public String idcheck_result(HttpServletRequest request, HttpServletResponse response) {
        
        String id = request.getParameter("id");
        UsersDAO dao = new UsersDAO();
        int result = dao.idCheck(id);
        request.setAttribute("result", result);
        return "../users/result.jsp";
    }
    
    // 회원가입 - 아이디 중복 확인 결과
    @RequestMapping("users/emailcheck_result.do")
    public String emailcheck_result(HttpServletRequest request, HttpServletResponse response) {
        
        String email = request.getParameter("email");
        UsersDAO dao = new UsersDAO();
        int result = dao.emailCheck(email);
        request.setAttribute("result", result);
        return "../users/result.jsp";
    }
    
    // 회원가입 - 회원가입 결과
    @RequestMapping("users/join_ok.do")
    public String join_ok(HttpServletRequest request, HttpServletResponse response) {
        
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (Exception ex) {
        }
        
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String post = request.getParameter("post");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String question = request.getParameter("question");
        String answer = request.getParameter("answer");

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
        
        dao.join(vo);
        return "redirect:../main/main.do";
    }
}