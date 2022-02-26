<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="sist.com.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/addetail.css">
<link rel="stylesheet" href="../css/ad_book.css">
</head>
<body>
		<section>
				<!-- 공고 메인 정보 -->
				<div class="container container-pad">
						<div class="row row-border room m-top-40">
								<div class="col-md-12 roomy-10">
										<h4>
												<b>${ad.ad_title }</b>
										</h4>
								</div>
								<div class="col-md-4 roomy-10 no-select">
										<div class="top-table-title title-deco">
												<b>지원자격</b>
										</div>
										<table class="top-table">
												<tr>
														<th class="width-80">경력</th>
														<td>${ad.ad_we }</td>
												</tr>
												<tr>
														<th>학력</th>
														<td>${ad.ad_education }</td>
												</tr>
										</table>
								</div>
								<div class="col-md-4 roomy-10 no-select">
										<div class="top-table-title title-deco">
												<b>근무조건</b>
										</div>
										<table class="top-table">
												<tr>
														<th class="width-80">근무예정지</th>
														<td class="word-break">${ad.ad_workplace }</td>
												</tr>
												<tr>
														<th class="width-80">임금</th>
														<td>${ad.ad_wage }</td>
												</tr>
										</table>
								</div>
								<div class="col-md-4 roomy-10 no-select">
										<div class="logo-container ">
												<img class="clogo" src="${company.c_logo }">
										</div>
										<table class="top-table">
												<tr>
														<th class="top-table-left">기업명</th>
														<td>
																<a href="../company/company.do?cid=${company.c_id }">${company.c_name } </a>
														</td>
												</tr>
												<tr>
														<th class="width-80">업종</th>
														<td>${company.c_industry }</td>
												</tr>
												<tr>
														<th class="width-80">기업규모</th>
														<td>${company.c_size }</td>
												</tr>
										</table>
								</div>
						</div>
						<!-- ############################################################################################################################ -->
						<!--              모집 요강                -->
						<div class="row m-top-60">
								<div class="no-select">
										<h3>
												<i class="fa fa-book" aria-hidden="true"></i>&nbsp; <b>모집요강</b>
										</h3>
								</div>
								<div class="row row-border">
										<div class="row bggrey no-select title-deco padding-10">직무내용</div>
										<div class="row content">${ad.ad_content }</div>

										<div class="row">
												<div class="col-md-4 col-padding-0">
														<div class="row bggrey title-deco padding-10 no-select">경력조건</div>
														<div class="row bgwhite padding-15">${ad.ad_we }</div>
												</div>
												<div class="col-md-4 col-padding-0">
														<div class="row bggrey title-deco padding-10 no-select">학력</div>
														<div class="row bgwhite padding-15">${ad.ad_education }</div>
												</div>
												<div class="col-md-4 col-padding-0">
														<div class="row bggrey title-deco padding-10 no-select">근무예정지</div>
														<div class="row bgwhite padding-15">
																<a href="#wptarget">${ad.ad_workplace }</a>
														</div>
												</div>
										</div>
								</div>
						</div>


						<!-- ############################################################################################################################ -->

						<!--              근무 조건                -->
						<div class="row m-top-60">
								<div class="no-select">
										<h3>
												<i class="fa fa-clock-o" aria-hidden="true"></i>&nbsp; <b>근무조건</b>
										</h3>
								</div>
								<div class="row row-border">
										<div class="row">
												<div class="col-md-4 col-padding-0">
														<div class="row bggrey title-deco padding-10 no-select">임금조건</div>
														<div class="row bgwhite padding-15">${ad.ad_wage }</div>
												</div>
												<div class="col-md-4 col-padding-0">
														<div class="row bggrey title-deco padding-10 no-select">근무시간</div>
														<div class="row bgwhite padding-15">${ad.ad_workhours }</div>
												</div>
												<div class="col-md-4 col-padding-0">
														<div class="row bggrey title-deco padding-10 no-select">근무형태</div>
														<div class="row bgwhite padding-15">${ad.ad_worktype }</div>
												</div>
										</div>
								</div>
						</div>


						<!-- ############################################################################################################################ -->

						<!--              우대사항 및 전형방법              -->
						<div class="row m-top-60">
								<div class="no-select">
										<h3>
												<i class="fa fa-trophy" aria-hidden="true"></i>&nbsp; <b>우대사항 및 전형방법</b>
										</h3>
								</div>
								<div class="row row-border">
										<div class="row">
												<div class="col-md-3 col-padding-0">
														<div class="row bggrey title-deco padding-10 no-select">우대전공</div>
														<div class="row bgwhite padding-15">${ad.ad_major }</div>
												</div>
												<div class="col-md-3 col-padding-0">
														<div class="row bggrey title-deco padding-10 no-select">우대 자격증</div>
														<div class="row bgwhite padding-15">
																<a href="#btarget">${ad.ad_qualification }</a>
														</div>
												</div>
												<div class="col-md-3 col-padding-0">
														<div class="row bggrey title-deco padding-10 no-select">우대 언어</div>
														<div class="row bgwhite padding-15">${ad.ad_language }</div>
												</div>
												<div class="col-md-3 col-padding-0">
														<div class="row bggrey title-deco padding-10 no-select">접수 마감일</div>
														<div class="row bgwhite padding-15">${ad.ad_end }</div>
												</div>
										</div>
								</div>
						</div>

						<!-- ############################################################################################################################ -->

						<!--              위치정보                 -->

						<div class="row m-top-60">
								<a name="wptarget"></a>
								<div class="top-ad-title no-select">
										<h3>
												<i class="fa fa-map-marker" aria-hidden="true"></i>&nbsp; <b>위치정보</b>
										</h3>
								</div>
								<div class="row row-border">
										<div id="map" style="width: 100%; height: 400px;"></div>
								</div>
						</div>

						<!-- ########################################################################################## -->

						<!--              추천 도서                -->

						<div class="row m-top-60">
								<div class="no-select">
										<h3>
												<i class="fa fa-graduation-cap" aria-hidden="true"></i>&nbsp; <b>추천 도서</b> <a name="btarget"></a>
										</h3>
								</div>
								<div class="row row-border">
										<div class="row bggrey title-deco padding-10 no-select">추천 수험서</div>
										<div class="row content">
												<c:forEach var="b" items="${booksList }" varStatus="status">
														<div class="row">
																<!-- 추천 수험서가 있을 경우에만 출력 -->

																<!-- ######################################################################################################################## -->


																<%--
																<div class="spacer"></div>
																<div class="arrow" onclick="mySlideLeft()">
																		<img src="http://cerati.org/eoitecne/img/down.png" class="arrowleft arimage" />
																</div>
																<div class="slider-cont" id="slider-cont">
																		<!--<div class="slider-layer"  id="slider-cont"></div>-->
																		<div class="test-slider">
																				<div class="cont-slider" onclick="selectSliderElement(this)" id="ext-1">
																						<img class="flat-w-image image-slider" src="http://cerati.org/eoitecne/imghttp://cerati.org/eoitecne//prodotti/05.png" /><img src="http://cerati.org/eoitecne/img/prodotti/05.png" class="image-w" />
																				</div>
																				<div class="cont-slider" onclick="selectSliderElement(this)" id="ext-2">
																						<img class="flat-w-image image-slider" src="http://cerati.org/eoitecne/img/prodotti/02.png" /><img src="http://cerati.org/eoitecne/img/prodotti/02.png" class="image-w" />
																				</div>
																				<div class="cont-slider" onclick="selectSliderElement(this)" id="ext-3">
																						<img class="flat-w-image image-slider" src="http://cerati.org/eoitecne/img/prodotti/05.png" /><img src="http://cerati.org/eoitecne/img/prodotti/05.png" class="image-w" />
																				</div>
																				<div class="cont-slider" onclick="selectSliderElement(this)" id="ext-4">
																						<img class="flat-w-image image-slider" src="http://cerati.org/eoitecne/img/prodotti/02.png" /><img src="http://cerati.org/eoitecne/img/prodotti/02.png" class="image-w" />
																				</div>
																				<div class="cont-slider" onclick="selectSliderElement(this)" id="ext-5">
																						<img class="flat-w-image image-slider" src="http://cerati.org/eoitecne/img/prodotti/05.png" /><img src="http://cerati.org/eoitecne/img/prodotti/05.png" class="image-w" />
																				</div>
																				<div class="cont-slider" onclick="selectSliderElement(this)" id="ext-6">
																						<img class="flat-w-image image-slider" src="http://cerati.org/eoitecne/img/prodotti/02.png" /><img src="http://cerati.org/eoitecne/img/prodotti/02.png" class="image-w" />
																				</div>
																				<div class="cont-slider" onclick="selectSliderElement(this)" id="ext-7">
																						<img class="flat-w-image image-slider" src="http://cerati.org/eoitecne/img/prodotti/05.png" /><img src="http://cerati.org/eoitecne/img/prodotti/05.png" class="image-w" />
																				</div>
																				<div class="cont-slider" onclick="selectSliderElement(this)" id="ext-8">
																						<img class="flat-w-image image-slider" src="http://cerati.org/eoitecne/img/prodotti/02.png" /><img src="http://cerati.org/eoitecne/img/prodotti/02.png" class="image-w" />
																				</div>
																		</div>
																</div>
																<div class="arrow" onclick="mySlideRight()">
																		<img src="http://cerati.org/eoitecne/img/down.png" class="arrowright arimage" />
																</div>


--%>


																<!-- ######################################################################################################################## -->

																<c:choose>
																		<c:when test="${fn:length(b)!=0 }">
																				<div class="qual-name">${qualList[status.index] }</div>
																		</c:when>
																</c:choose>

																<c:forEach var="books" items="${b }">
																		<div class="col-sm-2">
																				<div class="text-center">
																						<img class="book-img" src="${books.book_img }">
																				</div>
																				<br>
																				<div class="text-center short-container">
																						<div class="short-multi-line">
																								<a href="${books.book_link }"><b>${books.book_title }</b></a>
																						</div>
																				</div>
																				<div class="text-center">판매지수 ${books.book_sold }</div>
																		</div>
																</c:forEach>
														</div>
												</c:forEach>
										</div>
								</div>
						</div>

						<!-- ########################################################################################## -->

						<!--              홈/즐겨찾기 버튼                -->

						<div class="text-center row roomy-40">
								<a href="../main/main.do" class="btn btn-primary">홈으로</a>&nbsp;<a href="#" class="btn btn-default"> 즐겨찾기 추가</a>&nbsp;&nbsp;
								<div class="row no-select padding-10">조회수 ${ad.ad_visits }</div>
						</div>

				</div>
		</section>

		<!-- ########################################################################################## -->

		<!--              지도 API           -->

		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5169c9ddd251dfb03456915e6dd020c6&libraries=services"></script>
		<script>
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			mapOption = {
				center : new kakao.maps.LatLng(37.499506307492915,
						127.0332437153399), // 지도의 중심좌표
				level : 4
			// 지도의 확대 레벨
			};
			// 지도를 생성합니다    
			var map = new kakao.maps.Map(mapContainer, mapOption);
			// 주소-좌표 변환 객체를 생성합니다
			var geocoder = new kakao.maps.services.Geocoder();
			// 주소로 좌표를 검색합니다
			geocoder.addressSearch('${ad.ad_workplace }',
					function(result, status) {
						// 정상적으로 검색이 완료됐으면 
						if (status === kakao.maps.services.Status.OK) {
							var coords = new kakao.maps.LatLng(result[0].y,
									result[0].x);
							// 결과값으로 받은 위치를 마커로 표시합니다
							var marker = new kakao.maps.Marker({
								map : map,
								position : coords
							});
							// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
							map.setCenter(coords);
						}
					});
		</script>

		<!-- ######################################################################################################################## -->


		<script type="text/javascript">
			
		</script>


		<!-- ######################################################################################################################## -->

		<!-- 여기까지 직접 작성 -->

</body>
</html>