package sist.com.model;

import java.util.*;

import javax.servlet.http.*;

import sist.com.controller.RequestMapping;

import sist.com.dao.*;
import sist.com.vo.*;

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
    
    // 헤더 - 마이페이지 이동
    @RequestMapping("users/mypage.do")
    public String mypage(HttpServletRequest request, HttpServletResponse response) {
        
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        UsersDAO dao = new UsersDAO();

        UsersVO user = dao.userDetail(id);

        request.setAttribute("user", user);

        request.setAttribute("main_jsp", "../users/mypage.jsp");
        return "../main/main.jsp";
    }
    
    // 로그인 - 회원가입 페이지 이동
    @RequestMapping("users/join.do")
    public String join(HttpServletRequest request, HttpServletResponse response) {
        
        request.setAttribute("main_jsp", "../users/join.jsp");
        return "../main/main.jsp";
    }
    
    // 로그인 - 아이디 찾기
    @RequestMapping("users/idfind.do")
    public String idfind(HttpServletRequest request, HttpServletResponse response) {
        
        return "../users/idfind.jsp";
    }
    
    // 로그인 - 비밀번호 찾기
    @RequestMapping("users/pwfind.do")
    public String qfind(HttpServletRequest request, HttpServletResponse response) {
        
        return "../users/pwfind.jsp";
    }
    
    // 로그인 - 비밀번호 찾기
    @RequestMapping("users/pwfind2.do")
    public String pwfind(HttpServletRequest request, HttpServletResponse response) {
        
        String id = request.getParameter("id");
        String question = request.getParameter("question");
        
        request.setAttribute("id", id);
        request.setAttribute("question", question);
        return "../users/pwfind2.jsp";
    }
    
    // 로그인 - 비밀번호 초기화
    @RequestMapping("users/pwreset.do")
    public String pwrest(HttpServletRequest request, HttpServletResponse response) {
        
        String id = request.getParameter("id");
        request.setAttribute("id", id);
        return "../users/pwreset.jsp";
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
    
    // 로그인 - 비밀번호 찾기 - 비밀번호 업데이트
    @RequestMapping("users/pwreset_ok.do")
    public void pwreset_result(HttpServletRequest request, HttpServletResponse response) {
        
        String id = request.getParameter("id");
        String password = request.getParameter("pwd");
        System.out.println("여기");
        System.out.println("id: " + id);
        System.out.println("pwd: " + password);
        
        UsersDAO dao = new UsersDAO();
        dao.pwUpdate(id, password);
    }
    
    // 회원가입 - 아이디 중복 확인
    @RequestMapping("users/idcheck.do")
    public String idcheck(HttpServletRequest request, HttpServletResponse response) {
        
        return "../users/idcheck.jsp";
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
    
    // 회원가입 - 회원가입 결과
    @RequestMapping("users/join_ok.do")
    public String join_ok(HttpServletRequest request, HttpServletResponse response) {
        
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
        
        dao.join(vo);
        return "redirect:../main/main.do";
    }
    
    // 마이페이지 - 회원정보 수정
    @RequestMapping("users/update.do")
    public String update(HttpServletRequest request, HttpServletResponse response) {
        
        HttpSession session = request.getSession();
        
        String id = (String) session.getAttribute("id");
        
        UsersDAO dao = new UsersDAO();
        UsersVO vo = dao.userUpdate(id);
        
        request.setAttribute("vo", vo);
        request.setAttribute("main_jsp", "../users/join_update.jsp");
        return "../main/main.jsp";
    }
    
    // 마이페이지 - 회원정보 수정 결과
    @RequestMapping("users/update_ok.do")
    public String update_ok(HttpServletRequest request, HttpServletResponse response) {
        
        HttpSession session = request.getSession();
        
        String id = (String) session.getAttribute("id");
        
        UsersDAO dao = new UsersDAO();
        UsersVO vo = dao.userUpdate(id);
        
        request.setAttribute("vo", vo);
        request.setAttribute("main_jsp", "../users/update.jsp");
        return "../main/main.jsp";
    }
    

    @RequestMapping("users/delete_ok.do")
    public String memberJoinDeleteOk(HttpServletRequest request, HttpServletResponse response) {
        
        String pwd = request.getParameter("pwd");
        
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        UsersDAO dao = new UsersDAO();
        
        String result = dao.userDelete(pwd, id);
        if (result.equals("yes")) {
            session.invalidate();
        }
        request.setAttribute("result", result);
        return "../users/join_delete_ok.jsp";
    }
}