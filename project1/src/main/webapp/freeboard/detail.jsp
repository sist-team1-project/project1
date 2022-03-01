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
</head>
<body>
  <div class="container container-pad">
    <!-- 제목 -->
    <div class="row m-top-40">
      <div class="col-md-12 roomy-10">
        <h4><b>[${detail.board_category }]&nbsp;&nbsp;${detail.board_title }</b></h4>
      </div>
    </div>
    <!-- 날짜 / 조회수 -->
    <div class="row roomy-10">
      <div class="col-md-12">
        <b>${detail.u_id }</b>&nbsp;&nbsp;&nbsp;${detail.board_date }&nbsp;&nbsp;&nbsp;조회수 ${detail.board_visits }
      </div>
    </div>
    <hr>
    <!-- 내용 -->
    <div class="row roomy-20 content">
      <div class="col-md-12">
        ${detail.board_content }
      </div>
    </div>
    <hr>
    
    <!-- 댓글 작성 -->
    <div class="row roomy-20">
      <div class="col-md-12 text-center">
        <form method=post action="../freeboard/reply_ok.do" id="insert-form">
          <c:if test="${sessionScope.id!=null }">
            <textarea id="insert-textarea"></textarea>
            <button type="submit" id="insert-btn">댓글<br>작성</button>
          </c:if>
          <c:if test="${sessionScope.id==null }">
            <textarea readonly>로그인한 뒤 작성하여 주세요.</textarea>
            <button type="button" id="insert-btn">댓글<br>작성</button>
          </c:if>
        </form>
      </div>
    </div>
    
    <!-- 댓글 출력 -->
    


    <!-- 하단 버튼 -->
    <div class="row roomy-20">
      <div class="col-md-12 text-right">
        <c:if test="${detail.u_id==sessionScope.id }">
          <a href="../freeboard/update.do?bid=${detail.board_id }" class="btn btn-pink">수정</a>
          <a href="#" class="btn btn-pink">삭제</a>
        </c:if>
        <a href="../freeboard/freeboard.do" class="btn btn-default">목록</a>
      </div>
    </div>
  </div>
</body>
</html>