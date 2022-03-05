package sist.com.model;

import java.util.*;

import javax.servlet.http.*;
import sist.com.controller.RequestMapping;

import sist.com.vo.*;
import sist.com.dao.*;

public class SearchModel {

    @RequestMapping("search/searchcompany.do")
    public String search_company(HttpServletRequest request, HttpServletResponse response) {

        /*       Best 기업       */
        CompanyDAO c = new CompanyDAO();
        ReviewDAO r = new ReviewDAO();

        List<CompanyVO> company = c.bestCompanyList();
        List<String> review = new ArrayList<String>();

        for (int i = 0; i < company.size(); i++) {
            String bestreview = r.bestCompanyReviewList(company.get(i).getC_id());
            review.add(bestreview);
        }

        request.setAttribute("company", company);
        request.setAttribute("review", review);

        request.setAttribute("main_jsp", "../search/search_company.jsp");
        return "../main/main.jsp";
    }

    @RequestMapping("search/searchcompany_result.do")
    public String search_company_result(HttpServletRequest request, HttpServletResponse response) {

        String search = request.getParameter("search");
        CompanyDAO c = new CompanyDAO();

        List<CompanyVO> c_result = c.companySearchList(search);
        List<Integer> r_result = new ArrayList<Integer>();
        for (CompanyVO i : c_result) {
            r_result.add(i.getC_id());
        }

        request.setAttribute("c_result", c_result);
        request.setAttribute("r_result", r_result);
        ;
        return "../search/search_company_result.jsp";
    }

    @RequestMapping("search/searchad.do")
    public String search_ad(HttpServletRequest request, HttpServletResponse response) {
        String search = request.getParameter("search");

        AdDAO a = new AdDAO();

        request.setAttribute("search", search);

        request.setAttribute("main_jsp", "../search/search_ad.jsp");
        return "../main/main.jsp";
    }

    @RequestMapping("search/searchad_result.do")
    public String search_ad_result(HttpServletRequest request, HttpServletResponse response) {
        String keyword = request.getParameter("keyword");
        String address = request.getParameter("address");
        String we = request.getParameter("we");
        String edu = request.getParameter("edu");
        String size = request.getParameter("size");
        System.out.println(size);
        if (address.equals("시/도 선택 ")) {
            address = "";
        }

        AdDAO a = new AdDAO();
        List<AdVO> ad = a.adSearchList(keyword, address, we, edu, size);

        request.setAttribute("main_jsp", "../search/search_ad.jsp");
        return "../search/search_ad_result.jsp";
    }
}