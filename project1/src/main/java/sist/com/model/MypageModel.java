package sist.com.model;

import java.util.*;

import javax.servlet.http.*;

import sist.com.controller.RequestMapping;

import sist.com.dao.*;
import sist.com.vo.*;

public class MypageModel {

    // 헤더 - 마이페이지 이동
    @RequestMapping("mypage/mypage.do")
    public String mypage(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        
        if (id == null) {
            return "redirect: ../main/main.do";
        }
        
        UsersDAO dao = new UsersDAO();
        
        UsersVO user = dao.userDetail(id);
        
        request.setAttribute("user", user);
        request.setAttribute("tab", 1);
        request.setAttribute("main_jsp", "../mypage/mypage.jsp");
        request.setAttribute("content_jsp", "../mypage/info.jsp");
        return "../main/main.jsp";
    }
    
    // 마이페이지 - 회원정보 수정 이동
    @RequestMapping("mypage/update.do")
    public String user_update(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        
        if (id == null) {
            return "redirect: ../main/main.do";
        }
        
        UsersDAO dao = new UsersDAO();
        UsersVO user = dao.userDetail(id);
        
        request.setAttribute("tab", 2);
        request.setAttribute("user", user);
        request.setAttribute("main_jsp", "../mypage/mypage.jsp");
        request.setAttribute("content_jsp", "../mypage/update.jsp");
        return "../main/main.jsp";
    }
    
    // 마이페이지 - 비밀번호 수정 이동
    @RequestMapping("mypage/update_pwd.do")
    public String user_pwd_update(HttpServletRequest request, HttpServletResponse response) {
        
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        
        if (id == null) {
            return "redirect: ../main/main.do";
        }
        
        request.setAttribute("tab", 3);
        request.setAttribute("main_jsp", "../mypage/mypage.jsp");
        request.setAttribute("content_jsp", "../mypage/update_pwd.jsp");
        return "../main/main.jsp";
    }

    // 마이페이지 - 회원 탈퇴 이동
    @RequestMapping("mypage/delete.do")
    public String user_delete(HttpServletRequest request, HttpServletResponse response) {
        
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        
        if (id == null) {
            return "redirect: ../main/main.do";
        }
        
        request.setAttribute("tab", 4);
        request.setAttribute("main_jsp", "../mypage/mypage.jsp");
        request.setAttribute("content_jsp", "../mypage/delete.jsp");
        return "../main/main.jsp";
    }
    
    // 마이페이지 - 즐겨찾기 관리 이동
    @RequestMapping("mypage/favorite.do")
    public String favorite_List(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String uid = (String) session.getAttribute("id");
        
        if (uid == null) {
            return "redirect: ../main/main.do";
        }
        
        // 공고 번호, 공고정보
        FavoriteDAO dao = new FavoriteDAO();

        // 즐찾 공고번호 리스트
        List<Integer> flist = dao.favListData(uid);

        // 공고번호들의 공고정보리스트, 회사정보리스트
        List<AdVO> adList = new ArrayList<AdVO>();
        List<String> cList = new ArrayList<String>();

        AdDAO a = new AdDAO();
        AdVO vo;
        CompanyDAO c = new CompanyDAO();

        List<Integer> fid_list = new ArrayList<Integer>();

        // 공고번호 for문
        for (int i : flist) {
            vo = a.adDetail(i);
            adList.add(vo);
            cList.add(c.companyName(vo.getC_id()));
            fid_list.add(dao.favData(uid, i));
        }
        
        request.setAttribute("adList", adList);
        request.setAttribute("c_name", cList);
        request.setAttribute("fid_list", fid_list);
        
        request.setAttribute("tab", 5);
        request.setAttribute("main_jsp", "../mypage/mypage.jsp");
        request.setAttribute("content_jsp", "../mypage/favorite.jsp");
        return "../main/main.jsp";
    }
    
    // 마이페이지 - 회원정보 수정 결과
    @RequestMapping("mypage/update_ok.do")
    public String update_ok(HttpServletRequest request, HttpServletResponse response) {
        
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (Exception ex) {
        }
        
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String post = request.getParameter("post");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String question = request.getParameter("question");
        String answer = request.getParameter("answer");
        
        if (id == null) {
            return "redirect: ../main/main.do";
        }
        
        UsersVO user = new UsersVO();
        user.setU_id(id);
        user.setU_password(password);
        user.setU_email(email);
        user.setU_post(post);
        user.setU_address1(address1);
        user.setU_address2(address2);
        user.setU_question(question);
        user.setU_answer(answer);
        
        UsersDAO dao = new UsersDAO();
        
        String result = dao.userUpdate(user);
        
        request.setAttribute("result", result);
        return "../users/result.jsp";
    }

    // 마이페이지 - 비밀번호 수정 결과
    @RequestMapping("mypage/update_pwd_ok.do")
    public String update_pwd_ok(HttpServletRequest request, HttpServletResponse response) {
        
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (Exception ex) {
        }
        
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("new_password");
        
        if (id == null) {
            return "redirect: ../main/main.do";
        }
        
        UsersDAO dao = new UsersDAO();
        String result = dao.userPwdUpdate(id, password, newPassword);
        
        request.setAttribute("result", result);
        return "../users/result.jsp";
    }
    
    // 마이페이지 - 회원 탈퇴 결과
    @RequestMapping("mypage/delete_ok.do")
    public String memberJoinDeleteOk(HttpServletRequest request, HttpServletResponse response) {
        
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        String password = request.getParameter("password");
        
        if (id == null) {
            return "redirect: ../main/main.do";
        }

        UsersDAO dao = new UsersDAO();
        String result = dao.userDelete(id, password);
        
        if (result.equals("yes")) {
            session.invalidate();
        }
        
        request.setAttribute("result", result);
        return "../users/result.jsp";
    }
}