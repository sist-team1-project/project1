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
		$('#pw-find-btn').click(function() {
			let name = $('#name').val();
            if (name.trim() == "") {
                $('#name').focus();
                return;
            }
            
            let id = $('#id').val();
            if (id.trim() == "") {
                $('#id').focus();
                return;
            }
            
            $.ajax({
                type : 'post',
                url : '../users/pwfind_result.do',
                data : {
                	"name" : name,
                    "id" : id
                },
                success : function(result) {
                    let res = result.trim();
                    if (res == 'no') {
                        $('#pwdfind').html('<div class="m-top-120">해당 정보로 계정이 존재하지 않습니다.</div>');
                        $('#ok').show();
                    } else {
                    	$.ajax({
                            type : 'post',
                            url : '../users/pwfind2.do',
                            data : {
                            	"id" : id,
                            	"question" : result
                            },
                            success : function(result2) {
                            	$('#pwdfind').html(result2);
                            }
                        })
                    }
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
    <div id="pwdfind">
      <div class=""><h4><b>비밀번호 찾기</b></h4></div>
      <div class=roomy-10>&nbsp;&nbsp;&nbsp;&nbsp;<b>이름:</b> <input type=text size=20 id="name" placeholder="이름 입력"></div>
      <div class=roomy-10><b>아이디:</b> <input type=text size=20 id="id" placeholder="아이디 입력"></div>
      <div class=roomy-10><input type="button" class="btn btn-primary" id="pw-find-btn" value="비밀번호 찾기"></div>
    </div>
    <div id="ok">
      <div class="roomy-10"><input type="button" id="ok-btn" class="btn btn-default" value="확인"></div>
    </div>
  </div>
</body>
</html>