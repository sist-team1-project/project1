<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="sist.com.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../css/users/mypage.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
  <div class="row">
    <div class="col-sm-3">
      <a href="#"><h3>마이페이지</h3></a>
        <div class="list-group sm-4">
            <a class="list-group-item list-group-item-info text-center font-weight-bold">내 정보</a><a href="#"
                        class="list-group-item list-group-item-action text-center font-weight-bold">개인정보 수정</a> <a href="#"
                        class="list-group-item list-group-item-action text-center font-weight-bold">즐겨찾기 관리</a>
     </div>
   </div>
<div class="container">
<div class="col-sm-4">
<h3>마이페이지 / 내 정보</h3>
  <div class="col-md-3">이름</div>
  <div class="col-md-9">${user.u_id }</div>
  <div class="col-md-3">성별</div>
  <div class="col-md-9">${user.u_gender }</div>
  <div class="col-md-3">이메일</div>
  <div class="col-md-9">${user.u_email }</div>
  <div class="col-md-3">주소</div>
  <div class="col-md-9">${user.u_address1 }</div>
</div>
</div>
</div>
</div>



</body>
</html>