<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
Shadowbox.init({
    players:['iframe']
})
$(function(){
    $('#checkBtn').click(function(){
      
        Shadowbox.open({
            content:'../login/idcheck.do',
            player:'iframe',
            title:'아이디 중복체크',
            width:420,
            height:250
        })
    })
    $('#postBtn').click(function(){
        
        new daum.Postcode({
            oncomplete:function(data)
            {
                $('#post').val(data.zonecode)
                $('#addr1').val(data.address)
            }
        }).open()
    })
    $('#joinBtn').click(function(){
     
        let id=$('#join_id').val();
        if(id.trim()=="")
        {
            alert("중복체크 버튼을 클릭하세요!!")
            return;
        }
       
        let pwd=$('#join_pwd').val()
        if(pwd.trim()=="")
        {
            $('#join_pwd').focus();
            return;
        }
        let pwd1=$('#pwd1').val()
        if(pwd1!=pwd) 
        {
            alert("비밀번호가 틀립니다!!\n다시 입력하세요")
            $('#pwd1').focus();
            return;
        }
        
        let name=$('#name').val()
        if(name.trim()=="")
        {
            $('#name').focus();
            return;
        }
      
        let day=$('#day').val()
        if(day.trim()=="")
        {
            alert("생년월일을 입력하세요!!") 
            return;
        }
         
        let post=$('#post').val()
        if(post.trim()=="")
        {
            alert("우편번호를 검색하세요")
            return;
        }
        
        $('#join_frm').submit()
        
    })
})
</script>
</head>
<body>
  <div class="wrapper row3">
  <div id="breadcrumb" class="clear"> 
    <!-- ################################################################################################ -->
    <ul>
      <li>회원 가입</li>
    </ul>
    <!-- ################################################################################################ --> 
   </div>
  </div>
  <div class="wrapper row3">
   <main class="container clear">
   <form method="post" action="../login/join_ok.do" name="join_frm" id="join_frm">
    <table class="table">
      <tr>
       <td class="text-right" width=15%>아이디</td>
       <td width=85% class="inline">
         <input type=text name=id id="join_id" size=22 class="input-sm" placeholder="아이디 중복체크를 해주세요."readonly >
         <input type=button id="checkBtn" value="아이디중복체크" class="btn btn-sm btn-primary">
         <br><small>아이디는 6~12자로 되어야 합니다.</small>
       </td>
      </tr>
      <tr>
       <td class="text-right" width=15%>비밀번호</td>
       <td width=85% class="inline">
         <input type=password name=pwd id=join_pwd size=22 class="input-sm" placeholder="비밀번호를 입력하세요.">
         &nbsp;&nbsp;재입력:
         <input type=password name=pwd1 id=pwd1 size=22 class="input-sm" placeholder="비밀번호를 입력하세요.">
         <br><small>비밀번호는 8~20자로 되어야 합니다.</small>
       </td>
      </tr>
      <tr>
       <td class="text-right" width=15%>이름</td>
       <td width=85%>
         <input type=text name=name id=name size=22 class="input-sm" placeholder="이름을 입력하세요.">
       </td>
      </tr>
      <tr>
       <td class="text-right" width=15%>생년월일</td>
       <td width=85%>
         <input type=date size=22 name=birthday class="input-sm" id="day">
       </td>
      </tr>
      <tr>
      <tr>
       <td class="text-right" width=15%>성별</td>
       <td width=85% class="inline">
         <input type=radio value="남자" name=gender checked="checked">남자
         <input type=radio value="남자" name=gender>여자
       </td>
      </tr>
      <tr>
       <td class="text-right" width=15%>이메일</td>
       <td width=85%>
         <input type=text name=email id=email size=50 class="input-sm" placeholder="이메일 주소를 입력하세요.">
       </td>
      </tr>
      <tr>
       <td class="text-right" width=15%>우편번호</td>
       <td width=85% class='inline'>
         <input type=text name=post id=post size=10 class="input-sm" readonly>
         <input type=button id="postBtn" value="우편번호 찾기"
          class="btn btn-sm btn-danger">
       </td>
      </tr>
      <tr>
       <td class="text-right" width=15%>주소</td>
       <td width=85%>
         <input type=text name=addr1 id=addr1 size=50 class="input-sm" readonly>
       </td>
      </tr>
      <tr>
       <td class="text-right" width=15%>상세주소</td>
       <td width=85%>
         <input type=text name=addr2 id=addr2 size=50 class="input-sm">
       </td>
      </tr>
      <tr>
       <td class="text-right" width=22%>비밀번호 찾기 질문/답변</td>
       <td width=85% class="inline">
       <select name=question id=question>
        <option value="기억에 남는 추억의 장소는?" >기억에 남는 추억의 장소는? </option><option value="자신의 인생 좌우명은?" >자신의 인생 좌우명은? </option><option value="자신의 보물 제1호는?" >자신의 보물 제1호는? </option><option value="가장 기억에 남는 선생님 성함은?" >가장 기억에 남는 선생님 성함은? </option><option value="타인이 모르는 자신만의 신체비밀이 있다면?" >타인이 모르는 자신만의 신체비밀이 있다면? </option><option value="추억하고 싶은 날짜가 있다면?" >추억하고 싶은 날짜가 있다면? </option><option value="받았던 선물 중 기억에 남는 독특한 선물은?" >받았던 선물 중 기억에 남는 독특한 선물은? </option><option value="유년시절 가장 생각나는 친구 이름은?" >유년시절 가장 생각나는 친구 이름은? </option><option value="인상 깊게 읽은 책 이름은?" >인상 깊게 읽은 책 이름은? </option><option value="읽은 책 중에서 좋아하는 구절이 있다면?" >읽은 책 중에서 좋아하는 구절이 있다면? </option><option value="자신이 두번째로 존경하는 인물은?" >자신이 두번째로 존경하는 인물은? </option><option value="친구들에게 공개하지 않은 어릴 적 별명이 있다면?" >친구들에게 공개하지 않은 어릴 적 별명이 있다면? </option><option value="초등학교 때 기억에 남는 짝꿍 이름은?" >초등학교 때 기억에 남는 짝꿍 이름은? </option><option value="다시 태어나면 되고 싶은 것은?" >다시 태어나면 되고 싶은 것은? </option><option value="내가 좋아하는 캐릭터는?" >내가 좋아하는 캐릭터는? </option></select>
      <br><input type="text" name=answer id=answer size=38 placeholder="답변을 입력하세요.">
       </tr>
      <tr>
        <td colspan="2" class="text-center">
         <input type=button class="btn btn-sm btn-primary" value="회원가입"
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









