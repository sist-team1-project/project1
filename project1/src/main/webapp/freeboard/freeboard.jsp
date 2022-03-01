<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="sist.com.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/freeboard.css">
</head>
<body>
  <div class="container container-pad">
    <div class="row roomy-10 m-top-40 p-l-15 no-select">
      <h4>
        <b>자유게시판</b>
      </h4>
    </div>
    
    <!-- 게시물 관리 (로그인시에만) -->
    <c:if test="${sessionScope.id!=null }">
      <div class="row p-l-15 no-select">
        <a href="../freeboard/freeboard.do" class="top-menu-selected">자유게시판</a>
        <a href="#" class="top-menu-notselected">내가 쓴 글 관리</a>
        <a href="#" class="top-menu-notselected">내가 쓴 댓글 관리</a>
      </div>
    </c:if>
    
    <!-- 리스트 상단 -->
    <div class="row top-border"></div>
    <div id="board-top" class="row roomy-10 text-center">
      <div class="col-sm-7">제목</div>
      <div class="col-sm-2">글쓴이</div>
      <div class="col-sm-2">날짜</div>
      <div class="col-sm-1">조회</div>
    </div>

    <!-- 리스트 -->
    <c:forEach var="b" items="${board }" varStatus="status">
      <div class="row row-border roomy-15">
        <div id="post-title" class="col-sm-7 post-title-container short-container">
          <a href="../freeboard/detail.do?bid=${b.board_id }" class="post-title short-line"><span class="category">[${b.board_category }]</span>&nbsp;&nbsp;${b.board_title }</a>
        </div>
        <div class="col-sm-2 text-center">${b.u_id }</div>
        <div class="col-sm-2 text-center">${b.board_date }</div>
        <div class="col-sm-1 text-center">
          <span id="small">조회수 </span>${b.board_visits }</div>
      </div>
    </c:forEach>
    <div class="row roomy-10">
      <div class="post">
        <a href="#" class="btn btn-primary">글쓰기</a>
      </div>
    </div>
    
    <!-- 페이지 -->
    <div class="row roomy-10">
      <div class="page no-select">
        <ul>
          <c:if test="${startPage>1 }">
            <li><a href="../freeboard/freeboard.do?page=${startPage-1 }">&laquo;</a></li>
          </c:if>
          <c:set var="style" value="" />
          <c:forEach var="i" begin="${startPage }" end="${endPage }">
            <c:if test="${i==curPage }">
              <c:set var="style" value="class=current" />
              <li class="current">${i }</li>
            </c:if>
            <c:if test="${i!=curPage }">
              <li><a href="../freeboard/freeboard.do?page=${i }">${i }</a></li>
            </c:if>
          </c:forEach>
          <c:if test="${endPage<totalPage }">
            <li><a href="../freeboard/freeboard.do?page=${endPage+1 }">&raquo;</a></li>
          </c:if>
        </ul>
      </div>
    </div>
  </div>
</body>
</html>