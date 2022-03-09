<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <link rel="stylesheet" href="../css/users/join.css">
  <script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
  <script type="text/javascript" src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  <script type="text/javascript">
  
    Shadowbox.init({
        players:['iframe'],
        overlayOpacity: 0.5
    })
    
    $(function(){
        
        $('#id-check-btn').click(function() {
            Shadowbox.open({
                content:'../users/idcheck.do',
                player:'iframe',
                width:350,
                height:270
            })
        })
        
        $('#email-check-btn').click(function() {
            Shadowbox.open({
                content:'../users/emailcheck.do',
                player:'iframe',
                width:350,
                height:270
            })
        })
        
        $('#post-btn').click(function() {
            new daum.Postcode({
                oncomplete:function(data) {
                    $('#post').val(data.zonecode)
                    $('#address1').val(data.address)
                }
            }).open()
        })
        
        $('#join-btn').click(function() {
         
            let id = $('#id').val();
            if(id.trim() == "") {
                alert("아이디를 중복체크를 해주세요")
                return;
            }
            
            let pwd = $('#password').val()
            if(pwd.trim() == "") {
                $('#password').focus();
                return;
            }
            
            let pwd2 = $('#password2').val()
            if(pwd2 != pwd)  {
            	alert("비밀번호가 일치하지 않습니다.");
                $('#pwd2').focus();
                return;
            }
            
            if(validatePassword(pwd) == false) {
            	return;
            }
            
            let name = $('#name').val()
            if(name.trim() == "") {
                $('#name').focus();
                return;
            }
          
            let birthday = $('#birthday').val()
            if(birthday.trim() == "") {
                alert("생년월일을 입력하세요") 
                return;
            }
            
            let email = $('#email').val()
            if(email.trim() == "") {
                alert("이메일을 입력하세요")
                return;
            }
            
            let post = $('#post').val()
            if(post.trim() == "") {
                alert("우편번호를 검색하세요")
                return;
            }
                        
            let answer = $('#answer').val()
            if(answer.trim() == "") {
                alert("비밀번호 찾기 답변을 입력하세요")
                return;
            }
            
            $('#join_form').submit()
        })
        
        // 아이디 재입력 시도시 확인버튼 사라지게
        $('#id').focus(function(){
            $('#id').val('');
            $('#ok').hide();
        })
    })
    
    function validatePassword(password){
    	var num = password.search(/[0-9]/g);
    	var eng = password.search(/[a-z]/ig);
    	
    	if(password.length < 8 || password.length > 20){
    	    alert("비밀번호를 8자리 ~ 20자리 이내로 입력해주세요.");
    		return false;
    	} else if(password.search(/\s/) != -1){
    		alert("비밀번호는 공백 없이 입력해주세요.");
    		return false;
    	} else if(num < 0 || eng < 0){
    		alert("비밀번호는 영문,숫자를 혼합하여 입력해주세요.");
    		return false;
    	} else {
    		return true;
    	}
    }
  </script>
</head>
<body>
  <!-- 여기부터 -->
  <section>
    <div class="container">
      <div class="middle m-top-40">
        <div class="content fadeInDown">
          <div class="text-center fadeIn first"><h4><i class="fa fa-user-circle" aria-hidden="true"></i>&nbsp;<b>회원가입</b></h4></div>
          <!-- Login Form -->
          <div class="text-left">
          
            <!--   폼   -->
            <form id="join_form" method="post" action="../users/join_ok.do">
              <!--   아이디   -->
              <div class="roomy-10 fadeIn second">
                <div>아이디</div>
                <input type="text" name="id" id="id" placeholder="아이디 중복 확인을 해주세요" readonly>
                <input type="button" id="id-check-btn" class="btn btn-primary" value="중복 확인">
                <small> 6~12자 사이 및 영문+숫자</small>
              </div>
              <!-- -------- -->
              
              <!--   비밀번호   -->
              <div class="roomy-10 fadeIn second">
                <div>비밀번호</div>
                <input type="password" name="password" id="password" placeholder="비밀번호를 입력하세요">
                <input type="password" name="password2" id="password2" placeholder="비밀번호를 입력하세요">
                <small> 8~20자 사이 및 영문+숫자</small>
              </div>
              <!-- -------- -->
              
              <!--   이름   -->
              <div class="roomy-10 fadeIn second">
                <div>이름</div>
                <input type="text" name="name" id="name" placeholder="이름을 입력하세요.">
              </div>
              <!-- -------- -->
              
              <!--   이메일   -->
              <div class="roomy-10 fadeIn third">
                <div>이메일</div>
                <input type="text" name="email" id="email" placeholder="이메일 주소 중복 확인을 해주세요" readonly>
                <input type="button" id="email-check-btn" class="btn btn-default" value="중복 확인">
              </div>
              <!-- -------- -->
              
              <!--  생년월일  -->
              <div class="roomy-10 fadeIn third">
                <div>생년월일</div>
                <input type="date" name="birthday" id="birthday">
              </div>
              <!-- -------- -->
              
              <!--   성별   -->
              <div class="roomy-10 fadeIn third">
                <div>성별</div>
                <label class="radio-inline"><input class="form-check-input" type="radio" value="남자" name=gender checked="checked"> 남자</label>
                <label class="radio-inline"><input class="form-check-input"  type="radio" value="여자" name=gender> 여자</label>
              </div>
              <!-- -------- -->
                            
              <!--   주소   -->
              <div class="roomy-10 fadeIn fourth">
                <div>우편번호</div>
                <input type="text" name="post" id="post" placeholder="우편번호 찾기를 해주세요" readonly>
                <input type="button" id="post-btn" class="btn btn-primary" value="우편번호 찾기">
                <div>주소</div>
                <input type="text" name="address1" id="address1" readonly>
                <div>상세주소</div>
                <input type="text" name="address2" id="address2">
              </div>
              <!-- -------- -->
              
              <!-- 비밀번호 찾기 질문 답변 -->
              <div class="roomy-10 fadeIn fourth">
                <div>비밀번호 찾기 질문/답변</div>
                <select name=question id=question>
                  <option value="기억에 남는 추억의 장소는?" >기억에 남는 추억의 장소는?</option>
                  <option value="자신의 인생 좌우명은?" >자신의 인생 좌우명은?</option>
                  <option value="자신의 보물 제1호는?" >자신의 보물 제1호는?</option>
                  <option value="가장 기억에 남는 선생님 성함은?" >가장 기억에 남는 선생님 성함은?</option>
                  <option value="타인이 모르는 자신만의 신체비밀이 있다면?" >타인이 모르는 자신만의 신체비밀이 있다면?</option>
                  <option value="추억하고 싶은 날짜가 있다면?" >추억하고 싶은 날짜가 있다면? </option>
                  <option value="받았던 선물 중 기억에 남는 독특한 선물은?" >받았던 선물 중 기억에 남는 독특한 선물은?</option>
                  <option value="유년시절 가장 생각나는 친구 이름은?" >유년시절 가장 생각나는 친구 이름은?</option>
                  <option value="인상 깊게 읽은 책 이름은?" >인상 깊게 읽은 책 이름은? </option>
                  <option value="읽은 책 중에서 좋아하는 구절이 있다면?" >읽은 책 중에서 좋아하는 구절이 있다면?</option>
                  <option value="자신이 두번째로 존경하는 인물은?" >자신이 두번째로 존경하는 인물은?</option>
                  <option value="친구들에게 공개하지 않은 어릴 적 별명이 있다면?" >친구들에게 공개하지 않은 어릴 적 별명이 있다면?</option>
                  <option value="초등학교 때 기억에 남는 짝꿍 이름은?" >초등학교 때 기억에 남는 짝꿍 이름은?</option>
                  <option value="다시 태어나면 되고 싶은 것은?" >다시 태어나면 되고 싶은 것은?</option>
                  <option value="내가 좋아하는 캐릭터는?" >내가 좋아하는 캐릭터는? </option>
                </select>
                <input type="text" name=answer id=answer placeholder="답변을 입력하세요.">
              </div>
              <!-- -------- -->
              
              <!--  회원가입 버튼   -->
              <div class="fadeIn fourth">
                <input type="button" id="join-btn" class="btn btn-primary" value="회원가입">
                <a href="../users/login.do" id="cancel-btn" class="btn btn-default">취소</a>
              </div>
              <!-- -------- -->
            </form>
            <!-- -------- -->
            
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- 여기까지 직접 작성 -->
</body>
</html>