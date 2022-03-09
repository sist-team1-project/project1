package sist.com.model;

import sist.com.controller.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.*;

import sist.com.vo.*;
import sist.com.dao.*;

// 자유 게시판
public class CalendarModel {
    
    // 자유게시판 - 페이지 이동
    @RequestMapping("calendar/calendar.do")
    public String calendar(HttpServletRequest request, HttpServletResponse response) {
        
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        
        // 첫페이지
        if(year == null && month == null) {
            Date date = new Date();
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
            String today = sdf.format(date);
            StringTokenizer st = new StringTokenizer(today, "-");
            year = st.nextToken();
            month = st.nextToken();
        }
        
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(year));
        cal.set(Calendar.MONTH, Integer.parseInt(month)- 1);
        cal.set(Calendar.DATE, 1);
        int week = cal.get(Calendar.DAY_OF_WEEK);
        int lastday = cal.getActualMaximum(Calendar.DATE);

        request.setAttribute("year", year);
        request.setAttribute("month", month);
        request.setAttribute("week", week - 1);
        request.setAttribute("lastday", lastday);
        String[] strWeek = { "일", "월", "화", "수", "목", "금", "토" };
        request.setAttribute("strWeek", strWeek);
        
        
        request.setAttribute("main_jsp", "../calendar/calendar.jsp");
        return "../main/main.jsp";
    }
}