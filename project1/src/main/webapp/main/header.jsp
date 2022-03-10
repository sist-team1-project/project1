<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
</head>
<body>
  <nav class="navbar navbar-default bootsnav">
    <div class="container"> 
      <!-- 네비게이션바 로고 -->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-menu">
          <i class="fa fa-bars"></i>
        </button>
        <a class="navbar-brand" href="../main/main.do">
          <img src="../images/logo.png" class="logo">
        </a>
      </div>

      <!-- 네비게이션 바 메뉴 -->
      <div class="collapse navbar-collapse" id="navbar-menu">
        <ul class="nav navbar-nav navbar-left">
          <li><a href="../main/main.do">홈</a></li>                    
          <li><a href="../search/searchcompany.do">기업 정보</a></li>
          <li><a href="../search/searchad.do">채용 공고</a></li>
          <li><a href="../calendar/calendar.do">채용 달력</a></li>
          <li><a href="../freeboard/freeboard.do">자유게시판</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <c:if test="${sessionScope.id==null }">
            <li><a href="../users/login.do">로그인</a></li>
          </c:if>
          <c:if test="${sessionScope.id!=null }">
            <li><a href="../mypage/mypage.do">마이페이지</a></li>
            <li><a href="../users/logout_ok.do">로그아웃</a></li>
          </c:if>
        </ul>
      </div>
      <!-- ------------- -->
    </div>
  </nav>
</body>
</html>