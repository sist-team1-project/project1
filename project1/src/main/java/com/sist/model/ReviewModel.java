package com.sist.model;

import com.sist.controller.*;

import java.util.*;
import javax.servlet.http.*;

import com.sist.dao.*;
import com.sist.vo.*;

public class ReviewModel {
    
    @RequestMapping("review/review.do")
    public String company_review(HttpServletRequest request, HttpServletResponse response) {
        
        String cid = request.getParameter("cid");
        String page = request.getParameter("page");
        
        if (page == null) page = "1";
        
        int curPage = Integer.parseInt(page);
        
        ReviewDAO dao = new ReviewDAO();
        List<ReviewVO> review = dao.reviewList(Integer.parseInt(cid), Integer.parseInt(page));
        int totalPage = dao.reviewTotalPage(Integer.parseInt(cid));
        
        // 페이지
        final int BLOCK = 5;
        int startPage = ((curPage - 1) / BLOCK * BLOCK) + 1;
        int endPage = ((curPage - 1) / BLOCK * BLOCK) + BLOCK;
        if (endPage > totalPage) {
            endPage = totalPage;
        }
        request.setAttribute("review", review);
        request.setAttribute("curPage", curPage);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("cid", cid);
        
        return "../company/review.jsp";
    }
    
    @RequestMapping("review/insert.do")
    public void company_review_insert(HttpServletRequest request, HttpServletResponse response) {
        
        String uid = request.getParameter("uid");
        String cid = request.getParameter("cid");
        String content = request.getParameter("content");
        String goodbad = request.getParameter("goodbad");
        String job = request.getParameter("job");
        
        
        try {
        	request.setCharacterEncoding("UTF-8");
        } catch(Exception ex) {}
        
        ReviewVO vo = new ReviewVO();
        vo.setU_id(uid);
        vo.setC_id(Integer.parseInt(cid));
        vo.setReview_goodbad(Integer.parseInt(goodbad));
        vo.setReview_job(job);
        vo.setReview_content(content);
        
        ReviewDAO dao = new ReviewDAO();
        dao.reviewInsert(vo); 
    }
    
    @RequestMapping("review/delete.do")
    public void company_review_delete(HttpServletRequest request, HttpServletResponse response) {
        
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        String rid = request.getParameter("rid");
        
        ReviewDAO dao = new ReviewDAO();
        if(dao.checkUser(id, Integer.parseInt(rid)) == true) {
            dao.reviewDelete(Integer.parseInt(rid));
        }
    }
    
    @RequestMapping("review/update.do")
    public void company_review_update(HttpServletRequest request, HttpServletResponse response) {

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (Exception ex) {
        }
        
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        String rid = request.getParameter("rid");
        String goodbad = request.getParameter("goodbad");
        String job = request.getParameter("job");
        String content = request.getParameter("content");
        
        ReviewDAO dao = new ReviewDAO();
        if(dao.checkUser(id, Integer.parseInt(rid)) == true) {
            dao.reviewUpdate(Integer.parseInt(rid), Integer.parseInt(goodbad), job, content);
        }
    }
}