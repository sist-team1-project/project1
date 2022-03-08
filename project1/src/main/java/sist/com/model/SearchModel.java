package sist.com.model;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.*;
import sist.com.controller.RequestMapping;

import sist.com.vo.*;
import sist.com.dao.*;

public class SearchModel {
    
    // 헤더 - 기업 검색 이동
    @RequestMapping("search/searchcompany.do")
    public String search_company(HttpServletRequest request, HttpServletResponse response) {
        
        CompanyDAO c = new CompanyDAO();
        ReviewDAO r = new ReviewDAO();
        
        List<CompanyVO> company = c.bestCompanyList();
        List<String> review = new ArrayList<String>();
        
        // 베스트 기업 출력용
        for (int i = 0; i < company.size(); i++) {
            String bestreview = r.bestCompanyReviewList(company.get(i).getC_id());
            review.add(bestreview);
        }
        
        request.setAttribute("company", company);
        request.setAttribute("review", review);
        
        request.setAttribute("main_jsp", "../search/search_company.jsp");
        return "../main/main.jsp";
    }
    
    // 기업 검색 - 검색 결과
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
    
    // 헤더 - 공고 검색 이동
    @RequestMapping("search/searchad.do")
    public String search_ad(HttpServletRequest request, HttpServletResponse response) {
        
        request.setAttribute("main_jsp", "../search/search_ad.jsp");
        return "../main/main.jsp";
    }
    
    // 공고 검색 - 검색 결과
    @RequestMapping("search/searchad_result.do")
    public String search_ad_result(HttpServletRequest request, HttpServletResponse response) {
        
        String page = request.getParameter("page");
        
        /*   form으로 받아온 값들    */
        String keyword = request.getParameter("keyword");
        String sido = request.getParameter("sido");
        String gugun = request.getParameter("gugun");
        // 다중 체크값은 배열로 받음
        String[] weArr = request.getParameterValues("we");
        String[] eduArr = request.getParameterValues("edu");
        String[] sizeArr = request.getParameterValues("size");
        String[] worktypeArr = request.getParameterValues("worktype");
        String[] qualArr = request.getParameterValues("qual");
        String[] langArr = request.getParameterValues("lang");
        String date1 = request.getParameter("date1");
        String date2 = request.getParameter("date2");
        
        
        String address, we, edu, size, worktype, qual, lang;
        String url="";
        
        // 첫 로딩시 페이지는 form에 없으므로 null
        if (page == null) page = "1";
        int curPage = Integer.parseInt(page);
        
        
        if (sido.equals("시/도 선택")) sido = "";
        
        // sql 값 준비
        address = sido + " " + gugun;
        we = String.join("|", weArr);
        edu = String.join("|", eduArr);
        size = String.join("|", sizeArr);
        worktype = String.join("|", worktypeArr);
        qual = String.join("|", qualArr);
        lang = String.join("|", langArr);
        if (date1 == "" || date1 == null) date1 = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); // 오늘 날짜
        if (date2 == null) date2 = "";
        
        // 검색 결과
        AdDAO adao = new AdDAO();
        List<AdVO> ad = adao.adSearchList(Integer.parseInt(page), keyword, address, we, edu, size, worktype, qual, lang, date1, date2);
        
        // 공고마다 회사 이름 저장
        CompanyDAO cdao = new CompanyDAO();
        List<String> company = new ArrayList<String>();
        for(AdVO a : ad) company.add(cdao.companyName(a.getC_id()));
        
        // 페이지 처리를 위해 주소 만들기
        url += "keyword=" + keyword;
        url += "&sido=" + sido +"&gugun=" + gugun;
        for (String i : weArr) url += "&we=" + i;
        for (String i : eduArr) url += "&edu=" + i;
        for (String i : sizeArr) url += "&size=" + i;
        for (String i : worktypeArr) url += "&worktype=" + i;
        for (String i : qualArr)  url += "&qual=" + i;
        for (String i : langArr) url += "&lang=" + i;
        url += "&date1=" + date1 + "&date2=" + date2;
        
        // url 형식으로 변형
        url =  url.replace(" ", "+").replace("|", "%7C").replace("/", "%2F");
        
        // 페이지
        int totalPage = adao.adSearchListTotalPage(keyword, address, we, edu, size, worktype, qual, lang, date1, date2);
        final int BLOCK = 10;
        int startPage = ((curPage - 1) / BLOCK * BLOCK) + 1;
        int endPage = ((curPage - 1) / BLOCK * BLOCK) + BLOCK;
        if (endPage > totalPage) {
            endPage = totalPage;
        }
        
        // 디버깅용
        //System.out.println(url);
        
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        
        request.setAttribute("url", url);
        // 페이지
        request.setAttribute("curPage", curPage);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        // 출력
        request.setAttribute("ad", ad);
        request.setAttribute("company", company);
        // 오늘날짜
        request.setAttribute("today", today);
        
        return "../search/search_ad_result.jsp";
    }
}