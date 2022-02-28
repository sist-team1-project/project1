<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="sist.com.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <link rel="stylesheet" href="../css/freeboard_detail.css">
</head>
<body>

  <div class="container container-pad">

    <div class="row m-top-40">
      <div class="col-sm-12 roomy-10">
        <h4><b>[${detail.board_category }]&nbsp;&nbsp;${detail.board_title }</b></h4>
      </div>
    </div>
    <div class="row roomy-10">
      <div class="col-xs-12">
        ${detail.u_id }&nbsp;&nbsp;${detail.board_date }&nbsp;&nbsp;${detail.board_visits }
      </div>
    </div>
    <hr>
    <div class="row roomy-40">
      <div class="col-sm-12">
        ${detail.board_content }
      </div>
    </div>
    
    <div class="row roomy-10">
      <div class="col-md-8">
        <form>
          <textarea></textarea>
          <button type="submit">댓글<br>작성</button>
        </form>
      </div>
    </div>
    <div class="row roomy-10">
      <div class="col-sm-12">
        <a>수정</a>
        <a>삭제</a>
        <a>목록</a>
      </div>
    </div>
  </div>
</body>
</html>