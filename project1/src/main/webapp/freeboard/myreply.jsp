<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="sist.com.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <link rel="stylesheet" href="../css/freeboard/freeboard.css">
</head>
<body>
  <div class="container container-pad min-height">
  <c:if test="${sessionScope.id!=null }">
  <!-- 로그인 유저 -->
    <div class="row roomy-10 m-top-60 p-l-15 no-select">
      <div class="col-sm-4"><h4><i class="fa fa-comment" aria-hidden="true"></i>&nbsp;<b>내 댓글 보기</b></h4></div>
        <div class="col-sm-8">
          <ul class="menu">
            <li><a href="../freeboard/freeboard.do">자유게시판</a></li>
            <li><a href="../freeboard/mypost.do">내 게시물 보기</a></li>
            <li><a href="../freeboard/myreply.do">내 댓글 보기</a></li>
          </ul>
        </div>
    </div>

    <!-- 리스트 상단 -->
    <div class="row top-border"></div>
    <div id="board-top" class="row roomy-10 text-center">
      <div class="col-sm-7">제목</div>
      <div class="col-sm-2">글쓴이</div>
      <div class="col-sm-2">날짜</div>
      <div class="col-sm-1">조회</div>
    </div>
    
    <!-- 게시물 리스트 출력 -->
    <div id="list">
      <c:forEach var="b" items="${board }" varStatus="status">
        <div class="row roomy-15 border-bttom">
          <div id="board-title" class="col-sm-7 short-line">
            <!-- 탭 번호 -->
            
            <!-- 게시물 카테고리 / 제목 -->
            <a href="../freeboard/detail.do?bid=${b.board_id }"><span class="category">[${b.board_category }]&nbsp;&nbsp;</span>${b.board_title }</a>
            <!-- ----------------- -->
    
            <!-- 댓글 수 -->
            <span class="rcount">(${rcount[status.index] })</span>
            <!-- ----------------- -->
    
            <!-- 오늘 날짜에는 new 표시 -->
            <fmt:formatDate value="<%=new java.util.Date()%>" pattern="yyyy-MM-dd" var="today" />
            <c:if test="${today==b.board_date }">
              <sup>new</sup>
            </c:if>
            <!-- ----------------- -->
          </div>
          <div id="board-uid" class="col-sm-2 text-center">${b.u_id }</div>
          <div id="board-date" class="col-sm-2 text-center">${b.board_date }</div>
          <div id="board-visits" class="col-sm-1 text-center">
            <span id="small">조회수 </span>${b.board_visits }</div>
        </div>
      </c:forEach>
    </div>
    
    <!--    페이징    -->
    <div class="row roomy-20">
      <div class="page no-select">
        <ul>
          <c:if test="${startPage>1 }">
            <li class="paging"><a href="../freeboard/myreply.do?page=${startPage-1 }"><i class="fa fa-caret-left" aria-hidden="true"></i></a></li>
          </c:if>
          <c:forEach var="i" begin="${startPage }" end="${endPage }">
            <c:if test="${i==curPage }">
              <li class="current">${i }</li>
            </c:if>
            <c:if test="${i!=curPage }">
              <li class="paging"><a href="../freeboard/myreply.do?page=${i }">${i }</a></li>
            </c:if>
          </c:forEach>
          <c:if test="${endPage<totalPage }">
            <li class="paging"><a href="../freeboard/myreply.do?page=${endPage+1 }"><i class="fa fa-caret-right" aria-hidden="true"></i></a></li>
          </c:if>
        </ul>
      </div>
    </div>
  </c:if>
  </div>
</body>
</html>