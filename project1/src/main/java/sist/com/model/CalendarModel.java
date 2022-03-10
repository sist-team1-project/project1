package sist.com.model;

import sist.com.controller.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.*;

import sist.com.vo.*;
import sist.com.dao.*;

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
        
        // 첫페이지
        if(year == null && month == null) {
            Date date = new Date();
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
            String today = sdf.format(date);
            StringTokenizer st = new StringTokenizer(today, "-");
            year = st.nextToken();
            month = st.nextToken();
        }
        
        if (month.equals("0")) {
            year = String.valueOf(Integer.parseInt(year)-1);
            month = "12";
        } else if (month.equals("13")) {
            year = String.valueOf(Integer.parseInt(year)+1);
            month = "1";
        }
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(year));
        cal.set(Calendar.MONTH, Integer.parseInt(month)- 1);
        cal.set(Calendar.DATE, 1);
        int week = cal.get(Calendar.DAY_OF_WEEK);
        int lastday = cal.getActualMaximum(Calendar.DATE);
        
        AdDAO dao = new AdDAO();
        List<List<AdVO>> ad = new ArrayList<List<AdVO>>();
        for(int i = 1; i <= lastday; i++) {
            String date = year + "-" + month + "-" + i;
            ad.add(dao.adByDate(date));
        }
        
        
        request.setAttribute("year", year);
        request.setAttribute("month", month);
        request.setAttribute("week", week);
        request.setAttribute("lastday", lastday);
        String[] strWeek = { "일", "월", "화", "수", "목", "금", "토" };
        request.setAttribute("strWeek", strWeek);
        request.setAttribute("ad", ad);
        
        return "../calendar/list.jsp";
    }
}