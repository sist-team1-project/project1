<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
          <img src="../images/logo.png" class="logo" alt="">
        </a>
      </div>

      <!-- 네비게이션 바 메뉴 -->
      <div class="collapse navbar-collapse" id="navbar-menu">
        <ul class="nav navbar-nav navbar-left">
          <li><a href="../main/main.do">홈</a></li>                    
          <li><a href="../search/searchcompany.do">기업 정보</a></li>
          <li><a href="../ad/ad.do">채용 공고</a></li>
          <li><a href="#calendar">채용 달력</a></li>
          <li><a href="../freeboard/freeboard.do">자유게시판</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li><a href="#">로그인</a></li>                    
        </ul>
      </div>
    </div>
  </nav>
</body>
</html>