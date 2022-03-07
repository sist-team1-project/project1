<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="../shadow/css/shadowbox.css">
<script type="text/javascript" src="../shadow/js/shadowbox.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
//shadowbox초기화 
Shadowbox.init({
    players:['iframe']
})
$(function(){
    
    $('#postBtn').click(function(){
        // 우편번호 검색 처리 
        new daum.Postcode({
            oncomplete:function(data)
            {
                $('#post').val(data.zonecode)
                $('#addr1').val(data.address)
            }
        }).open()
    })
    $('#joinBtn').click(function(){
        // 회원 가입 처리 (유효성 검사) => NOT NULL , PRIMARY KEY , UNIQUE
        // id 
        let id=$('#join_id').val();
        if(id.trim()=="")
        {
            alert("중복체크 버튼을 클릭하세요!!")
            return;
        }
        // pwd
        let pwd=$('#join_pwd').val()
        if(pwd.trim()=="")
        {
            $('#join_pwd').focus();
            return;
        }
        
        // name
        let name=$('#name').val()
        if(name.trim()=="")
        {
            $('#name').focus();
            return;
        }
        // birthday
        let day=$('#day').val()
        if(day.trim()=="")
        {
            alert("생년월일을 입력하세요!!") //
            return;
        }
        // post 
        let post=$('#post').val()
        if(post.trim()=="")
        {
            alert("우편번호를 검색하세요")
            return;
        }
        // tel
        let tel2=$('#tel2').val()
        if(tel2.trim()=="")
        {
            $('#tel2').focus()
            return;
        }
        // content
        let cont=$('#content').val()
        if(cont.trim()=="")
        {
            $('#content').focus()
            return;
        }
        
        $('#join_frm').submit()
        
    })
})
</script>
</head>
<body>
<div class="container">
  <div class="row">
    <div class="col-sm-3">
      <a href="#"><h3>마이페이지</h3></a>
        <div class="list-group sm-4">
            <a class="list-group-item list-group-item-info text-center font-weight-bold">내 정보</a>
            <a href="../member/join_update.do" class="list-group-item list-group-item-action text-center font-weight-bold">개인정보 수정</a> 
            <a href="../member/join_delete.do" class="list-group-item list-group-item-action text-center font-weight-bold">회원 탈퇴</a> 
            <a href="#" class="list-group-item list-group-item-action text-center font-weight-bold">즐겨찾기 관리</a>
  <div class="wrapper row3">
  <div id="breadcrumb" class="clear"> 
    <!-- ################################################################################################ -->
    <ul>
      <li>정보 수정</li>
    </ul>
    <!-- ################################################################################################ --> 
   </div>
  </div>
  <div class="wrapper row3">
   <main class="container clear">
   <h2 class="sectiontitle">회원 수정</h2>
   <form method="post" action="../member/join_update_ok.do" name="join_frm" id="join_frm">
    <table class="table">
      <tr>
       <td class="text-right" width=15%>아이디</td>
       <td width=85% class="inline">
         <input type=text name=u_id id="join_id" size=15 class="input-sm" readonly
           value="${vo.u_id }"
         >
       </td>
      </tr>
      <tr>
       <td class="text-right" width=15%>비밀번호</td>
       <td width=85% class="inline">
         <input type=password name=u_password id=join_pwd size=15 class="input-sm">
       </td>
      </tr>
      <tr>
       <td class="text-right" width=15%>이름</td>
       <td width=85%>
         <input type=text name=u_name id=name size=15 class="input-sm"
          value="${vo.u_name }"
         >
       </td>
      </tr>
      <tr>
       <td class="text-right" width=15%>성별</td>
       <td width=85% class="inline">
         <input type=radio value="남자" name=u_gender ${vo.u_gender=="남자"?"checked":"" }>남자
         <input type=radio value="여자" name=u_gender ${vo.u_gender=="여자"?"checked":"" }>여자
       </td>
      </tr>
      <tr>
       <td class="text-right" width=15%>생년월일</td>
       <td width=85%>
         <input type=date size=20 name=u_birthday class="input-sm" id="day"
          value="${vo.u_birthday }"
         >
       </td>
      </tr>
      <tr>
       <td class="text-right" width=15%>이메일</td>
       <td width=85%>
         <input type=text name=u_email id=email size=50 class="input-sm"
          value="${vo.u_email }"
         >
       </td>
      </tr>
      <tr>
       <td class="text-right" width=15%>우편번호</td>
       <td width=85% class='inline'>
         <input type=text name=u_post id=post size=10 class="input-sm" readonly
           value="${vo.u_post }"
         >
         <input type=button id="postBtn" value="우편번호찾기"
          class="btn btn-sm btn-danger">
       </td>
      </tr>
      <tr>
       <td class="text-right" width=15%>주소</td>
       <td width=85%>
         <input type=text name=u_address1 id=addr1 size=50 class="input-sm" readonly
          value="${vo.u_address1 }"
         >
       </td>
      </tr>
      <tr>
       <td class="text-right" width=15%>상세주소</td>
       <td width=85%>
         <input type=text name=u_address2 id=addr2 size=50 class="input-sm"
          value="${vo.u_address2 }"
         >
       </td>
      </tr>
      <tr>
        <td colspan="2" class="text-center">
         <input type=button class="btn btn-sm btn-primary" value="회원수정"
           id="joinBtn"
         >
         <input type=button class="btn btn-sm btn-danger" value="취소"
          onclick="javascript:history.back()"
         >
        </td>
      </tr>
    </table>
    </form>
   </main>
  </div>
</body>
</html>

