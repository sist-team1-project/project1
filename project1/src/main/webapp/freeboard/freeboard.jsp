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
    <div class="row roomy-10 m-top-60 p-l-15 no-select">
      <div class="col-sm-3"><h4><i class="fa fa-smile-o" aria-hidden="true"></i>&nbsp;<b>자유게시판</b></h4></div>
      
      <c:if test="${sessionScope.id!=null }">
        <div class="col-sm-9">
          <ul class="menu">
            <li><a href="../freeboard/freeboard.do">자유게시판</a></li>
            <li><a href="../freeboard/mypost.do">내 게시물 보기</a></li>
            <li><a href="../freeboard/myreply.do">내 댓글 보기</a></li>
          </ul>
        </div>
      </c:if>
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
    
    <c:if test="${sessionScope.id!=null }">
      <!-- 글쓰기 버튼 로그인시에만 보이기 -->
      <div class="row">
        <div class="post">
          <a href="../freeboard/insert.do" class="btn btn-primary">글쓰기</a>
        </div>
      </div>
    </c:if>
    
    <!--    검색    -->
    <div class="row roomy-10 text-center">
      <form method="post" action="../freeboard/freeboard.do">
        <select name="topic">
          <option value="title" <c:if test="${topic eq 'title' }">selected</c:if>>제목</option>
          <option value="writer" <c:if test="${topic eq 'writer' }">selected</c:if>>작성자</option>
          <option value="content" <c:if test="${topic eq 'content' }">selected</c:if>>내용</option>
        </select>
        <input type=text name="search" value="${search }"> <input type=submit class="btn btn-primary" value="검색">
      </form>
    </div>
    <!-- ---------- -->
    
    <!--    페이징    -->
    <div class="row roomy-10">
      <div class="page no-select">
        <ul>
          <c:if test="${startPage>1 }">
            <li class="paging"><a href="../freeboard/freeboard.do?page=${startPage-1 }&topic=${topic }&search=${search }"><i class="fa fa-caret-left" aria-hidden="true"></i></a></li>
          </c:if>
          <c:forEach var="i" begin="${startPage }" end="${endPage }">
            <c:if test="${i==curPage }">
              <li class="current">${i }</li>
            </c:if>
            <c:if test="${i!=curPage }">
              <li class="paging"><a href="../freeboard/freeboard.do?page=${i }&topic=${topic }&search=${search }">${i }</a></li>
            </c:if>
          </c:forEach>
          <c:if test="${endPage<totalPage }">
            <li class="paging"><a href="../freeboard/freeboard.do?page=${endPage+1 }&topic=${topic }&search=${search }"><i class="fa fa-caret-right" aria-hidden="true"></i></a></li>
          </c:if>
        </ul>
      </div>
    </div>
    <!-- ---------- -->
  </div>
</body>
</html>