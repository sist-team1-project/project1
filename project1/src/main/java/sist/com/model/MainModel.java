package sist.com.model;

import sist.com.controller.RequestMapping;
import java.util.*;
import javax.servlet.http.*;

import sist.com.vo.*;
import sist.com.dao.*;

public class MainModel {

    @RequestMapping("main/main.do")
    public String main_page(HttpServletRequest request, HttpServletResponse response) {

        CompanyDAO c = new CompanyDAO();
        AdDAO a = new AdDAO();
        ReviewDAO r = new ReviewDAO();
        BoardDAO p = new BoardDAO();
        
        /*       Best 기업       */
        List<CompanyVO> company = c.bestCompanyList();
        List<String> review = new ArrayList<String>();

        for (int i = 0; i < company.size(); i++) {
            String bestreview = r.bestCompanyReviewList(company.get(i).getC_id());
            review.add(bestreview);
        }
        
        List<CompanyVO> bigCompany = c.bigCompanyList();
        
        /*       Best 공고       */
        List<AdVO> ad = a.bestAdList();
        List<String> adCname = new ArrayList<String>();
        
        for (int i = 0; i < ad.size(); i++) {
            String cname = c.companyName(ad.get(i).getC_id());
            adCname.add(cname);
        }
        
        
        /*       마감 임박 공고       */
        List<AdVO> adEnd = a.adEndList();
        List<String> adEndCname = new ArrayList<String>();
        
        for (int i = 0; i < adEnd.size(); i++) {
            String cname = c.companyName(ad.get(i).getC_id());
            adEndCname.add(cname);
        }
        
        
        List<BoardVO> freeBoardVisits = p.freeboardListByVisits();
        
        /*       Cookie        */
        Cookie[] cookies = request.getCookies();
        List<String> adIds = new ArrayList<String>();
        
        if (cookies != null) {
			for (Cookie cook : cookies) {
				String name = cook.getName();
				String value = cook.getValue();
				
				if (name.equals("adview")) {
					System.out.println(value);
					
					for (String id : value.split("%2C")) {
						adIds.add(id);
					}
				}
			}
		}
        
        List<AdVO> cookieList = a.adCookieList(adIds);
        request.setAttribute("cookieList", cookieList);         
                
        request.setAttribute("company", company);
        request.setAttribute("review", review);
        
        request.setAttribute("bigCompany", bigCompany);
        
        request.setAttribute("ad", ad);
        request.setAttribute("adCname", adCname);
        request.setAttribute("adEnd", adEnd);
        request.setAttribute("adEndCname", adEndCname);
        
        request.setAttribute("freeBoardVisits", freeBoardVisits);
        
        request.setAttribute("main_jsp", "../main/home.jsp");
        
        return "../main/main.jsp";
    }
}