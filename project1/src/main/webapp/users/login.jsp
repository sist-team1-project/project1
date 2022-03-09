<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="sist.com.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>Insert title here</title>
  <link rel="stylesheet" href="../css/users/login.css">
  <script>
    Shadowbox.init({
        players:['iframe'],
        overlayOpacity: 0.5
    })
    
    $(function(){
    	$('#idfind').css('cursor','pointer');
  	    $('#idfind').click(function(){
  	      
  	        Shadowbox.open({
  	            content:'../users/idfind.do',
  	            player:'iframe',
  	            width:350,
  	            height:300
  	        })
  	    })
  	    
  	    $('#pwfind').css('cursor','pointer');
        $('#pwfind').click(function(){
          
            Shadowbox.open({
                content:'../users/pwfind.do',
                player:'iframe',
                width:350,
                height:300
            })
        })
    })
  </script>
</head>
<body>
  <!-- 여기부터 -->
  <section>
    <div class="container">
      <div class="middle">
        <c:if test="${sessionScope.id==null }">
          <div class="content fadeInDown">
            <div class="box">자리JOB기에 로그인하여<br>더 많은 재미를 느껴보세요!</div>
            <div class="content-pad">
              <div class="fadeIn first">
                <h4><b>로그인</b></h4>
              </div>
              <!-- Login Form -->
              <form id="login_form" method="post" action="../users/login_ok.do">
                <input type="text" id="id" class="fadeIn second" name="id" placeholder="아이디">
                <input type="password" id="pwd" class="fadeIn third" name="pwd" placeholder="비밀번호">
                <div class="formFooter fadeIn third">
                  <a id="idfind">아이디 찾기</a>&nbsp;&nbsp;&nbsp;<a id="pwfind">비밀번호 찾기</a>
                </div>
                <input type="submit" id="login" class="login fadeIn fourth " value="로그인">
              </form>
              <a type="button" class="join fadeIn fourth" href="../users/join.do">회원가입</a>
              <div class="roomy-10"><small>자리JOB기는 회원님의 익명성을 보장하기 위해 <br>어떠한 개인정보도 노출하지 않습니다.</small></div>
            </div>
          </div>
        </c:if>
        <c:if test="${sessionScope.id!=null }">


        </c:if>
      </div>
    </div>
  </section>
  <!-- 여기까지 직접 작성 -->
</body>
</html>