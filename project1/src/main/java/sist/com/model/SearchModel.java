package sist.com.model;

import java.text.SimpleDateFormat;
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
    public String search_ad_result(HttpServletRequest request, HttpServletResponse response) {
        String page = request.getParameter("page");
        String keyword = request.getParameter("keyword");
        String sido = request.getParameter("sido");
        String gugun = request.getParameter("gugun");
        String[] weArr = request.getParameterValues("we");
        String[] eduArr = request.getParameterValues("edu");
        String[] sizeArr = request.getParameterValues("size");
        String[] worktypeArr = request.getParameterValues("worktype");
        String[] qualArr = request.getParameterValues("qual");
        String[] langArr = request.getParameterValues("lang");
        String date1 = request.getParameter("date1");
        String date2 = request.getParameter("date2");
        

        if(page == null) page = "1";
        int curPage = Integer.parseInt(page);
        
        String address, we, edu, size, worktype, qual, lang;
        
        if(keyword == null) keyword = "";
        
        if (sido==null  || sido.equals("시/도 선택"))  address = "";
        else address = sido + " " + gugun;
        
        if (weArr == null)  we = "신입|경력|관계없음";
        else we = String.join("|", weArr);

        if(eduArr == null) edu = "중졸이하|고졸|2~3년|4년|석사|박사|학력무관";
        else edu = String.join("|", eduArr);
        
        if(sizeArr == null) size = "대기업|중견기업|중소기업|기타|-";
        else size = String.join("|", sizeArr);
        
        if(worktypeArr == null)  worktype = "주 5일|주 6일|주 4일|주 3일|주 2일|주 1일|주 7일";
        else worktype = String.join("|", worktypeArr);
        
        if(qualArr == null) qual = "";
        else qual = String.join("|", qualArr);
        
        if(langArr == null) lang = "";
        else lang = String.join("|", langArr);
        
        if (date1 == "" || date1 == null) date1 = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); // 오늘 날짜
        
        if (date2 == null) date2 = "";
        
        if(qual.length() != 0 && qual.charAt(qual.length()-1) == '|')
            qual = qual.substring(0, qual.length()-1);
        if(lang.length() != 0 && lang.charAt(lang.length()-1) == '|')
            lang = lang.substring(0, lang.length()-1);
        
        AdDAO adao = new AdDAO();
        CompanyDAO cdao = new CompanyDAO();
        
        List<AdVO> ad = adao.adSearchList(Integer.parseInt(page), keyword, address, we, edu, size, worktype, qual, lang, date1, date2);
        List<String> company = new ArrayList<String>();
        for(AdVO a : ad) {
            company.add(cdao.companyName(a.getC_id()));
        }
        int totalPage = adao.adSearchListTotalPage(keyword, address, we, edu, size, worktype, qual, lang, date1, date2);
        
        // 페이지
        final int BLOCK = 10;
        int startPage = ((curPage - 1) / BLOCK * BLOCK) + 1;
        int endPage = ((curPage - 1) / BLOCK * BLOCK) + BLOCK;
        if (endPage > totalPage) {
            endPage = totalPage;
        }
        
        // 검색어들
        request.setAttribute("keyword", keyword);
        request.setAttribute("sido", sido);
        request.setAttribute("gugun", gugun);
        request.setAttribute("we", we.replace("|", "%7C")); // 페이지에서 주소로 쓰이기 위해서
        request.setAttribute("edu", edu.replace("|", "%7C"));
        request.setAttribute("size", size.replace("|", "%7C"));
        request.setAttribute("worktype", worktype.replace("|", "%7C"));
        request.setAttribute("qual", qual.replace("|", "%7C"));
        request.setAttribute("lang", lang.replace("|", "%7C"));
        request.setAttribute("date1", date1);
        request.setAttribute("date2", date2);
        // 페이지
        request.setAttribute("curPage", curPage);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        // 출력
        request.setAttribute("ad", ad);
        request.setAttribute("company", company);
       
        
        request.setAttribute("main_jsp", "../search/search_ad.jsp");
        return "../main/main.jsp";
    }
}