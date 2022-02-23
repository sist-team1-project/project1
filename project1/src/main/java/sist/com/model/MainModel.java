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
        PostDAO p = new PostDAO();
        
        /*       Best 기업       */
        List<CompanyVO> company = c.getBestCompanyList();
        List<String> review = new ArrayList<String>();

        for (int i = 0; i < company.size(); i++) {
            String bestreview = r.bestCompanyReviewList(company.get(i).getC_id());
            
            // 리뷰가 없을 경우
            if (bestreview.isEmpty()) {
                bestreview = "유저들의 리뷰를 기다리고 있습니다.";
            }
            review.add(bestreview);
        }
        
        List<CompanyVO> bigCompany = c.getBigCompanyList();
        
        /*       Best 공고       */
        List<AdVO> ad = a.bestAdList();
        List<CompanyVO> adCompany = new ArrayList<CompanyVO>();
        
        for (int i = 0; i < ad.size(); i++) {
            CompanyVO info = c.getCompanyDetail(ad.get(i).getC_id());

            // 마감 날짜가 없을 경우
            if (ad.get(i).getAd_end() == null) {
                ad.get(i).setAd_end("채용시까지");
            }
            
            String we = ad.get(i).getAd_we();
            if(we.startsWith("경력")) {
                ad.get(i).setAd_we(we.substring(0,we.indexOf(" (")));
            }
            
            String addr = info.getC_address();
            addr = addr.substring(addr.indexOf(")") + 2);
            addr = addr.substring(0,addr.indexOf(" ", addr.indexOf(" ")+1));
            info.setC_address(addr);
            adCompany.add(info);
        }
        
        
        /*       마감 임박 공고       */
        List<AdVO> adEnd = a.AdEndList();
        List<CompanyVO> adEndCompany = new ArrayList<CompanyVO>();
        
        for (int i = 0; i < adEnd.size(); i++) {
            CompanyVO info = c.getCompanyDetail(adEnd.get(i).getC_id());
            
            String we = adEnd.get(i).getAd_we();
            if(we.startsWith("경력")) {
                adEnd.get(i).setAd_we(we.substring(0,we.indexOf(" (")));
            }
            
            String addr = info.getC_address();
            addr = addr.substring(addr.indexOf(")") + 2);
            addr = addr.substring(0,addr.indexOf(" ", addr.indexOf(" ")+1));
            info.setC_address(addr);
            adEndCompany.add(info);
        }
        
        
        List<PostVO> freeBoardVisits = p.getFreeboardListByVisits();
        
        
        request.setAttribute("company", company);
        request.setAttribute("review", review);
        
        request.setAttribute("bigCompany", bigCompany);
        
        request.setAttribute("ad", ad);
        request.setAttribute("adCompany", adCompany);
        request.setAttribute("adEnd", adEnd);
        request.setAttribute("adEndCompany", adEndCompany);
        
        request.setAttribute("freeBoardVisits", freeBoardVisits);
        
        request.setAttribute("main_jsp", "../main/home.jsp");
        
        return "../main/main.jsp";
    }
}