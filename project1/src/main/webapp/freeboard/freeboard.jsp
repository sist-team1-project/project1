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
            type: 'post',
            url: '../freeboard/freeboardlist.do',
            success: function(result) {
                $('#data').html(result);
            }
        })
        $('#menu1').css("cursor", "pointer")
        $('#menu2').css("cursor", "pointer")
        $('#menu3').css("cursor", "pointer")
        
        $('#menu1').click(function() {
            $('.selected').attr("class", "unselected");
            $('#menu1').removeClass("unselected");
            $("#menu1").addClass("selected");
            
            $.ajax({
                type: 'post',
                url: '../freeboard/freeboardlist.do',
                success: function(result) {
                    $('#data').html(result);
                }
            })
        })
        
        $('#menu2').click(function() {
        	$('.selected').attr("class", "unselected");
        	$('#menu2').removeClass("unselected");
        	$("#menu2").addClass("selected");
            $.ajax({
                type: 'post',
                url: '../freeboard/freeboardlist.do',
                data : {"sub":"1"},
                success: function(result) {
                    $('#data').html(result);
                }
            })
        })
        
        $('#menu3').click(function() {
            $('.selected').attr("class", "unselected");
            $('#menu3').removeClass("unselected");
            $("#menu3").addClass("selected");
            $.ajax({
                type: 'post',
                url: '../freeboard/freeboardlist.do',
                data : {"sub":"2"},
                success: function(result) {
                    $('#data').html(result);
                }
            })
        })
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
        <span id="menu1" class="selected">자유게시판</span>
        <span id="menu2" class="unselected">내가 쓴 글 관리</span>
        <span id="menu3" class="unselected">내가 쓴 댓글 관리</span>
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
    
    <div id="data">
      
    </div>
  </div>
</body>
</html>