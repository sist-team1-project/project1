<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
    $(function() {
        $('#pw-find-btn').click(function() {
        	let id = $('#id').val();
            let pwd = $('#pwd1').val();
            if (pwd.trim() == "") {
                $('#pwd1').focus();
                return;
            }
            
            let pwd2 = $('#pwd2').val();
            if (pwd2.trim() == "") {
                $('#pwd2').focus();
                return;
            }
            
            if(pwd != pwd2) {
          	    alert("비밀번호가 일치하지 않습니다.");
          	    $('#pwd1').val('');
          	    $('#pwd2').val('');
          	    return;
            }
            
            if(validatePassword(pwd) == false) {
                return;
            }
            
            $.ajax({
                type : 'post',
                url : '../users/pwreset_ok.do',
                data : $('#pwreset-form').serialize(),
                success : function(result) {
                	alert("비밀번호가 재설정되었습니다");
                	parent.Shadowbox.close()
                }
            })
        })
        $('#ok-btn').click(function() {
            parent.Shadowbox.close()
        })
    });
    
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
<div><h4><b>비밀번호 재설정</b></h4></div>
<form method="post" id="pwreset-form">
  <input type=hidden id="id" name="id" value="${id }">
  <div class="roomy-10">
    <div><b>비밀번호:</b> <input type=password size=20 id="pwd1" name="pwd" placeholder="비밀번호 입력"></div>
    <div>&nbsp;&nbsp;&nbsp;<b>재입력:</b> <input type=password size=20 id="pwd2" placeholder="비밀번호 재입력"></div>
  </div>
  <small> 8~20자 사이 및 영문+숫자</small>
  <div class=roomy-10><input type="button" class="btn btn-primary" id="pw-find-btn" value="업데이트"></div>
</form>