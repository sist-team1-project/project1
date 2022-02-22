package sist.com.model;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sist.com.controller.RequestMapping;
import sist.com.dao.*;
public class UsersModel {
   @RequestMapping("login/login.do")
   public String memberLogin(HttpServletRequest request,HttpServletResponse
		   response)
   {
	   String id=request.getParameter("id");
	   String pwd=request.getParameter("pwd");
	   UsersDAO dao=new UsersDAO();
	   String result=dao.isLogin(id, pwd);
	   request.setAttribute("result", result);//id,pwd,ok
	   if(!(result.equals("NOID")||result.equals("NOPWD")))
	   {
		   HttpSession session=request.getSession();
		   StringTokenizer st=new StringTokenizer(result,"|");
		   session.setAttribute("id", id);
		   session.setAttribute("name", st.nextToken());
		   session.setAttribute("admin", Integer.parseInt(st.nextToken()));
	   }
	   return "../login/login.jsp";
   }
   @RequestMapping("member/logout.do")
   public String memberLogout(HttpServletRequest request,HttpServletResponse
		   response)
   {
	   HttpSession session=request.getSession();
	   session.invalidate(); //session해제
	   return "redirect:../main/main.do";
   }
}















