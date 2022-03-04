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
}