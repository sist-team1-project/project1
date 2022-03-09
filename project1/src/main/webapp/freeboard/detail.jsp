<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="sist.com.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <link rel="stylesheet" href="../css/freeboard/detail.css">
  <script>
    $(function() {
    	replyList();
    	
        $('#reply-insert-btn').click(function() {
            let content = $('#reply-content').val();
            if (content.trim() == "") {
                $('#reply-content').focus();
                return;
            }
            
            $.ajax({
                type:'POST',
                url : '../reply/insert.do',
                data : $('#reply-form').serialize(),
                success : function(){
                    replyList();
                }
            });
        })
        
        $('#delete-btn').click(function() {
        	var result = confirm('게시물을 삭제하시겠습니까?');
            if (result) {
                let bid = $('#bid').val();
                $.ajax({
                    type:'post',
                    url : '../freeboard/delete.do',
                    data : {"bid" : bid},
                    success : function(result){
                        alert("삭제되었습니다");
                        location.href = result; 
                    }
                });
            }
        })
    })
    
    function replyList() {
        $('#reply-content').val("");
        let bid = $('#bid').val();
        $.ajax({
            type : 'get',
            url : '../reply/reply.do',
            data : {"bid" : bid},
            success : function(result) {
                $('#reply').html(result);
            }
        });
    }

  </script>
</head>
<body>
  <div class="container container-pad container-height">
  
    <!-- 제목 -->
    <div class="row m-top-40">
      <div class="col-md-12 roomy-10">
        <h4><b>[${detail.board_category }]&nbsp;&nbsp;${detail.board_title }</b></h4>
      </div>
    </div>
    <!------------->
    
    <!-- 날짜 / 조회수 -->
    <div class="row roomy-10">
      <div class="col-md-12">
        <b>${detail.u_id }</b>&nbsp;&nbsp;&nbsp;${detail.board_date }&nbsp;&nbsp;&nbsp;조회수 ${detail.board_visits }
        <input type="hidden" id="bid" name="bid" value="${detail.board_id }"> <!-- 게시물 번호 -->
      </div>
    </div>
    <hr>
    <!------------->
    
    <!-- 내용 -->
    <div class="row roomy-10 content">
      <div class="col-md-12">
        <pre>${detail.board_content }</pre>
      </div>
    </div>
    <hr>
    <!------------->
    
    <!-- 버튼 -->
    <div class="row roomy-10">
      <div class="col-md-12 text-right">
        <c:if test="${detail.u_id==sessionScope.id }">
          <!-- 글쓴이일 때 수정/삭제 버튼 -->
          <a href="../freeboard/update.do?bid=${detail.board_id }" class="btn btn-pink">수정</a>
          <span id="delete-btn" class="btn btn-pink">삭제</span>
        </c:if>
        <a href="../freeboard/freeboard.do" class="btn btn-default">목록</a>
      </div>
    </div>
    <!------------->
    
    <!-- 댓글 작성 -->
    <div class="row roomy-20">
      <div class="col-md-12 text-center">
        <!-- 로그인 했을 때 댓글창 -->
        <c:if test="${sessionScope.id!=null }">
          <form id="reply-form">
            <input type="hidden" name="bid" value="${detail.board_id }">
            <textarea id="reply-content" name="content"></textarea>
            <button type="button" id="reply-insert-btn" class="reply-insert-btn">댓글<br>작성</button>
          </form>
        </c:if>
        <!-- 로그인 안했을 때 댓글창 -->
        <c:if test="${sessionScope.id==null }">
          <textarea readonly>로그인한 뒤 작성하여 주세요.</textarea>
          <button type="button" class="reply-insert-btn">댓글<br>작성</button>
        </c:if>
      </div>
    </div>
    <!------------->
    
    <!-- 댓글 출력 -->
    
    <div id="reply">
      
    </div>
    <!------------->
    
  </div>
</body>
</html>