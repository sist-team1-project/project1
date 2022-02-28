<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="sist.com.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <link rel="stylesheet" href="../css/freeboard_detail.css">
</head>
<body>

  <div class="container container-pad">

    <div class="row m-top-70">
      <div class="col-md-12 roomy-10">
        <h4><b>[${detail.board_category }]&nbsp;&nbsp;${detail.board_title }</b></h4>
      </div>
    </div>
    <div class="row roomy-10">
      <div class="col-md-12">
        <b>${detail.u_id }</b>&nbsp;&nbsp;&nbsp;${detail.board_date }&nbsp;&nbsp;&nbsp;조회수 ${detail.board_visits }
      </div>
    </div>
    <hr>
    <div class="row roomy-40 content">
      <div class="col-md-12">
        ${detail.board_content }
      </div>
    </div>
    
    <div class="row roomy-20">
      <div class="col-md-12 text-center">
        <form>
          <textarea class="insert-textarea"></textarea>
          <button id="insert-btn" type="submit">댓글<br>작성</button>
        </form>
      </div>
    </div>
    <div class="row roomy-20">
      <div class="col-md-12 bottom-btn">
        <c:if test="${detail.u_id==sessionScope.id }">
          <a href="#" class="btn btn-default pink-btn">수정</a>
          <a href="#" class="btn btn-default pink-btn">삭제</a>
        </c:if>
        <a href="../freeboard/freeboard.do" class="btn btn-default">목록</a>
      </div>
    </div>
  </div>
</body>
</html>