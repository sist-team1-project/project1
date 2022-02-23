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
        
        PostDAO dao = new PostDAO();
        List<PostVO> post = dao.getFreeboardList(curPage);
        for(PostVO p : post) {
            String date = p.getPost_date();
            p.setPost_date(date.substring(0,date.indexOf(" ")));
        }
        
        request.setAttribute("post", post);
        request.setAttribute("main_jsp", "../freeboard/freeboard.jsp");
        return "../main/main.jsp";
    }
}
