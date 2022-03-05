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
    @RequestMapping("reply/reply_ok.do")
    public void freeboard_reply_ok(HttpServletRequest request, HttpServletResponse response) {

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
    @RequestMapping("reply/reply_delete_ok.do")
    public String freeboard_reply_delete_ok(HttpServletRequest request, HttpServletResponse response) {

        String bid = request.getParameter("bid");
        String rid = request.getParameter("rid");

        ReplyDAO dao = new ReplyDAO();
        dao.freeboardReplyDelete(Integer.parseInt(rid));

        return "redirect:../freeboard/detail.do?bid=" + bid;
    }
}