<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container {
	margin: 0px auto; /*가운데 정렬*/
}

.tr-3 {
	font-size: 8pt;
}

select {
	width: 80px;
	height: 35px;
	border: 1px solid #999;
	border-radius: 0px;
}

th {
	font-weight: normal;
}
</style>
</head>
<body>
	<!-- 여기부터 -->
	<section>
		<div class="container fix">
			<table class="table"
				style="border: 1px solid #b4b4b4; margin-top: 20px">
				<tr>
					<td colspan="7" style="text-align: right"><select>
							<option>10개</option>
							<option>20개</option>
							<option>50개</option>
					</select>
						<button class="btn-default p-l-15 p-r-15" style="height: 35px;">보기</button>
					</td>
				<tr>
					<th width=300px class="text-center bg-grey">회사명</th>
					<th width=600px colspan="3" class="text-center bg-grey">채용공고</th>
					<th width=300px colspan="2" class="text-center bg-grey">근무조건</th>
					<th width=300px class="text-center bg-grey">마감일</th>
					<th width=10px class="text-center bg-grey"></th>
				</tr>
				<tr height=90px>
					<td width=300px rowspan="2"
						style="font-weight: bold; vertical-align: middle; text-indent: 15px">서귀포호텔</td>
					<td width=600px colspan="3" style="font-weight: bold; vertical-align: middle">국군복지단
						서귀포호텔 공개 채용(프론트)</td>
					<td width=300px colspan="2" style="font-weight: bold">연봉 2,400
						만원 ~ 2,500만원</td>
					<td width=300px class="text-center" style="font-weight: bold">채용시까지</td>
					<td width=100px rowspan="2"><a href="#" id="favorite" title="즐겨찾기 등록">즐겨찾기</a></td>
				</tr>
				<tr class="tr-3">
					<td width=150px>경력무관</td>
					<td width=150px>학력무관</td>
					<td width=300px>제주특별자치도 서귀포시 상예로</td>
					<td width=300px>주 5일</td>
					<td width=300px>근무시간</td>
				</tr>
				<tr height=90px>
					<td width=300px rowspan="2"
						style="font-weight: bold; vertical-align: middle; text-indent: 15px">서귀포호텔</td>
					<td width=600px colspan="3" style="font-weight: bold; vertical-align: middle">국군복지단
						서귀포호텔 공개 채용(프론트)</td>
					<td width=300px colspan="2" style="font-weight: bold">연봉 2,400
						만원 ~ 2,500만원</td>
					<td width=300px class="text-center" style="font-weight: bold">채용시까지</td>
					<td width=100px rowspan="2"><a href="#" id="favorite" title="즐겨찾기 등록">즐겨찾기</a></td>
				</tr>
				<tr class="tr-3">
					<td width=150px>경력무관</td>
					<td width=150px>학력무관</td>
					<td width=300px>제주특별자치도 서귀포시 상예로</td>
					<td width=300px>주 5일</td>
					<td width=300px>근무시간</td>
				</tr>
				<tr height=90px>
					<td width=300px rowspan="2"
						style="font-weight: bold; vertical-align: middle; text-indent: 15px">서귀포호텔</td>
					<td width=600px colspan="3" style="font-weight: bold; vertical-align: middle">국군복지단
						서귀포호텔 공개 채용(프론트)</td>
					<td width=300px colspan="2" style="font-weight: bold">연봉 2,400
						만원 ~ 2,500만원</td>
					<td width=300px class="text-center" style="font-weight: bold">채용시까지</td>
					<td width=100px rowspan="2"><a href="#" id="favorite" title="즐겨찾기 등록">즐겨찾기</a></td>
				</tr>
				<tr class="tr-3">
					<td width=150px>경력무관</td>
					<td width=150px>학력무관</td>
					<td width=300px>제주특별자치도 서귀포시 상예로</td>
					<td width=300px>주 5일</td>
					<td width=300px>근무시간</td>
				</tr>
			</table>
		</div>
	</section>
	<!-- 여기까지 직접 작성 -->
</body>
</html>