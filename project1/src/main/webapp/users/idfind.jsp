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
  <script type="text/javascript">
  
	$(function() {
		
		$('#id-find-btn').click(function() {
			
			let name = $('#name').val();
            if (name.trim() == "") {
                $('#name').focus();
                return;
            }
            
            let email = $('#email').val();
            if (email.trim() == "") {
                $('#email').focus();
                return;
            }
            
            $.ajax({
                type : 'post',
                url : '../users/idfind_result.do',
                data : {
                    "name" : name,
                    "email" : email
                },
                success : function(result) {
                    let res = result.trim();
                    if (res == 'no') {
                        $('#idfind').html('<div class="m-top-100">해당 정보로 계정이 존재하지 않습니다.</div>');
                    } else {
                        $('#idfind').html('<div class="m-top-80">검색된 아이디는<br><b>' + res + '</b><br>입니다.</div>');
                    }
                    $('#ok').show();
                }
            })
		})
		
		$('#ok-btn').click(function() {
			parent.Shadowbox.close()
        })
	});
	
  </script>
</head>
<body>
  <div class="m-top-40 text-center">
    <div id="idfind">
      <div class=""><h4><b>아이디찾기</b></h4></div>
      <div class=roomy-10>&nbsp;&nbsp;&nbsp;&nbsp;이름: <input type=text size=20 id="name" placeholder="이름 입력"></div>
      <div class=roomy-10>이메일: <input type="text" size=20 id="email" placeholder="이메일 입력"></div>
      <div class=roomy-10>
        <input type="button" class="btn btn-primary" id="id-find-btn" value="아이디 찾기">
      </div>
    </div>
    <div id="ok">
      <div class="roomy-10"><input type="button" id="ok-btn" class="btn btn-default" value="확인"></div>
    </div>
  </div>
</body>
</html>