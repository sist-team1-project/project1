<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="sist.com.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/ad.css">
</head>
<body>
		<section>
				<div class="container container-pad">
						<div class="row row-border room m-top-40">
								<div class="col-md-8 roomy-15">
										<div class="top-ad-title">
												<h3>
														<b>${ad.ad_title }</b>
												</h3>
										</div>
										<div class="col-lg-6 padding-0">
												<table class="top-table">
														<tr>
																<th class="top-table-title title-deco no-select" colspan="2">지원자격</th>
														</tr>
														<tr>
																<th class="top-table-left no-select">경력</th>
																<td>${ad.ad_we }</td>
														</tr>
														<tr>
																<th class="top-table-left no-select">학력</th>
																<td>${ad.ad_education }</td>
														</tr>
												</table>
										</div>
										<div class="col-lg-6 padding-0">
												<table class="top-table">
														<tr>
																<th class="top-table-title title-deco no-select" colspan="2">근무조건</th>
														</tr>
														<tr>
																<th class="top-table-left no-select">근무예정지</th>
																<td>${ad.ad_workplace }</td>
														</tr>
														<tr>
																<th class="top-table-left no-select">임금</th>
																<td>${ad.ad_wage }</td>
														</tr>
												</table>
										</div>
								</div>
								<div class="col-md-4 roomy-15">
										<div class="logo-container margin-bottom">
												<img class="clogo" src="${company.c_logo }">
										</div>
										<table class="top-table">

												<tr>
														<th class="top-table-left no-select">기업명</th>
														<td>
																<a href="../company/company.do?cid=${company.c_id }">${company.c_name } </a>
														</td>
												</tr>
												<tr>
														<th class="top-table-left no-select">업종</th>
														<td>${company.c_industry }</td>
												</tr>
												<tr>
														<th class="top-table-left no-select">기업규모</th>
														<td>${company.c_size }</td>
												</tr>
										</table>
								</div>
						</div>

						<div class="row m-top-60">
								<div class="top-ad-title">
										<h3>
												<i class="fa fa-book" aria-hidden="true"></i>&nbsp; <b>모집요강</b>
										</h3>
								</div>
								<div class="row row-border">
										<div>
												<table class="top-table">
														<tr>
																<th class="no-select top-ad-title Tcenter mar15-pad20 title-deco">직무내용</th>
														</tr>
														<tr>
																<td>
																		<ul>
																				<li>${ad.ad_content }</li>
																		</ul>
																</td>
														</tr>
												</table>
										</div>

										<div class="row col-md-4">
												<table class="top-table">
														<tr>
																<th class="no-select Tcenter mar15-pad20 title-deco">경력조건</th>
														</tr>
														<tr>
																<td class="Tcenter padding-bottom-30">${ad.ad_we }</td>
														</tr>
												</table>
										</div>
										<div class="row col-md-4">
												<table class="top-table">
														<tr>
																<th class="no-select Tcenter mar15-pad20 title-deco">학력</th>
														</tr>
														<tr>
																<td class="Tcenter padding-bottom-30">${ad.ad_education }</td>
														</tr>
												</table>
										</div>
										<div class="row col-md-4">
												<table class="top-table">
														<tr>
																<th class="no-select Tcenter mar15-pad20 title-deco">근무예정지</th>
														</tr>
														<tr>
																<td class="Tcenter padding-bottom-30">${ad.ad_workplace }</td>
														</tr>
												</table>
										</div>
								</div>
						</div>

						<!-- ############################################################################################################################ -->

						<div class="row m-top-60">
								<div class="top-ad-title">
										<h3>
												<i class="fa fa-clock-o" aria-hidden="true"></i>&nbsp; <b>근무조건</b>
										</h3>
								</div>
								<div class="row row-border">
										<div class="row col-md-4">
												<table class="top-table">
														<tr>
																<th class="no-select Tcenter mar15-pad20 title-deco">임금조건</th>
														</tr>
														<tr>
																<td class="Tcenter padding-bottom-30">${ad.ad_wage }</td>
														</tr>
												</table>
										</div>
										<div class="row col-md-4">
												<table class="top-table">
														<tr>
																<th class="no-select Tcenter mar15-pad20 title-deco">근무시간</th>
														</tr>
														<tr>
																<td class="Tcenter padding-bottom-30">${ad.ad_workhours }</td>
														</tr>
												</table>
										</div>
										<div class="row col-md-4">
												<table class="top-table">
														<tr>
																<th class="no-select Tcenter mar15-pad20 title-deco">근무형태</th>
														</tr>
														<tr>
																<td class="Tcenter padding-bottom-30">${ad.ad_worktype }</td>
														</tr>
												</table>
										</div>
								</div>
						</div>

						<!-- ############################################################################################################################ -->


						<div class="row m-top-60">
								<div class="top-ad-title">
										<h3>
												<i class="fa fa-trophy" aria-hidden="true"></i>&nbsp; <b>우대사항 및 전형방법</b>
										</h3>
								</div>
								<div class="row row-border">
										<div class="row col-md-3">
												<table class="top-table">
														<tr>
																<th class="no-select Tcenter mar15-pad20 title-deco">우대전공</th>
														</tr>
														<tr>
																<td class="Tcenter padding-bottom-30">${ad.ad_major }</td>
														</tr>
												</table>
										</div>
										<div class="row col-md-3">
												<table class="top-table">
														<tr>
																<th class="no-select Tcenter mar15-pad20 title-deco">우대자격증</th>
														</tr>
														<tr>
																<td class="Tcenter padding-bottom-30">${ad.ad_qualification }</td>
														</tr>
												</table>
										</div>
										<div class="row col-md-3">
												<table class="top-table">
														<tr>
																<th class="no-select Tcenter mar15-pad20 title-deco">우대언어</th>
														</tr>
														<tr>
																<td class="Tcenter padding-bottom-30">${ad.ad_language }</td>
														</tr>
												</table>
										</div>
										<div class="row col-md-3">
												<table class="top-table">
														<tr>
																<th class="no-select Tcenter mar15-pad20 title-deco">접수마감일</th>
														</tr>
														<tr>
																<td class="Tcenter padding-bottom-30">${ad.ad_end }</td>
														</tr>
												</table>
										</div>
								</div>
						</div>

						<!-- ############################################################################################################################ -->

						<div class="row m-top-60">
								<div class="top-ad-title">
										<h3>
												<i class="fa fa-map-marker" aria-hidden="true"></i>&nbsp; <b>위치정보</b>
										</h3>
								</div>
								<div class="row row-border">

										<!-- ############################################################################################################# -->

										<div id="Map" style="width: 900px; height: 400px;"></div>
										<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=66a2db847dd74c2c67074ec7410fa651&libraries=services"></script>
										<script>
											var mapContainer = document
													.getElementById('map'), // 지도를 표시할 div 
											mapOption = {
												center : new kakao.maps.LatLng(
														33.450701, 126.570667), // 지도의 중심좌표
												level : 4
											// 지도의 확대 레벨
											};

											// 지도를 생성합니다    
											var map = new kakao.maps.Map(
													mapContainer, mapOption);

											// 주소-좌표 변환 객체를 생성합니다
											var geocoder = new kakao.maps.services.Geocoder();

											// 주소로 좌표를 검색합니다
											geocoder
													.addressSearch(
															'${company.c_address }',
															function(result,
																	status) {

																// 정상적으로 검색이 완료됐으면 
																if (status === kakao.maps.services.Status.OK) {

																	var coords = new kakao.maps.LatLng(
																			result[0].y,
																			result[0].x);

																	// 결과값으로 받은 위치를 마커로 표시합니다
																	var marker = new kakao.maps.Marker(
																			{
																				map : map,
																				position : coords
																			});

																	// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
																	map
																			.setCenter(coords);
																}
															});
										</script>
										</script>

								</div>
						</div>

						<!-- ########################################################################################## -->

						<div class="row m-top-60">
								<div class="top-ad-title">
										<h3>
												<i class="fa fa-graduation-cap" aria-hidden="true"></i>&nbsp; <b>추천 도서</b>
										</h3>
								</div>
								<div class="row row-border">

										<table class="top-table">
												<tr>
														<th class="no-select top-ad-title Tcenter mar15-pad20 title-deco">추천수험서</th>
												</tr>
												<tr>
														<td class="Tcenter padding-bottom-30">도서도서도서</td>
												</tr>
										</table>
								</div>
						</div>

						<div class="Tcenter row roomy-40">
								<a href="#" class="btn btn-xs btn-danger">홈으로</a>&nbsp;&nbsp; 
								<a href="#" class="btn btn-xs btn-danger"> 즐겨찾기 추가</a>&nbsp;&nbsp;
								<div class="no-select row Tright">조회수&nbsp;&nbsp;</div>
						</div>

				</div>
		</section>


		<!-- 여기까지 직접 작성 -->
</body>
</html>