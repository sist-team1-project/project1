<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var="r" items="${reply }" varStatus="status">
  <div class="row roomy-20 word">
    <div class="col-xs-2 short-line">${r.u_id }</div>
    <div class="col-xs-7 word">${r.reply_content }</div>
    <div class="col-xs-3 small-font text-right">
      <c:if test="${r.u_id==sessionScope.id}">
        <a class="reply-delete-btn" href="../freeboard/reply_delete_ok.do?bid=${r.board_id }&rid=${r.reply_id }" onclick="return confirm('댓글을 삭제 하시겠습니까?')">삭제</a>
      </c:if>
      &nbsp;${r.reply_date }
    </div>
  </div>
</c:forEach>