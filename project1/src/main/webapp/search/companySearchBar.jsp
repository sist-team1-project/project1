<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="sist.com.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/companySearch__.css">
</head>
<body>
	<div class="search_container">
		<p class="search_title">기업명을 검색하여</p>
		<p class="search_title">
			<span class="font_bold">기업정보</span>와 <span class="font_bold">면접후기</span>를
			확인 해보세요!
		</p>
	</div>
	
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
</body>
</html>