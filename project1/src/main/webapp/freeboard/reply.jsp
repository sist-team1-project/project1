<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
    $(function() {
        $('.reply-delete-btn').click(function() {
        	confirm('댓글을 삭제 하시겠습니까?');
            let rid = $(this).val();
            $.ajax({
                type:'POST',
                url : '../reply/reply_delete_ok.do',
                data : {"rid" : rid},
                success : function(){
                    replyList();
                }
            });
        })
    }) 
</script>
<c:forEach var="r" items="${reply }" varStatus="status">
  <div class="row roomy-20 word">
    <div class="col-xs-2">${r.u_id }</div>
    <div class="col-xs-7 word">
    <div><pre>${r.reply_content }</pre></div>
    <div></div>
    </div>
    <div class="col-xs-3 small-font text-right">
      <c:if test="${r.u_id==sessionScope.id}">
        <button class="reply-delete-btn" value="${r.reply_id }">삭제</button>
      </c:if>
      &nbsp;${r.reply_date }
    </div>
  </div>
</c:forEach>