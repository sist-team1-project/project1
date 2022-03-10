<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="sist.com.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../css/mypage/mypage.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
  <div class="row m-top-40">
    <div class="col-sm-3">
      <jsp:include page="menu.jsp"></jsp:include>
    </div>
    <div class="col-sm-9 m-top-20">
      <jsp:include page="${content_jsp }"></jsp:include>
    </div>
  </div>
</div>
</body>
</html>