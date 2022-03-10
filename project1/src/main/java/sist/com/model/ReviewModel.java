package sist.com.model;

import sist.com.controller.RequestMapping;
import java.util.*;
import javax.servlet.http.*;
import sist.com.vo.*;
import sist.com.dao.*;

public class ReviewModel {
    
    @RequestMapping("review/review.do")
    public String company_review(HttpServletRequest request, HttpServletResponse response) {
        
        String cid = request.getParameter("cid");
        
        ReviewDAO dao = new ReviewDAO();
        List<ReviewVO> review = dao.reviewList(Integer.parseInt(cid));
        
        request.setAttribute("review", review);
        
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