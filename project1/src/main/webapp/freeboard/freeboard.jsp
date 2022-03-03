<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="sist.com.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <link rel="stylesheet" href="../css/freeboard/freeboard.css">
  <script type="text/javascript">
    $(function() {
        $.ajax({
            type: 'get',
            url: '../freeboard/freeboardlist.do',
            data : {"tab":"tab1"},
            success: function(result) {
                $('#data').html(result);
            }
        })
        $('.menu').css("cursor", "pointer")
        $('.menu').click(function() {
        	$('.menu').css('background-color', 'white');
        	$(this).css('background-color', '#E7E7E7');
        	let tab = $(this).attr('id');
            $.ajax({
                type : 'get',
                url : '../freeboard/freeboardlist.do',
                data : {"tab":tab},
                success : function(result) {
                    $('#data').html(result);
                }
            })
        })
        $('#tab1').click();
    })
  </script>
</head>
<body>
  <div class="container container-pad">
    <div class="row roomy-10 m-top-40 p-l-15 no-select">
      <h4>
        <i class="fa fa-smile-o" aria-hidden="true"></i>&nbsp;<b>자유게시판</b>
      </h4>
    </div>

    <!-- 게시물 관리 (로그인시에만) -->
    <c:if test="${sessionScope.id!=null }">
      <div class="row p-l-15 no-select">
        <span id="tab1" class="menu">자유게시판</span>
        <span id="tab2" class="menu">내가 쓴 글 관리</span>
        <span id="tab3" class="menu">내가 쓴 댓글 관리</span>
      </div>
    </c:if>
    <div class="row top-border"></div>
    
    <!-- 리스트 상단 -->
    <div class="row top-border"></div>
    <div id="board-top" class="row roomy-10 text-center">
      <div class="col-sm-7">제목</div>
      <div class="col-sm-2">글쓴이</div>
      <div class="col-sm-2">날짜</div>
      <div class="col-sm-1">조회</div>
    </div>
    
    <!-- 게시물 리스트 출력 -->
    <div id="data">
      
    </div>
  </div>
</body>
</html>