<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="sist.com.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script>
    $(function() {
        
        /* 리뷰 삭제 */
        $('.review-delete-btn').click(function() {
            let ask = confirm('리뷰 삭제 하시겠습니까?');
            if (ask == false) return;
            let rid = $(this).attr("data-no");
            
            $.ajax({
                type:'POST',
                url : '../review/delete.do',
                data : {"rid" : rid},
                success : function(){
                    reviewList();
                }
            });
        })
        
        /* 리뷰 수정 */
        $('.review-update').click(function() {
            let rid = $(this).val();
            
            let job = $('#review-job' + rid).val();
            if (job.trim() == "") {
                $('#review-job' + rid).focus();
                return;
            }
            
            let content = $('#review-content' + rid).val();
            if (content.trim() == "") {
                $('#review-content' + rid).focus();
                return;
            }
            let ask = confirm('댓글을 수정 하시겠습니까?');
            if (ask == false) return;
            
            $.ajax({
                type: 'POST',
                url : '../review/update.do',
                data : $('#review-update-form' + rid).serialize(),
                success : function(){
                    reviewList();
               }
            });
        })
        
        /* 수정 버튼 열기 접기 */
        $('.rarea').hide();
        let i = 0;
        $('.review-update-btn').click(function() {
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
                $('.review-update-btn').text("수정");
            }
        })
    }) 
</script>
      


<c:forEach var="r" items="${review }" varStatus="status">
  <div class="col-md-12 room">
    <div class="review-job">
      <c:if test="${r.review_goodbad==1}">
        <i class="fa fa-thumbs-up" aria-hidden="true"></i>&nbsp;&nbsp;<b>${r.review_job }</b>
      </c:if>
      <c:if test="${r.review_goodbad==2}">
        <i class="fa fa-thumbs-down" aria-hidden="true"></i>&nbsp;&nbsp;<b>${r.review_job }</b>
      </c:if>
      <c:if test="${r.u_id==sessionScope.id}">
        <span class="btn btn-xs btn-default review-update-btn" data-no="${r.review_id }">수정</span>
        <span class="btn btn-xs btn-default review-delete-btn" data-no="${r.review_id }">삭제</span>
      </c:if>
    </div>
    <div class="review-content"><pre>${r.review_content }</pre></div>
    <div class="room rarea" id="rarea${r.review_id }">
      <form id="review-update-form${r.review_id }">
        <input type="hidden" name="rid" value="${r.review_id }">
        <b>면접은 만족 하셨나요?</b>&nbsp;&nbsp;<input type="radio" value=1 name="goodbad" id="good" checked><label for="goodbad"> 만족</label> <input type=radio value=2 name=goodbad id="bad"><label for="bad"> 불만족</label><br><br>
        <b>지원하신 직무</b><br>
        <input id="review-job${r.review_id }" type=text name=job><br><br>
        <b>경험하신 면접에 대하여 작성하여 주세요</b><br>
        <textarea id="review-content${r.review_id }" rows="6" name=content></textarea>
        <button type="button" class="review-update" value="${r.review_id }">수정</button>
      </form>
    </div>
  </div>
</c:forEach>