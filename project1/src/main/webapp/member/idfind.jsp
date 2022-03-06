<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<script src="http://code.jquery.com/jquery.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<script>
 $(function(){
    $("#tabs").tabs();
    
    $('#nameBtn').on('click',function(){
        let name=$('#name').val(); 
        if(name.trim()=="")
        {
            $('#name').focus();
            $('#name_find').text("이름을 입력하세요")
            return;
        }
        $.ajax({
            type:'post',
            url:'../member/idfind_result.do',
            data:{"name":name},
            success:function(res) 
            {
                let t=res.trim();
                if(t=='no')
                {
                    $('#name_find').text('이름이 존재하지 않습니다!!')
                }
                else
                {
                    $('#name_find').text(t);
                }
                
            }
        })
    })
    $('#emailBtn').click(function(){
        let email=$('#email').val();
        if(email.trim()=="")
        {
            $('#email').focus();
            $('#email_find').text("이메일을 입력하세요!!");
            return;
        }
        $.ajax({
            type:'post',
            url:'../member/email_result.do',
            data:{"email":email},
            success:function(res)
            {
                let r=res.trim();
                if(r=="no")
                {
                    $('#email_find').text("이메일이 존재하지 않습니다!!");
                }
                else
                {
                    $('#email_find').text(r);
                }
            }
        })
    })
 });
  </script>
</head>
<body>
  <div class="wrapper row3">
  <div id="breadcrumb" class="clear"> 
   </div>
  </div>
  <div class="wrapper row3">
   <div style="height:650px">
       <main class="container clear">
        <h2 class="sectiontitle">아이디 찾기</h2>
        <div id="tabs">
          <ul>
            <li><a href="#tabs-1">이름</a></li>
            <li><a href="#tabs-2">이메일</a></li>
          </ul>
          <div id="tabs-1">
            <p class="inline">
              <input type=text id="name" size=20 class="input-sm">
              <input type=button value="검색" class="btn btn-sm btn-danger"
               id="nameBtn">
            </p>
            <p id="name_find" style="font-size: 15pt;color:red"></p>
          </div>          <div id="tabs-2">

            <p class="inline">
              <input type=text id="email" size=35 class="input-sm">
              <input type=button value="검색" class="btn btn-sm btn-danger"
               id="emailBtn">
            </p>
            <p id="email_find" style="font-size: 15pt;color:red"></p>
        </div>
       </main>
   </div>
  </div>
</body>
</html>