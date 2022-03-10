<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="../css/style.css">
  <link rel="stylesheet" href="../css/users/idpwfind.css">
  <script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>

<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<!-- 
     Shadow는 서버를 거쳐서 데이터 읽고 실행 => 종료 
 -->
<script type="text/javascript">
    $(function(){
    	
        $('#email-check-btn').click(function(){
        	$('#ok').hide();
        	
            let email = $('#email').val();
            if(email.trim() == "") {
                $('#email').focus();
                return;
            }
            
            if(validateEmail(email) == false) {
                return;
            }
            
            $.ajax({
                type:'POST',
                url:'../users/emailcheck_result.do',
                data:{"email" : email},
                success:function(result) {
                    let count=result.trim();
                    if(count==0) {
                    	$('#emailcheck').removeAttr('class').addClass('m-top-40 text-center');
                        $('#print').html('<font color=blue>'+email+'는(은) 사용가능합니다</font>');
                        $('#check').hide();
                        $('#ok').show();
                    }
                    else {
                    	$('#emailcheck').removeAttr('class').addClass('m-top-40 text-center');
                        $('#print').html('<font color=red>'+email+'는(은) 사용중입니다</font>')
                    }
                    
                }
            })
        })
        
        $('#ok-btn').click(function(){
            parent.user_form.email.value=$('#email').val();
            parent.Shadowbox.close()
        })
        
        // 이메일 재입력 시도시 확인버튼 사라지게
        $('#email').focus(function(){
        	$('#emailcheck').removeAttr('class').addClass('m-top-60 text-center');
        	$('#print').html('')
            $('#email').val('');
            $('#ok').hide();
            $('#check').show();
        })
    })
    
    function validateEmail(email) {
    	
        var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
        if(!re.test(email)) {
        	alert("올바르지 않은 이메일 형식입니다.")
        	return false;
        } else {
        	return true;
        }
        
    }
</script>
</head>
<body>
  <div id="emailcheck" class="m-top-60 text-center">
    <div><h4><b>이메일 중복확인</b></h4></div>
    <div class=roomy-10><b>이메일:</b> <input type="text" size=20 id="email" placeholder="이메일 입력"></div>
    <div id="check" class=roomy-10><input type="button" class="btn btn-primary" id="email-check-btn" value="중복 검사"></div>
    <div id="ok" class="roomy-10"><input type="button" id="ok-btn" class="btn btn-primary" value="확인"></div>
    <div id="print"></div>
  </div>
</body>
</html>