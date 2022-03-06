<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
    $(function() {
    	
    	/* 댓글 삭제 */
    	$('.reply-delete-btn').click(function() {
        	let ask = confirm('댓글을 삭제 하시겠습니까?');
        	if (ask == false) return;
            let rid = $(this).attr("data-no");
            
            $.ajax({
                type:'POST',
                url : '../reply/delete.do',
                data : {"rid" : rid},
                success : function(){
                    replyList();
                }
            });
        })
        
        /* 댓글 수정 */
        $('.reply-update').click(function() {
        	let rid = $(this).val();
        	let content = $('#reply-content' + rid).val();
        	if (content.trim() == "") {
                $('#reply-content' + rid).focus();
                return;
            }
            let ask = confirm('댓글을 수정 하시겠습니까?');
            if (ask == false) return;
            
            $.ajax({
                type: 'POST',
                url : '../reply/update.do',
                data : $('#reply-update-form' + rid).serialize(),
                success : function(){
                    replyList();
               }
            });
        })
        
        /* 수정 버튼 열기 접기 */
        $('.rarea').hide();
        let i = 0;
        $('.reply-update-btn').click(function() {
            $('.rarea').hide();
            let rid = $(this).attr("data-no");
            if (i == 0) {
                $('#rarea' + rid).show();
                i = 1;
                $(this).text("취소");
            }
            else {
                $('#rarea' + rid).hide();
                i = 0;
                $('.reply-update-btn').text("수정");
            }
        })
    }) 
</script>
<c:forEach var="r" items="${reply }" varStatus="status">
  <div class="row roomy-20 word">
    <div class="col-xs-2">${r.u_id }</div>
    <div class="col-xs-7 word">
    <div><pre>${r.reply_content }</pre></div>
    <c:if test="${r.u_id==sessionScope.id}">
      <div class="rarea" id="rarea${r.reply_id }">
      <!-- 댓글 수정 -->
        <form id="reply-update-form${r.reply_id }">
          <input type="hidden" name="rid" value="${r.reply_id }">
          <textarea id="reply-content${r.reply_id }" name="content">${r.reply_content }</textarea>
          <button type="button" class="reply-update" value="${r.reply_id }">수정</button>
        </form>
      </div>
    </c:if>
    </div>
    <div class="col-xs-3 small-font text-right">
      <c:if test="${r.u_id==sessionScope.id}">
        <span class="btn reply-update-btn" data-no="${r.reply_id }">수정</span>
        <span class="btn reply-delete-btn" data-no="${r.reply_id }">삭제</span>
      </c:if>
      &nbsp;${r.reply_date }
    </div>
  </div>
</c:forEach>