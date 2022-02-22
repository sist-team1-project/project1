package sist.com.model;

import sist.com.controller.RequestMapping;
import java.util.*;
import javax.servlet.http.*;

import sist.com.vo.*;
import sist.com.dao.*;

public class AdModel {
    
    @RequestMapping("ad/ad.do")
    public String ad_page(HttpServletRequest request, HttpServletResponse response) {
        String cid = request.getParameter("cid");
        String adid = request.getParameter("adid");
        
        AdDAO a = new AdDAO();
        AdVO ad = a.getAdDetail(Integer.parseInt(adid));
        
        if (ad.getAd_end() == null) {
            ad.setAd_end("채용시까지");
        }
        
        CompanyDAO c = new CompanyDAO();
        CompanyVO company = c.getCompanyDetail(Integer.parseInt(cid));
        
        request.setAttribute("ad", ad);
        request.setAttribute("company", company);
        request.setAttribute("main_jsp", "../ad/ad.jsp");
        return "../main/main.jsp";
    }
}
