<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.sb-container {
	margin: 100px auto 0 30px;
}

.sb-example-1 .search {
	width: 600px;
	margin: 0 auto;
	display: flex;
}

.sb-example-1 .searchTerm {
	width: 330px;
	height: 14px;
	border-style: 3px solid grey;
	border-right: none;
	padding: 18px 0 18px 18px;
	border-radius: 10px 0 0 10px;
	outline: none;
	color: #9DBFAF;
}

.sb-example-1 .searchTerm:focus {
	color: #017576;
}

.sb-example-1 .searchButton {
	width: 80px;
	height: 40px;
	border: 1px solid black;
	background: black;
	text-align: center;
	color: #fff;
	border-radius: 0 10px 10px 0;
	font-size: 18px;
}

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
	<!-- 
  	<div class="sb-container">
		<div class="sb-example-1">
			<div class="search">
				<input type="text" class="searchTerm" placeholder="회사 이름으로 검색하세요">
				<button type="submit" class="searchButton">
					검색 <i class="fa fa-search"></i>
				</button>
			</div>
		</div>
	</div>
-->

	<!-- 여기부터 -->
	<section>
		<c:forEach var="vo" items="${list }">
			<div class="container">
				<table class="table" style="margin: 20px auto; width: 650px;">
					<tr class="tr">
						<td class="td-1" rowspan="2">${vo.c_logo }</td>
						<td class="text-left td-2" colspan="2">${vo.c_logo }</td>
						<td class="text-center td-3" rowspan="2">000 면접정보(기업리뷰)</td>
					</tr>
					<tr>
						<td class="td-4">${vo.c_industry }</td>
						<td class="td-5">${vo.c_address }</td>
					</tr>
					
				</table>
			</div>
		</c:forEach>
	</section>