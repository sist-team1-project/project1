package sist.com.model;

import java.util.*;

import javax.servlet.http.*;
import sist.com.controller.RequestMapping;

import sist.com.vo.*;
import sist.com.dao.*;

public class SearchModel {
    
	@RequestMapping("search/searchcompany.do")
    public String main_page(HttpServletRequest request) {

		CompanyDAO c = new CompanyDAO();
        AdDAO a = new AdDAO();
        ReviewDAO r = new ReviewDAO();
        PostDAO p = new PostDAO();
		
        request.setAttribute("main_jsp", "../search/search_company.jsp");
        
        List<CompanyVO> company = c.bestCompanyList();
        List<String> review = new ArrayList<String>();

        for (int i = 0; i < company.size(); i++) {
            String bestreview = r.bestCompanyReviewList(company.get(i).getC_id());
            
            // 리뷰가 없을 경우
            if (bestreview.isEmpty()) {
                bestreview = "유저들의 리뷰를 기다리고 있습니다.";
            }
            review.add(bestreview);
        }
        request.setAttribute("company", company);
        request.setAttribute("review", review);
        
        return "../main/main.jsp";
    }
}