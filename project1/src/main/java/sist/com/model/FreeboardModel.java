package sist.com.model;

import sist.com.controller.RequestMapping;
import java.util.*;
import javax.servlet.http.*;

import sist.com.vo.*;
import sist.com.dao.*;

public class FreeboardModel {

    @RequestMapping("freeboard/freeboard.do")
    public String freeboard_page(HttpServletRequest request, HttpServletResponse response) {

        String page = request.getParameter("page");
        if (page == null)
            page = "1";

        int curPage = Integer.parseInt(page);

        BoardDAO dao = new BoardDAO();
        List<BoardVO> board = dao.freeboardList(curPage);

        int totalPage = dao.freeboardTotalPage();
        final int BLOCK = 10;
        int startPage = ((curPage - 1) / BLOCK * BLOCK) + 1;
        int endPage = ((curPage - 1) / BLOCK * BLOCK) + BLOCK;
        if (endPage > totalPage) {
            endPage = totalPage;
        }
        request.setAttribute("curPage", curPage);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("board", board);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("main_jsp", "../freeboard/freeboard.jsp");
        return "../main/main.jsp";
    }

    @RequestMapping("freeboard/detail.do")
    public String freeboard_detail(HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter("bid");
        BoardDAO dao = new BoardDAO();
        BoardVO detail = dao.freeboardDetail(Integer.parseInt(id));
        request.setAttribute("detail", detail);
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

        BoardVO vo = new BoardVO();
        vo.setU_id(uid);

        vo.setBoard_category(category);
        vo.setBoard_title(title);
        vo.setBoard_content(content);

        BoardDAO dao = new BoardDAO();
        dao.freeboardInsert(vo);

        return "redirect:../freeboard/freeboard.do";
    }
}