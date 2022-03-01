<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="sist.com.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/freeboard/insert.css">
<script>
	$(function() {
		$('#insert-btn').click(function() {
			let category = $('#category').val();
			if (category.trim() == "none") {
				$('#category').focus();
				return;
			}

			let title = $('#title').val();
			if (title.trim() == "") {
				$('#title').focus();
				return;
			}

			let content = $('#content').val();
			if (content.trim() == "") {
				$('#content').focus();
				return;
			}

			alert("게시물이 수정되었습니다");
			$('#update-form').submit();
		})
	})
	
    $(function() {
        $('#cancel-btn').click(function() {
            history.back();
        })
    })
</script>
</head>
<body>
  <div class="container container-pad">
    <div class="row m-top-40">
      <div class="col-md-12 roomy-10">
        <h4>
          <b>글 수정</b>
        </h4>
      </div>
    </div>

    <form method=post action="../freeboard/update_ok.do" id="update-form">
      <input type="hidden" name="bid" value=${detail.board_id }>
      <div class="row roomy-10">
        <div class="col-sm-3">
          <select id="category" class="form" name="category">
            <option value="none">카테고리 선택</option>
            <option value="고민" <c:if test="${detail.board_category eq '고민'}">selected</c:if>>고민</option>
            <option value="잡담" <c:if test="${detail.board_category eq '잡담'}">selected</c:if>>잡담</option>
            <option value="스터디" <c:if test="${detail.board_category eq '스터디'}">selected</c:if>>스터디</option>
            <option value="추천" <c:if test="${detail.board_category eq '추천'}">selected</c:if>>추천</option>
          </select>
        </div>
      </div>
      <div class="row roomy-10">
        <div class="col-sm-12">
          <input type="text" id="title" class="form" name="title" placeholder="제목 입력" value="${detail.board_title }">
        </div>
      </div>
      
      <div class="row roomy-10">
        <div class="col-sm-12">
          <textarea id="content" class="form" name="content">${detail.board_content }</textarea>
        </div>
      </div>

      <div class="row roomy-10">
        <div class="col-sm-12 text-right">
            <input type="button" id="insert-btn" class="btn btn-primary" value="수정">
            <input type="button" id="cancel-btn" class="btn btn-default" value="취소">
        </div>
      </div>
    </form>
  </div>

</body>
</html>