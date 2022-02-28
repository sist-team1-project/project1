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
        if(page==null) page = "1";
        
        int curPage = Integer.parseInt(page);
        
        BoardDAO dao = new BoardDAO();
        List<BoardVO> board = dao.freeboardList(curPage);
        
        for(BoardVO p : board) {
            String date = p.getBoard_date();
            p.setBoard_date(date.substring(0,date.indexOf(" ")));
        }
        
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
}
