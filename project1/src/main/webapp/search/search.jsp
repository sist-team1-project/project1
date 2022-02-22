<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 여기부터 -->
	<section>
		<div class="container">
			<h3 class="h3" style="margin-top: 30px;">채용정보 상세검색</h3>
			<form>
				<div class="row" style="padding: 40px; border: 1px solid #b4b4b4">
					<div class="fix" style="margin-bottom: 20px">
						<div style="font-weight: bold; float: left; width: 15%">키워드</div>
						<div style="float: left">
							<input type="text" name="키워드" class="keyword"
								placeholder="키워드를 입력하세요" size="100%"><br> <input
								type="checkbox" name="범위" value="전체" checked> 전체 <input
								type="checkbox" name="범위" value="제목"> 제목 <input
								type="checkbox" name="범위" value="회사명"> 회사명 <input
								type="checkbox" name="범위" value="직무내용"> 직무내용&nbsp;
						</div>
					</div>
					<div class="fix" style="margin-bottom: 20px">
						<div>
							<div style="float: left; width: 50%; font-weight: bold;">
								지역<input type="button" class="btn btn-default" value="지역별">
							</div>
							<div style="float: left; width: 50%; font-weight: bold;">
								희망임금 <span style="font-weight: normal"><select>
										<option>선택</option>
								</select> <input type='text' size="10">만원이상 ~ <input type='text'
									size="10">만원이하 </span>
							</div>
						</div>
					</div>
					<div>
						<div style="height: 110px;">
							<div>
								<div style="float: left; width: 15%; font-weight: bold;">
									경력</div>
								<div style="float: left; width: 35%;">
									<input type='checkbox' name='경력' value="전체" checked> 전체<br>
									<input type='checkbox' name='경력' value="신입"> 신입<br>
									<input type='checkbox' name='경력' value="경력"> 경력 ( <input
										type='text' size="10">년 ~ <input type='text' size="10">년)
									<br> <input type='checkbox' name='경력' value="관계없음">
									관계없음
								</div>
							</div>
							<div>
								<div style="float: left; width: 15%; font-weight: bold;">
									학력</div>
								<div style="float: left; width: 35%;">
									<input type='checkbox' name='학력' value="전체" checked> 전체
									<input type='checkbox' name='학력' value="중졸이하"> 중졸이하 <input
										type='checkbox' name='학력' value="고졸"> 고졸<br> <input
										type='checkbox' name='학력' value="대졸(2~3년)"> 대졸(2~3년) <input
										type='checkbox' name='학력' value="대졸(4년)"> 대졸(4년) <input
										type='checkbox' name='학력' value="석사"> 석사<br> <input
										type='checkbox' name='학력' value="박사"> 박사 <input
										type='checkbox' name='학력' value="학력무관"> 학력무관<br>
								</div>
							</div>
						</div>
					</div>
					<div class="m-bottom-20">
						<div style="float: left; width: 15%; font-weight: bold;">
							기업형태</div>
						<div>
							<input type='checkbox' name='기업형태' value="전체" checked> 전체
							<input type='checkbox' name='기업형태' value="대기업"> 대기업 <input
								type='checkbox' name='기업형태' value="중견기업"> 중견기업 <input
								type='checkbox' name='기업형태' value="중소기업"> 중소기업
						</div>
					</div>
					<div class="m-bottom-20">
						<div style="float: left; width: 15%; font-weight: bold;">
							근무형태</div>
						<div>
							<input type='checkbox' name='근무형태' value="주 5일"> 주 5일 <input
								type='checkbox' name='근무형태' value="주 6일"> 주 6일 <input
								type='checkbox' name='근무형태' value="주 4일"> 주 4일 <input
								type='checkbox' name='근무형태' value="주 3일"> 주 3일 <input
								type='checkbox' name='근무형태' value="주 2일"> 주 2일 <input
								type='checkbox' name='근무형태' value="주 1일"> 주 1일 <input
								type='checkbox' name='근무형태' value="주 7일"> 주 7일
						</div>
					</div>
					<div class='m-bottom-20'>
						<div style="font-weight: bold;">
							자격면허 <input type="button" class="btn btn-default" value="자격증 선택">
						</div>
					</div>
					<div class='m-bottom-20'>
						<div style="font-weight: bold;">
							외국어 <input type="button" class="btn btn-default" value="외국어 선택">
						</div>
					</div>
					<div class='m-bottom-20' style="font-weight: bold;">
						<div>
							마감일<span style="font-weight: normal"> <input type="date"
								id="start" name="deadline"> ~ <!-- value 속성으로 날짜의 최댓값과 최솟값 설정 가능-->
								<input type="date" id="end" name="deadline">
							</span>
						</div>
					</div>
					<div>
						<button class='bg-finence text-white'
							style="margin: auto; padding: 5px 20px; border-raidus: 10px; display: block;">검색</button>
					</div>
				</div>
			</form>
		</div>
	</section>
	<!-- 여기까지 직접 작성 -->
</body>
</html>