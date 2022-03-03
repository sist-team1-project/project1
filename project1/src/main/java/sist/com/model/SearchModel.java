package sist.com.model;

import java.util.*;

import javax.servlet.http.*;
import sist.com.controller.RequestMapping;

import sist.com.vo.*;
import sist.com.dao.*;

public class SearchModel {
    
    @RequestMapping("search/searchcompany.do")
    public String search_company_page(HttpServletRequest request, HttpServletResponse response) {
        
        String search = request.getParameter("search");
        CompanyDAO c = new CompanyDAO();
        ReviewDAO r = new ReviewDAO();
        
        if(search != null) {
            
            
            List<CompanyVO> company = c.companySearchList(search);
            List<Integer> reviews = new ArrayList<Integer>();
            for(CompanyVO i : company) {
                reviews.add(i.getC_id());
            }
            request.setAttribute("company", company);
            request.setAttribute("reviews", reviews);
        }
        
        /*       Best 기업       */
        List<CompanyVO> company2 = c.bestCompanyList();
        List<String> reviews2 = new ArrayList<String>();

        for(CompanyVO j : company2) {
            String bestreview = r.bestCompanyReviewList(j.getC_id());
            
            // 리뷰가 없을 경우
            if (bestreview.isEmpty()) {
                bestreview = "유저들의 리뷰를 기다리고 있습니다.";
            }
            reviews2.add(bestreview);
        }
        
        request.setAttribute("company2", company2);
        request.setAttribute("reviews2", reviews2);
        
        request.setAttribute("main_jsp", "../search/search_company.jsp");
        return "../main/main.jsp";
    }
    
    
    
    @RequestMapping("search/searchad.do")
    public String search_ad_page(HttpServletRequest request, HttpServletResponse response) {
    	String search = request.getParameter("search");
    	
    	AdDAO a = new AdDAO();
    	
    	if(search != null) {
    		
    		List<AdVO> ad = a.adSearchList(search);
    		request.setAttribute("search", search);
    	}
    	request.setAttribute("main_jsp", "../search/search.jsp");
    	return "../main/main.jsp";
    }
}