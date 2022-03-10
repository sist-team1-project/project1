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
        cdao.updateCompanyVisits(Integer.parseInt(cid)); // 조회수 증가
        CompanyVO company = cdao.companyDetail(Integer.parseInt(cid)); // 기업 정보

        AdDAO a = new AdDAO();
        List<AdVO> adlist = a.companyAdList(Integer.parseInt(cid)); // 진행중인 공고
        
        ReviewDAO dao = new ReviewDAO();
        List<ReviewVO> review = dao.reviewList(Integer.parseInt(cid)); // 리뷰 목록

        request.setAttribute("company", company);
        request.setAttribute("adlist", adlist);
        request.setAttribute("review", review);
        
        request.setAttribute("main_jsp", "../company/company.jsp");
        
        return "../main/main.jsp";
    }
}