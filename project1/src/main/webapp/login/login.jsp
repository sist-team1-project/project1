<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="sist.com.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <link rel="stylesheet" href="../css/login.css">
</head>
<body>
  <!-- 여기부터 -->
  <div class="wrapper fadeInDown zero-raduis">
   
    <div id="formContent">
      <div class="fadeIn first">
        <!-- <img src="http://danielzawadzki.com/codepen/01/icon.svg" id="icon" alt="User Icon" /> -->
        <h2 class="my-5">로그인</h2>
      </div>
      <c:if test="${sessionScope.id==null }">
        <!-- Login Form -->
        <form method="post" action="../member/login.do">
          <input type="text" id="id" class="fadeIn second zero-raduis" name="id" placeholder="아이디"> <input type="password" id="password" class="fadeIn third zero-raduis" name="pwd" placeholder="비밀번호">
          <div id="formFooter">
            <a class="underlineHover" href="#">아이디 찾기 </a> <a class="underlineHover" href="#">비밀번호 찾기</a>
          </div>

          <input type="submit" class="fadeIn fourth zero-raduis" value="로그인"> <input type="button" class="fadeIn fourth zero-raduis pc" value="회원가입">

        </form>
      </c:if>
      <c:if test="${sessionScope.id!=null }">



      </c:if>
    </div>
  </div>
  <!-- 여기까지 직접 작성 -->
</body>
</html>