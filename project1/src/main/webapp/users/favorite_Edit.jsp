<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="sist.com.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/users/favorite.css">
</head>
<body>
  <div class="container container-pad">
    <div class="row m-top-40">
      <div class="col-sm-3 text-center">
        <h3>&nbsp;</h3>
        <div class="list-group sm-4">
            <a class="list-group-item list-group-item-info text-center font-weight-bold">내 정보</a>
            <a href="../users/update.do"class="list-group-item list-group-item-action text-center font-weight-bold">개인정보 수정</a> 
            <a href="../users/delete.do"class="list-group-item list-group-item-action text-center font-weight-bold">회원 탈퇴</a>
            <a href="../users/favorite.do" class="list-group-item list-group-item-action text-center font-weight-bold">즐겨찾기 관리</a>
        </div>
      </div>
      <div class="col-sm-9">
        <div class="row">
          <h3 class="text-left">즐겨찾기 관리</h3>
          <div class="col-md-3 text-center m-bottom-20 title-deco">회사명</div>
          <div class="col-md-5 text-center m-bottom-20 title-deco">채용제목</div>
          <div class="col-md-2 text-center m-bottom-20 title-deco">마감일</div>
          <div class="col-md-2 text-center m-bottom-20 title-deco">&nbsp;</div>
        </div>
        <div class="row">

          <c:forEach var="f" items="${adList }" varStatus="status">
            <div class="row content">

              <div class="col-md-3 m-bottom-20">${c_name[status.index] }</div>
              <div class="col-md-5 m-bottom-20">${f.ad_title }</div>
              <div class="col-md-2 m-bottom-20">${f.ad_end }</div>
              <div class="col-sm-2 m-bottom-20">
                <a href="../favorite/delete.do?fid=${fid_list[status.index] }" class="btn btn-danger btn-sm">즐겨찾기 해제</a>
              </div>

            </div>
          </c:forEach>
        </div>
      </div>
    </div>
  </div>

</body>
</html>