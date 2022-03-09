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
    $(function(){
        $('#id-check-btn').click(function(){
        	$('#ok').hide();
        	
            let id = $('#id').val();
            if(id.trim() == "") {
                $('#id').focus();
                return;
            }
            
            if(validateId(id) == false) {
                return;
            }
            
            $.ajax({
                type:'POST',
                url:'../users/idcheck_result.do',
                data:{"id" : id},
                success:function(result) {
                    let count=result.trim();
                    if(count==0) {
                    	$('#idcheck').removeAttr('class').addClass('m-top-40 text-center');
                        $('#print').html('<font color=blue>'+id+"는(은) 사용가능합니다</font>");
                        $('#check').hide();
                        $('#ok').show();
                    }
                    else {
                    	$('#idcheck').removeAttr('class').addClass('m-top-40 text-center');
                        $('#print').html('<font color=red>'+id+"는(은) 사용중입니다</font>")
                    }
                    
                }
            })
        })
        
        $('#ok-btn').click(function(){
            parent.join_form.id.value=$('#id').val()
            parent.Shadowbox.close()
        })
        
        $('#id').focus(function(){
            $('#idcheck').removeAttr('class').addClass('m-top-60 text-center');
            $('#print').html('')
            $('#id').val('');
            $('#ok').hide();
            $('#check').show();
        })
    })

    function validateId(id){
    	var num = id.search(/[0-9]/g);
        var eng = id.search(/[a-z]/ig);
        
        if(id.length < 6 || id.length > 12){
            alert("아이디는 6자리 ~ 12자리 이내로 입력해주세요.");
            return false;
        } else if(id.search(/\s/) != -1){
            alert("아이디는 공백 없이 입력해주세요.");
            return false;
        } else if(num < 0 || eng < 0){
            alert("아이디는 영문,숫자를 혼합하여 입력해주세요.");
            return false;
        } else {
            return true;
        }
    }
  </script>
</head>
<body>
  <div id="idcheck" class="m-top-60 text-center">
    <div><h4><b>아이디 중복확인</b></h4></div>
    <div class=roomy-10><b>아이디:</b> <input type="text" size=20 id="id" placeholder="아이디 입력"></div>
    <div id="check" class=roomy-10><input type="button" class="btn btn-primary" id="id-check-btn" value="중복 검사"></div>
    <div id="ok" class="roomy-10"><input type="button" id="ok-btn" class="btn btn-primary" value="확인"></div>
    <div id="print"></div>
  </div>
</body>
</html>