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
          	  alert("비밀번호가 다릅니다");
            }
            
            $.ajax({
                type : 'post',
                url : '../users/pwreset_ok.do',
                data : {
                    "id" : id,
                    "pwd" : pwd
                },
                success : function(result) {
                	alert("비밀번호가 재설정되었습니다");
                	parent.Shadowbox.close()
                }
            })
        })
    });
</script>
<div class=""><h4><b>비밀번호 재설정</b></h4></div>
<form method="post" action="../users/pwreset_ok.do" id="pwreset-form">
  <input type=hidden id="id" name="id" value="${id }">
  <div class=roomy-10>비밀번호: <input type=password size=20 id="pwd1" name="pwd" placeholder="비밀번호 입력"></div>
  <div class=roomy-10>&nbsp;&nbsp;&nbsp;재입력: <input type=password size=20 id="pwd2" placeholder="비밀번호 재입력"></div>
  <div class=roomy-10><input type="button" class="btn btn-primary" id="pw-find-btn" value="업데이트"></div>
</form>