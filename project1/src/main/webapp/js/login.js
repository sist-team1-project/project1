$(function(){
    $('#login').click(function(){
        let id = $('#id').val();
         if(id.trim() == ""){
            $('#id').focus();
            return;
        } 
        let pwd = $('#pwd').val();
         if(pwd.trim() == ""){
            $('#pwd').focus();
            return;
        } 
        //입력된 경우 데이터 전송
        $.ajax({
            type:'post',
            url:'../users/login_result.do',
            data:{"id":id, "pwd":pwd},
            success:function(res){
                let result = res.trim();
                if(result == 'FAIL'){
                    alert(result);
                    alert("로그인 정보가 올바르지 않습니다");
                    $('#id').val("");
                    $('#pwd').val("");
                    $('#id').focus();
                }else{
                    //로그인 성공
                    location.href="../main/main.do";
                }
            }
        })
    })
})