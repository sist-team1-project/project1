package sist.com.model;

import sist.com.controller.RequestMapping;
import java.util.*;
import javax.servlet.http.*;
import sist.com.vo.*;
import sist.com.dao.*;

public class CompanyModel {
    
    @RequestMapping("company/company.do")
    public String company_page(HttpServletRequest request, HttpServletResponse response) {
        String cid = request.getParameter("cid");
        
        CompanyDAO cdao = new CompanyDAO();
        CompanyVO company = cdao.companyDetail(Integer.parseInt(cid));

        AdDAO a = new AdDAO();
        List<AdVO> adlist = a.companyAdList(Integer.parseInt(cid));
        
        ReviewDAO rdao = new ReviewDAO();
        List<ReviewVO> review = rdao.reviewList(Integer.parseInt(cid));

        request.setAttribute("company", company);
        request.setAttribute("adlist", adlist);
        request.setAttribute("review", review);
        
        request.setAttribute("main_jsp", "../company/company.jsp");
        
        return "../main/main.jsp";
    }
    
    @RequestMapping("company/review.do")
    public String company_review(HttpServletRequest request, HttpServletResponse response) {
        String cid = request.getParameter("cid");
        
        ReviewDAO rdao = new ReviewDAO();
        List<ReviewVO> review = rdao.reviewList(Integer.parseInt(cid));
        
        request.setAttribute("review", review);
        
        return "../company/review.jsp";
    }
    
    @RequestMapping("company/review_insert.do")
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
        
        ReviewDAO rdao = new ReviewDAO();
        rdao.reviewInsert(vo); 
    }
    
    @RequestMapping("company/review_delete.do")
    public void company_review_delete(HttpServletRequest request, HttpServletResponse response) {
        String rid = request.getParameter("rid");
        
        try {
            request.setCharacterEncoding("UTF-8");
        } catch(Exception ex) {}
        
        
        ReviewDAO rdao = new ReviewDAO();
        rdao.reviewDelete(Integer.parseInt(rid));
    }
}