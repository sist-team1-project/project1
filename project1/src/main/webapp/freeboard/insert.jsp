<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
$(function(){
    $('#insert-btn').click(function(){
        let category = $('#category').val();
        if(category.trim() == "none"){
            $('#category').focus();
            return;
        }
        
        let title = $('#title').val();
        if(title.trim() == "") {
        	$('#title').focus();
            return;
        }
        
        let content = $('#content').val();
        if(content.trim() == "") {
            $('#content').focus();
            return;
        }
        
        alert("게시물이 작성되었습니다");
        $('#insert-form').submit();
    })
})

</script>
</head>
<body>
  <div class="container container-pad">
    <div class="row m-top-70">
      <form method=post action="../freeboard/insert_ok.do" id="insert-form">
        <input type="hidden" name="uid" value=${sessionScope.id }>
        <select id="category" name="category">
          <option value="none">카테고리 선택</option>
          <option value="고민">고민</option>
          <option value="잡담">잡담</option>
          <option value="스터디">스터디</option>
          <option value="추천">추천</option>
        </select>
        <input type="text" id="title" name="title">
        <textarea id="content" name="content"></textarea>
        <input type="button" id="insert-btn" value="글쓰기">
      </form>
    </div>
  </div>
</body>
</html>