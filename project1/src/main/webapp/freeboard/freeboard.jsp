<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="sist.com.model.*"%>
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
  <div class="container">
    
    <div class="row m-top-40"><div class="no-select"><h4><b>자유게시판</b></h4></div></div>
    <c:if test="${sessionScope.id!=null }">
      <div class="row m-top-20">
        <div class="col-md-4 top-menu">자유게시판</div>
        <div class="col-md-4 top-menu">내가 쓴 글 관리</div>
        <div class="col-md-4 top-menu">내가 쓴 댓글 관리</div>
      </div>  
    </c:if>
    <div class="row m-top-20">
      <div id="board-top" class="row topborder pad-10 text-center">
        <div class="col-sm-7">제목</div>
        <div class="col-sm-5">
          <div class="col-sm-5">글쓴이</div>
          <div class="col-sm-4">날짜</div>
          <div class="col-sm-3">조회수</div>
        </div>

      </div>
    </div>
    
      <c:forEach var="p" items="${post }" varStatus="status">
      <div class="row row-border">
        <div class="row pad-10">
          <div id="post-title"  class="col-sm-7 post-title-container short-container"><span class="post-title short-line">[${p.post_category }]&nbsp;&nbsp;${p.post_title }</span></div>
          <div class="col-sm-5 small-font">
            <div class="col-sm-5 text-center">${p.u_id }</div>
            <div class="col-sm-4 text-center">${p.post_date }</div>
            <div id = "visits" class="col-sm-3 text-center">${p.post_visits }</div>
            <div id = "visits-small" class="col-md-3 text-center">조회수 ${p.post_visits }</div>
          </div>      
        </div>
      </div>  
      </c:forEach>

    
  </div>
</body>
</html>