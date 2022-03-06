package sist.com.model;

import sist.com.controller.RequestMapping;
import java.util.*;
import javax.servlet.http.*;

import sist.com.vo.*;
import sist.com.dao.*;

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

        String bid = request.getParameter("bid");
        String uid = request.getParameter("uid");
        String content = request.getParameter("content");

        ReplyVO vo = new ReplyVO();
        vo.setBoard_id(Integer.parseInt(bid));
        vo.setU_id(uid);
        vo.setReply_content(content);

        ReplyDAO dao = new ReplyDAO();
        dao.replyInsert(vo);
    }
    
    // 댓글 - 댓글 삭제
    @RequestMapping("reply/delete.do")
    public void freeboard_reply_delete(HttpServletRequest request, HttpServletResponse response) {

        String rid = request.getParameter("rid");

        ReplyDAO dao = new ReplyDAO();
        dao.replyDelete(Integer.parseInt(rid));
    }
    
    @RequestMapping("reply/update.do")
    public void freeboard_reply_update(HttpServletRequest request, HttpServletResponse response) {

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (Exception ex) {
        }

        String rid = request.getParameter("rid");
        String content = request.getParameter("content");
        
        ReplyDAO dao = new ReplyDAO();
        dao.replyUpdate(Integer.parseInt(rid), content);
    }
}