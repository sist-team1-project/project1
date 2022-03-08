package sist.com.model;

import sist.com.controller.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.*;

import sist.com.vo.*;
import sist.com.dao.*;

// 자유 게시판
public class FreeboardModel {
    
    // 자유게시판 - 페이지 이동
    @RequestMapping("freeboard/freeboard.do")
    public String freeboard(HttpServletRequest request, HttpServletResponse response) {
        
        String page = request.getParameter("page");
        
        if (page == null) page = "1";
        
        int curPage = Integer.parseInt(page);
        
        BoardDAO bdao = new BoardDAO();
        List<BoardVO> board = bdao.freeboardList(curPage, 1);
        
        // 댓글 수 가져오기
        ReplyDAO rdao = new ReplyDAO();
        List<Integer> rcount = new ArrayList<Integer>();
        for (BoardVO b : board) {
            rcount.add(rdao.replyCount(b.getBoard_id()));
        }
        
        int totalPage = bdao.freeboardTotalPage("", 1);
        
        // 페이지
        final int BLOCK = 10;
        int startPage = ((curPage - 1) / BLOCK * BLOCK) + 1;
        int endPage = ((curPage - 1) / BLOCK * BLOCK) + BLOCK;
        if (endPage > totalPage) {
            endPage = totalPage;
        }
        
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        
        request.setAttribute("curPage", curPage);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("board", board);
        request.setAttribute("rcount", rcount);
        request.setAttribute("today", today);
        request.setAttribute("main_jsp", "../freeboard/freeboard.jsp");
        return "../main/main.jsp";
    }
    
    // 내 게시물 관리 - 페이지 이동
    @RequestMapping("freeboard/mypost.do")
    public String freeboard_mypost(HttpServletRequest request, HttpServletResponse response) {
        
        String page = request.getParameter("page");
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        
        if (page == null) page = "1";
        
        int curPage = Integer.parseInt(page);
        
        BoardDAO bdao = new BoardDAO();
        List<BoardVO> board = bdao.freeboardMyList(curPage, id, 1);
        
        // 댓글 수 가져오기
        ReplyDAO rdao = new ReplyDAO();
        List<Integer> rcount = new ArrayList<Integer>();
        for (BoardVO b : board) {
            rcount.add(rdao.replyCount(b.getBoard_id()));
        }
        
        int totalPage = bdao.freeboardTotalPage(id, 2);
        
        // 페이지
        final int BLOCK = 10;
        int startPage = ((curPage - 1) / BLOCK * BLOCK) + 1;
        int endPage = ((curPage - 1) / BLOCK * BLOCK) + BLOCK;
        if (endPage > totalPage) {
            endPage = totalPage;
        }
        request.setAttribute("curPage", curPage);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("board", board);
        request.setAttribute("rcount", rcount);
        request.setAttribute("main_jsp", "../freeboard/mypost.jsp");
        return "../main/main.jsp";
    }
    
    // 내 댓글 관리 - 페이지 이동
    @RequestMapping("freeboard/myreply.do")
    public String freeboard_myreply(HttpServletRequest request, HttpServletResponse response) {
        
        String page = request.getParameter("page");
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        
        if (page == null) page = "1";
        
        int curPage = Integer.parseInt(page);
        
        BoardDAO bdao = new BoardDAO();
        List<BoardVO> board = bdao.freeboardMyList(curPage, id, 2);
        
        // 댓글 수 가져오기
        ReplyDAO rdao = new ReplyDAO();
        List<Integer> rcount = new ArrayList<Integer>();
        for (BoardVO b : board) {
            rcount.add(rdao.replyCount(b.getBoard_id()));
        }
        
        int totalPage = bdao.freeboardTotalPage(id, 3);
        
        // 페이지
        final int BLOCK = 10;
        int startPage = ((curPage - 1) / BLOCK * BLOCK) + 1;
        int endPage = ((curPage - 1) / BLOCK * BLOCK) + BLOCK;
        if (endPage > totalPage) {
            endPage = totalPage;
        }
        request.setAttribute("curPage", curPage);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("board", board);
        request.setAttribute("rcount", rcount);
        request.setAttribute("main_jsp", "../freeboard/myreply.jsp");
        return "../main/main.jsp";
    }
    
    // 게시물 - 상세 정보 출력
    @RequestMapping("freeboard/detail.do")
    public String freeboard_detail(HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter("bid");

        BoardDAO bdao = new BoardDAO();
        BoardVO detail = bdao.freeboardDetail(Integer.parseInt(id));

        request.setAttribute("detail", detail);
 
        request.setAttribute("main_jsp", "../freeboard/detail.jsp");
        return "../main/main.jsp";
    }
    
    // 게시물 - 작성 페이지 이동
    @RequestMapping("freeboard/insert.do")
    public String freeboard_insert(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("main_jsp", "../freeboard/insert.jsp");
        return "../main/main.jsp";
    }
    
    // 게시물 - 작성
    @RequestMapping("freeboard/insert_ok.do")
    public String freeboard_insert_ok(HttpServletRequest request, HttpServletResponse response) {

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (Exception ex) {
        }

        String uid = request.getParameter("uid");
        String category = request.getParameter("category");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        BoardVO vo = new BoardVO();
        vo.setU_id(uid);

        vo.setBoard_category(category);
        vo.setBoard_title(title);
        vo.setBoard_content(content);

        BoardDAO dao = new BoardDAO();
        dao.freeboardInsert(vo);

        return "redirect:../freeboard/freeboard.do";
    }
    
    // 게시물 - 수정 페이지 이동
    @RequestMapping("freeboard/update.do")
    public String freeboard_update(HttpServletRequest request, HttpServletResponse response) {

        String bid = request.getParameter("bid");

        BoardDAO dao = new BoardDAO();
        BoardVO detail = dao.freeboardUpdateDetail(Integer.parseInt(bid));

        request.setAttribute("detail", detail);
        request.setAttribute("main_jsp", "../freeboard/update.jsp");

        return "../main/main.jsp";
    }
    
    // 게시물 - 수정
    @RequestMapping("freeboard/update_ok.do")
    public String freeboard_update_ok(HttpServletRequest request, HttpServletResponse response) {

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (Exception ex) {
        }

        String bid = request.getParameter("bid");
        String category = request.getParameter("category");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        
        BoardVO vo = new BoardVO();

        vo.setBoard_id(Integer.parseInt(bid));
        vo.setBoard_category(category);
        vo.setBoard_title(title);
        vo.setBoard_content(content);

        BoardDAO dao = new BoardDAO();
        dao.freeboardUpdate(vo);

        return "redirect:../freeboard/detail.do?bid=" + bid;
    }
    
    // 게시물 - 삭제
    @RequestMapping("freeboard/delete.do")
    public String freeboard_delete_ok(HttpServletRequest request, HttpServletResponse response) {

        String bid = request.getParameter("bid");

        BoardDAO dao = new BoardDAO();
        dao.freeboardDelete(Integer.parseInt(bid));

        return "redirect:../freeboard/freeboard.do";
    }
    
    // 내 게시물 관리 - 다중 게시물 삭제
    @RequestMapping("freeboard/delete_multi.do")
    public String freeboard_delete_multi_ok(HttpServletRequest request, HttpServletResponse response) {

        String[] bid = request.getParameterValues("bid");
        BoardDAO dao = new BoardDAO();
        
        for(int i = 0; i < bid.length; i++) {
            dao.freeboardDelete(Integer.parseInt(bid[i]));
        }
        return "../freeboard/freeboard.do";
    }
}