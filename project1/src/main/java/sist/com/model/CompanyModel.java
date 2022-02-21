package sist.com.model;

import java.util.*;

import javax.servlet.http.*;
import sist.com.controller.RequestMapping;

import sist.com.vo.*;
import sist.com.dao.*;

public class CompanyModel {
    
    @RequestMapping("company/company.do")
    public String main_page(HttpServletRequest request) {
        String cid = request.getParameter("cid");
        
        CompanyDAO cdao = new CompanyDAO();
        CompanyVO company = cdao.getCompanyDetail(Integer.parseInt(cid));
        String addr = company.getC_address();
        addr = addr.substring(addr.indexOf(")") + 2);
        company.setC_address(addr);
        
        AdDAO a = new AdDAO();
        List<AdVO> ad = a.getAdDetailByCid(Integer.parseInt(cid));
        for(int i = 0; i < ad.size(); i++) {
            if(ad.get(i).getAd_end() == null) {
                ad.get(i).setAd_end("채용시까지");
            }
        }
        
        ReviewDAO rdao = new ReviewDAO();
        List<ReviewVO> review = rdao.getReviewDetail(Integer.parseInt(cid));
        
        
        request.setAttribute("company", company);
        request.setAttribute("ad", ad);
        request.setAttribute("review", review);
        
        request.setAttribute("main_jsp", "../company/company.jsp");
        return "../main/main.jsp";
    }
}