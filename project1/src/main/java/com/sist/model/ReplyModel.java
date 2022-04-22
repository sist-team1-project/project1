package com.sist.model;

import com.sist.controller.*;

import java.util.*;
import javax.servlet.http.*;

import com.sist.dao.*;
import com.sist.vo.*;

// 자유 게시판
public class ReplyModel {
    
    // 게시물 - 댓글 출력
    @RequestMapping("reply/reply.do")
    public String freeboard_reply(HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter("bid");

        ReplyDAO rdao = new ReplyDAO();
        List<ReplyVO> reply = rdao.replyList(Integer.parseInt(id));

        request.setAttribute("reply", reply);
        return "../freeboard/reply.jsp";
    }
    
    
    // 댓글 - 댓글 작성
    @RequestMapping("reply/insert.do")
    public void freeboard_reply_insert(HttpServletRequest request, HttpServletResponse response) {
        
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (Exception ex) {
        }
        
        HttpSession session = request.getSession();
        String bid = request.getParameter("bid");
        String content = request.getParameter("content");
        String uid = (String)session.getAttribute("id");
        
        // 로그인시에만 댓글 작성
        if( uid != null) {
            ReplyVO vo = new ReplyVO();
            vo.setBoard_id(Integer.parseInt(bid));
            vo.setU_id(uid);
            vo.setReply_content(content);
            
            ReplyDAO dao = new ReplyDAO();
            dao.replyInsert(vo);
        }
    }
    
    // 댓글 - 댓글 삭제
    @RequestMapping("reply/delete.do")
    public void freeboard_reply_delete(HttpServletRequest request, HttpServletResponse response) {
        
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        String rid = request.getParameter("rid");

        ReplyDAO dao = new ReplyDAO();
        
        // 유효성 검사
        if(dao.checkUser(id, Integer.parseInt(rid)) == true) {
            dao.replyDelete(Integer.parseInt(rid));
        }
    }
    
    @RequestMapping("reply/update.do")
    public void freeboard_reply_update(HttpServletRequest request, HttpServletResponse response) {

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (Exception ex) {
        }
        
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        String rid = request.getParameter("rid");
        String content = request.getParameter("content");
        
        ReplyDAO dao = new ReplyDAO();
        
        // 유효성 검사
        if(dao.checkUser(id, Integer.parseInt(rid)) == true) {
            dao.replyUpdate(Integer.parseInt(rid), content);
        }
    }
}