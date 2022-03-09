package sist.com.model;

import sist.com.controller.RequestMapping;
import java.util.*;
import javax.servlet.http.*;

import sist.com.vo.*;
import sist.com.dao.*;

public class MainModel {

    @RequestMapping("main/main.do")
    public String main_page(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String uid = (String) session.getAttribute("id");
        
        CompanyDAO c = new CompanyDAO();
        FavoriteDAO f = new FavoriteDAO();
        AdDAO a = new AdDAO();
        ReviewDAO r = new ReviewDAO();
        BoardDAO p = new BoardDAO();
        
        
        /*       Best 기업       */
        List<CompanyVO> company = c.bestCompanyList();
        List<String> review = new ArrayList<String>();

        for (CompanyVO i : company) {
            String bestreview = r.bestCompanyReviewList(i.getC_id());
            review.add(bestreview);
        }
        
        
        /*      대기업 리스트      */
        List<CompanyVO> bigCompany = c.bigCompanyList();
        
        
        /*       Best 공고       */
        List<AdVO> ad = a.bestAdList();
        List<String> adCname = new ArrayList<String>();
        List<Integer> favorite = new ArrayList<Integer>();
        
        for (AdVO i : ad) {
            String cname = c.companyName(i.getC_id());
            adCname.add(cname);
            
            if(uid != null) {
                int fav = f.favCount2(uid, i.getAd_id());
                favorite.add(fav);
                System.out.print(fav + " ");
            }
            
        }
        System.out.println();
        
        /*       마감 임박 공고       */
        List<AdVO> adEnd = a.adEndList();
        List<String> adEndCname = new ArrayList<String>();
        
        for (AdVO i: adEnd) {
            String cname = c.companyName(i.getC_id());
            adEndCname.add(cname);
        }
        
        
        List<BoardVO> freeBoardVisits = p.freeboardListByVisits();
        List<BoardVO> freeBoardReply = p.freeboardListByReply();
        
        /*       Cookie        */
        Cookie[] cookies = request.getCookies();
        List<String> adIds = new ArrayList<String>();
        List<AdVO> cookieList=null;
        
        for (int i = 0; i < cookies.length; i++) {
			if ("adview".equals(cookies[i].getName()) ) {
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
				cookieList = a.adCookieList(adIds);
			}
		}

        request.setAttribute("cookieList", cookieList);         
                
        request.setAttribute("company", company);
        request.setAttribute("review", review);
        
        request.setAttribute("bigCompany", bigCompany);
        
        request.setAttribute("ad", ad);
        request.setAttribute("adCname", adCname);
        request.setAttribute("favorite", favorite);       
        request.setAttribute("adEnd", adEnd);
        request.setAttribute("adEndCname", adEndCname);
        
        request.setAttribute("freeBoardVisits", freeBoardVisits);
        request.setAttribute("freeBoardReply", freeBoardReply);
        
        request.setAttribute("main_jsp", "../main/home.jsp");
        
        return "../main/main.jsp";
    }
}