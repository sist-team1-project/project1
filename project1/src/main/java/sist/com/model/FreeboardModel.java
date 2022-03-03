package sist.com.model;

import sist.com.controller.RequestMapping;
import java.util.*;
import javax.servlet.http.*;

import sist.com.vo.*;
import sist.com.dao.*;

public class FreeboardModel {
    
    @RequestMapping("freeboard/freeboard.do")
    public String freeboard(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("main_jsp", "../freeboard/freeboard.jsp");
        return "../main/main.jsp";
    }
    
    @RequestMapping("freeboard/freeboardlist.do")
    public String freeboard_list(HttpServletRequest request, HttpServletResponse response) {
        
        String page = request.getParameter("page");
        String sub = request.getParameter("sub");
        
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        
        
        if (page == null)
            page = "1";
             
        int curPage = Integer.parseInt(page);
        
        
        // 게시물 목록 가져오기
        BoardDAO bdao = new BoardDAO();
        List<BoardVO> board = new ArrayList<BoardVO>();
        int totalPage = 1;
        
        if (sub == null) { // 자유게시판
            board = bdao.freeboardList(curPage);
            totalPage = bdao.freeboardTotalPage(0,"");
        } else if (sub.equals("1")) { // 내가 쓴 글 관리
            board = bdao.freeboardMyList(curPage, 1, id);
            totalPage = bdao.freeboardTotalPage(1,id);
        } else if (sub.equals("2")) { // 내가 쓴 댓글 관리
            board = bdao.freeboardMyList(curPage, 2, id);
            totalPage = bdao.freeboardTotalPage(2,id);
        }
        
        // 댓글 수 가져오기
        ReplyDAO rdao = new ReplyDAO();
        List<Integer> rcount = new ArrayList<Integer>();
        for (BoardVO b : board) {
            rcount.add(rdao.replyCount(b.getBoard_id()));
        }
        
        // 페이지
        final int BLOCK = 10;
        int startPage = ((curPage - 1) / BLOCK * BLOCK) + 1;
        int endPage = ((curPage - 1) / BLOCK * BLOCK) + BLOCK;
        if (endPage > totalPage) {
            endPage = totalPage;
        }
        request.setAttribute("sub", sub);
        request.setAttribute("curPage", curPage);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("board", board);
        request.setAttribute("rcount", rcount);
        
        return "../freeboard/list.jsp";
    }
    
    @RequestMapping("freeboard/detail.do")
    public String freeboard_detail(HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter("bid");

        BoardDAO bdao = new BoardDAO();
        BoardVO detail = bdao.freeboardDetail(Integer.parseInt(id));

        ReplyDAO rdao = new ReplyDAO();
        List<ReplyVO> reply = rdao.replyList(Integer.parseInt(id));

        request.setAttribute("detail", detail);
        request.setAttribute("reply", reply);
        request.setAttribute("main_jsp", "../freeboard/detail.jsp");
        return "../main/main.jsp";
    }

    @RequestMapping("freeboard/insert.do")
    public String freeboard_insert(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("main_jsp", "../freeboard/insert.jsp");
        return "../main/main.jsp";
    }

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
        content = content.replace("\n", "<br>");

        BoardVO vo = new BoardVO();
        vo.setU_id(uid);

        vo.setBoard_category(category);
        vo.setBoard_title(title);
        vo.setBoard_content(content);

        BoardDAO dao = new BoardDAO();
        dao.freeboardInsert(vo);

        return "redirect:../freeboard/freeboard.do";
    }

    @RequestMapping("freeboard/update.do")
    public String freeboard_update(HttpServletRequest request, HttpServletResponse response) {

        String bid = request.getParameter("bid");

        BoardDAO dao = new BoardDAO();
        BoardVO detail = dao.freeboardUpdateDetail(Integer.parseInt(bid));

        request.setAttribute("detail", detail);
        request.setAttribute("main_jsp", "../freeboard/update.jsp");

        return "../main/main.jsp";
    }

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
        content = content.replace("\n", "<br>");

        BoardVO vo = new BoardVO();

        vo.setBoard_id(Integer.parseInt(bid));
        vo.setBoard_category(category);
        vo.setBoard_title(title);
        vo.setBoard_content(content);

        BoardDAO dao = new BoardDAO();
        dao.freeboardUpdate(vo);

        return "redirect:../freeboard/freeboard.do";
    }

    @RequestMapping("freeboard/delete_ok.do")
    public String freeboard_delete_ok(HttpServletRequest request, HttpServletResponse response) {

        String bid = request.getParameter("bid");

        BoardDAO dao = new BoardDAO();
        dao.freeboardDelete(Integer.parseInt(bid));

        return "redirect:../freeboard/freeboard.do";
    }

    @RequestMapping("freeboard/reply_ok.do")
    public String freeboard_reply_ok(HttpServletRequest request, HttpServletResponse response) {

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (Exception ex) {
        }

        String bid = request.getParameter("bid");
        String uid = request.getParameter("uid");
        String content = request.getParameter("content");
        content = content.replace("\n", "<br>");

        ReplyVO vo = new ReplyVO();
        vo.setBoard_id(Integer.parseInt(bid));
        vo.setU_id(uid);
        vo.setReply_content(content);

        ReplyDAO dao = new ReplyDAO();
        dao.replyInsert(vo);

        return "redirect:../freeboard/detail.do?bid=" + bid;
    }

    @RequestMapping("freeboard/reply_delete_ok.do")
    public String freeboard_reply_delete_ok(HttpServletRequest request, HttpServletResponse response) {

        String bid = request.getParameter("bid");
        String rid = request.getParameter("rid");

        ReplyDAO dao = new ReplyDAO();
        dao.freeboardReplyDelete(Integer.parseInt(rid));

        return "redirect:../freeboard/detail.do?bid=" + bid;
    }
}