package com.sist.model;

import com.sist.controller.*;

import java.time.LocalDate;
import java.util.*;
import javax.servlet.http.*;

import com.sist.dao.*;
import com.sist.vo.*;

// 채용 달력
public class CalendarModel {
    
    // 채용 달력 - 페이지 이동
    @RequestMapping("calendar/calendar.do")
    public String calendar(HttpServletRequest request, HttpServletResponse response) {
            
        request.setAttribute("main_jsp", "../calendar/calendar.jsp");
        return "../main/main.jsp";
    }
    
    // 채용 달력 - 페이지 이동
    @RequestMapping("calendar/list.do")
    public String calendar_list(HttpServletRequest request, HttpServletResponse response) {
        
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        
        int newYear = 0;
        int newMonth = 0;
        
        // 첫페이지
        if(year == null && month == null) {
            LocalDate now = LocalDate.now();
            newYear = now.getYear();
            newMonth = now.getMonthValue();
        } else {
            newYear = Integer.parseInt(year);
            newMonth = Integer.parseInt(month);
        }
                
        if (newMonth == 0) {
            newYear = newYear - 1;
            newMonth = 12;
        } else if (newYear == 13) {
            newYear = newYear + 1;
            newMonth = 1;
        }
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, newYear);
        cal.set(Calendar.MONTH, newMonth - 1);
        cal.set(Calendar.DATE, 1);
        int week = cal.get(Calendar.DAY_OF_WEEK);
        int lastday = cal.getActualMaximum(Calendar.DATE);
        
        AdDAO dao = new AdDAO();
        List<List<AdVO>> ad = new ArrayList<List<AdVO>>();
        
        String date = newYear + "-" + String.format("%02d", newMonth) + "-";
        for(int i = 1; i <= lastday; i++) {
            ad.add(dao.adByDate(date + String.format("%02d", i)));
        }
        
        request.setAttribute("year", newYear);
        request.setAttribute("month", newMonth);
        request.setAttribute("week", week);
        request.setAttribute("lastday", lastday);
        String[] strWeek = { "일", "월", "화", "수", "목", "금", "토" };
        request.setAttribute("strWeek", strWeek);
        request.setAttribute("ad", ad);
        
        return "../calendar/list.jsp";
    }
}