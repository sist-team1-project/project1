package sist.com.model;

import sist.com.controller.RequestMapping;
import java.util.*;
import javax.servlet.http.*;

import sist.com.vo.*;
import sist.com.dao.*;


public class FreeboardModel {
    
    @RequestMapping("freeboard/freeboard.do")
    public String main_page(HttpServletRequest request) {

        request.setAttribute("main_jsp", "../freeboard/freeboard.jsp");
        return "../main/main.jsp";
    }
}
