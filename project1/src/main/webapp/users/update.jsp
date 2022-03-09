<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/users/join.css">
<link rel="stylesheet" href="../shadow/css/shadowbox.css">
<script type="text/javascript" src="../shadow/js/shadowbox.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript"
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
	Shadowbox.init({
		players : [ 'iframe' ]
	})
	$(function() {

		$('#postBtn').click(function() {
			new daum.Postcode({
				oncomplete : function(data) {
					$('#post').val(data.zonecode)
					$('#addr1').val(data.address)
				}
			}).open()
		})
		$('#joinBtn').click(function() {
			let id = $('#join_id').val();
			if (id.trim() == "") {
				alert("중복체크 버튼을 클릭하세요!!")
				return;
			}
			let pwd = $('#join_pwd').val()
			if (pwd.trim() == "") {
				$('#join_pwd').focus();
				return;
			}
			let name = $('#name').val()
			if (name.trim() == "") {
				$('#name').focus();
				return;
			}
			let day = $('#day').val()
			if (day.trim() == "") {
				alert("생년월일을 입력하세요!!")
				return;
			}
			let post = $('#post').val()
			if (post.trim() == "") {
				alert("우편번호를 검색하세요")
				return;
			}
			let tel2 = $('#tel2').val()
			if (tel2.trim() == "") {
				$('#tel2').focus()
				return;
			}
			let cont = $('#content').val()
			if (cont.trim() == "") {
				$('#content').focus()
				return;
			}

			$('#join_frm').submit()

		})
	})
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-3">
				<a href="#"><h3>마이페이지</h3></a>
				<div class="list-group sm-4">
					<a
						class="list-group-item list-group-item-info text-center font-weight-bold">내
						정보</a> <a href="../users/update.do"
						class="list-group-item list-group-item-action text-center font-weight-bold">개인정보
						수정</a> <a href="../users/delete.do"
						class="list-group-item list-group-item-action text-center font-weight-bold">회원
						탈퇴</a> <a href="../users/favorite.do"
						class="list-group-item list-group-item-action text-center font-weight-bold">즐겨찾기
						관리</a>
					<div class="wrapper row3">
						<div id="breadcrumb" class="clear">
							<!-- ################################################################################################ -->
							<ul>
								<li>정보 수정</li>
							</ul>
							<!-- ################################################################################################ -->
						</div>
					</div>
					<div class="wrapper row3">
						<main class="container clear">
							<h2 class="sectiontitle">회원 수정</h2>
							<form method="post" action="../users/update_ok.do"
								name="join_frm" id="join_frm">
								<div class="roomy=10 fadeIn second"></div>
								<div>아이디</div>
								<input type="text" name=u_id id="join_id" readonly
									value="${vo.u_id }">
					</div>

					<div class="roomy=10 fadeIn second">
						<div>비밀번호</div>
						<input type=password name=u_password id=join_pwd class="input-sm">
					</div>
					<div class="roomy-10 fadeIn second">
						<div>이름</div>
						<input type=text name=u_name id=name class="input-sm"
							value="${vo.u_name }">
						<div class="roomy-10 fadeIn third">
							<div>성별</div>
							<input type=radio value="남자" name=u_gender
								${vo.u_gender=="남자"?"checked":"" }>남자 <input type=radio
								value="여자" name=u_gender ${vo.u_gender=="여자"?"checked":"" }>여자
						</div>
						<div class="roomy-10 fadeIn third">
							<div>생년월일</div>
							<input type=date size=20 name=u_birthday class="input-sm"
								id="day" value="${vo.u_birthday }">
						</div>
						<div class="roomy-10 fadeIn third">
						<div>이메일</div>
						<input type=text name=u_email id=email size=50 class="input-sm"
							value="${vo.u_email }">
                        </div>
                        <div class="roomy-10 fadeIn third">
						<div>우편번호</div>
						<input type=text name=u_post id=post size=10 class="input-sm"
							readonly value="${vo.u_post }"> <input type=button
							id="postBtn" value="우편번호찾기" class="btn btn-primary">
					
							<div>주소</div>
							<input type=text name=u_address1 id=addr1
								  readonly value="${vo.u_address1 }">

							<div>상세주소</div>
							<input type=text name=u_address2 id=addr2
							   value="${vo.u_address2 }">  
							   </div>

							<td colspan="2" class="text-center"><input type=button
								class="btn btn-sm btn-primary" value="회원수정" id="joinBtn">
								<input type=button class="btn btn-sm btn-danger" value="취소"
								onclick="javascript:history.back()"></td>
						</tr>
						</table>
						</form>
						</main>
					</div>
</body>
</html>