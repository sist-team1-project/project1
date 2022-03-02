<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="sist.com.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/freeboard/insert.css">
<script type="text/javascript" src="../js/freeboard.js"></script>
</head>
<body>
  <div class="container container-pad">
    <div class="row m-top-40">
      <div class="col-md-12 roomy-10">
        <h4>
          <b>글 작성</b>
        </h4>
      </div>
    </div>

    <form method=post action="../freeboard/insert_ok.do" id="insert-form">
      <input type="hidden" name="uid" value="${sessionScope.id }">
      <div class="row roomy-10">
        <div class="col-sm-3">
          <select id="category" class="form" name="category">
            <option value="none">카테고리 선택</option>
            <option value="고민">고민</option>
            <option value="잡담">잡담</option>
            <option value="스터디">스터디</option>
            <option value="추천">추천</option>
          </select>
        </div>
      </div>
      <div class="row roomy-10">
              <div class="col-sm-12">
          <input type="text" id="title" class="form" name="title" placeholder="제목 입력">
        </div>
      </div>
      
      <div class="row roomy-10">
        <div class="col-sm-12">
          <textarea id="content" class="form" name="content"></textarea>
        </div>
      </div>

      <div class="row roomy-10">
        <div class="col-sm-12 text-right">
            <input type="button" id="insert-btn" class="btn btn-primary" value="글쓰기">
            <input type="button" id="cancel-btn" class="btn btn-default" value="취소">
        </div>
      </div>
    </form>
  </div>
</body>
</html>