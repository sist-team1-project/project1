package com.sist.model;

import com.sist.controller.*;
import java.util.*;
import javax.servlet.http.*;

import com.sist.dao.*;
import com.sist.vo.*;

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
        List<Map<String,Object>> company = c.bestCompanyList();
                
        /*      대기업 리스트      */
        List<CompanyVO> bigCompany = c.bigCompanyList();
        
        /*       Best 공고       */
        List<Map<String,Object>> ad = a.bestAdList();
        List<Integer> favorite = new ArrayList<Integer>();
        
        for (Map<String,Object> i : ad) {
            int fav = f.favCount(uid, (int) i.get("ad_id"));
            favorite.add(fav);
        }
        
        /*       마감 임박 공고       */
        List<Map<String,Object>> adEnd = a.adEndList();
        List<Integer> favorite2 = new ArrayList<Integer>();
        
        for (Map<String,Object> i: adEnd) {
            int fav = f.favCount(uid, (int) i.get("ad_id"));
            favorite2.add(fav);
        }
        
        List<BoardVO> freeBoardVisits = p.freeboardListByVisits();
        List<BoardVO> freeBoardReply = p.freeboardListByReply();
        
        /*       Cookie        */
        Cookie[] cookies = request.getCookies();
        List<String> adIds = new ArrayList<String>();
        List<AdVO> cookieList = null;
        
        for (int i = 0; i < cookies.length; i++) {
			if ("adview".equals(cookies[i].getName()) ) {
				for (Cookie cook : cookies) {
					String name = cook.getName();
					String value = cook.getValue();
					
					if (name.equals("adview")) {
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
        
        request.setAttribute("bigCompany", bigCompany);
        
        request.setAttribute("ad", ad);
        request.setAttribute("favorite", favorite);       
        
        request.setAttribute("adEnd", adEnd);
        request.setAttribute("favorite2", favorite2);   
        
        request.setAttribute("freeBoardVisits", freeBoardVisits);
        request.setAttribute("freeBoardReply", freeBoardReply);
        
        request.setAttribute("main_jsp", "../main/home.jsp");
        
        return "../main/main.jsp";
    }
}