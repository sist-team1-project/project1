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
				<input type="hidden" id="ad_id" value="${ad.ad_id }">
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
						<div class="row m-top-60 no-select">
								<h3>
										<i class="fa fa-book" aria-hidden="true"></i>&nbsp; <b>모집요강</b>
								</h3>
						</div>
						<div class="row row-border">
								<div class="col-md-12 bggrey no-select title-deco roomy-10 text-center">직무내용</div>
								<div class="col-md-12 content">${ad.ad_content }</div>


								<div class="col-md-4">
										<div class="row bggrey title-deco no-select roomy-10 text-center">경력조건</div>
										<div class="row text-center content">${ad.ad_we }</div>
								</div>
								<div class="col-md-4">
										<div class="row bggrey title-deco no-select roomy-10 text-center">학력</div>
										<div class="row text-center content">${ad.ad_education }</div>
								</div>
								<div class="col-md-4">
										<div class="row bggrey title-deco no-select roomy-10 text-center">근무예정지</div>
										<div class="row text-center content">
												<a href="#wptarget">${ad.ad_workplace }</a>
										</div>
								</div>

						</div>



						<!-- ############################################################################################################################ -->

						<!--              근무 조건                -->
						<div class="row m-top-60 no-select">
								<h3>
										<i class="fa fa-clock-o" aria-hidden="true"></i>&nbsp; <b>근무조건</b>
								</h3>
						</div>
						<div class="row row-border">
								<div class="col-md-4">
										<div class="row bggrey title-deco no-select roomy-10 text-center">임금조건</div>
										<div class="row text-center content">${ad.ad_wage }</div>
								</div>
								<div class="col-md-4">
										<div class="row bggrey title-deco no-select roomy-10 text-center">근무시간</div>
										<div class="row text-center content">${ad.ad_workhours }</div>
								</div>
								<div class="col-md-4">
										<div class="row bggrey title-deco no-select roomy-10 text-center">근무형태</div>
										<div class="row text-center content">${ad.ad_worktype }</div>
								</div>

						</div>


						<!-- ############################################################################################################################ -->

						<!--              우대사항 및 전형방법              -->
						<div class="row m-top-60 no-select">
								<h3>
										<i class="fa fa-trophy" aria-hidden="true"></i>&nbsp; <b>우대사항 및 전형방법</b>
								</h3>
						</div>
						<div class="row row-border">
								<div class="col-md-3">
										<div class="row bggrey title-deco no-select roomy-10 text-center">우대전공</div>
										<div class="row text-center content">${ad.ad_major }</div>
								</div>
								<div class="col-md-3">
										<div class="row bggrey title-deco no-select roomy-10 text-center">우대 자격증</div>
										<div class="rrow text-center content">
												<a href="#btarget">${ad.ad_qualification }</a>
										</div>
								</div>
								<div class="col-md-3">
										<div class="row bggrey title-deco no-select roomy-10 text-center">우대 언어</div>
										<div class="row text-center content">${ad.ad_language }</div>
								</div>
								<div class="col-md-3">
										<div class="row bggrey title-deco no-select roomy-10 text-center">접수 마감일</div>
										<div class="row text-center content">${ad.ad_end }</div>
								</div>
						</div>



						<!-- ############################################################################################################################ -->

						<!--              위치정보                 -->

						<div class="row m-top-60 no-select">
								<a name="wptarget"></a>
								<h3>
										<i class="fa fa-map-marker" aria-hidden="true"></i>&nbsp; <b>위치정보</b>
								</h3>
						</div>

						<div class="row row-border">
								<div id="map" style="width: 100%; height: 400px;"></div>
						</div>


						<!-- ########################################################################################## -->

						<!--              추천 도서                -->

						<div class="row m-top-60 no-select">
								<a name="btarget"></a>
								<h3>
										<i class="fa fa-graduation-cap" aria-hidden="true"></i>&nbsp; <b>추천 도서</b>
								</h3>
						</div>
						<div class="row row-border">
								<div class="col-sm-12 bggrey title-deco no-select roomy-10 text-center">추천 수험서</div>
								<div class="col-sm-12">
										<c:forEach var="b" items="${booksList }" varStatus="status">
												<div class="row content">

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
																		<div class="col-sm-12 qual-name">${qualList[status.index] }</div>
																</c:when>
														</c:choose>

														<c:forEach var="books" items="${b }">
																<div class="col-sm-2 m-bottom-20">
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

						<!-- ########################################################################################## -->

						<!--              홈/즐겨찾기 버튼                -->

						<!-- <c:if test="${count==0 }">
	      		 		<a href="../jjim/jjim_insert.do?fno=${vo.no }" class="btn btn-xs btn-danger">찜하기</a>
	      				</c:if>
	      				<c:if test="${count!=0 }">
	     	 				<span class="btn btn-xs btn-default">찜하기</span>
	     	 				</c:if> -->


						<div class="text-center row roomy-40">
								<a href="../main/main.do" class="btn btn-primary">홈으로</a>&nbsp;
							 	<button type="button" id="favorite-insert-btn" class="btn btn-primary">즐겨찾기 추가</button>
								&nbsp;&nbsp;
								<c:if test="${sessionScope.id!=null }">
										<c:if test="${count==0 }">
												<button type="button" id="favorite-insert-btn" class="btn btn-primary">즐겨찾기 추가</button>
										</c:if>
										<c:if test="${count!=0 }">
												<span class="btn btn-default"> 즐겨찾기 추가</span>
										</c:if>
								</c:if>
								&nbsp;&nbsp;
								<div class="row no-select padding-10">조회수 ${ad.ad_visits }</div>
						</div>


						<div style="position: fixed; bottom: 50px; right: 60px;">
								<a href="#" class="scrollToTop"><i class="fa fa-chevron-up" aria-hidden="true"></i></a>
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

		<!-- 맨 위로 이동 버튼 -->
		<script>
			$(document).ready(function() {
				//Check to see if the window is top if not then display button
				$(window).scroll(function() {
					if ($(this).scrollTop() > 100) {
						$('.scrollToTop').fadeIn();
					} else {
						$('.scrollToTop').fadeOut();
					}
				});
				//Click event to scroll to top
				$('.scrollToTop').click(function() {
					$('html, body').animate({
						scrollTop : 0
					}, 800);
					return false;
				});
			});
		</script>


		<!-- ######################################################################################################################## -->

		<!--    cookie     -->
		<script>
			// 화면이 뜨는 것과 동시에 실행
			$(document).ready(function() {
				var adId = $('#ad_id').val();
				addCookie(adId);
			});

			function setCookie(cookie_name, value, days) {
				var exdate = new Date();
				exdate.setDate(exdate.getDate() + days);
				// 설정 일수만큼 현재시간에 만료값으로 지정

				var cookie_value = escape(value)
						+ ((days == null) ? '' : '; expires='
								+ exdate.toUTCString());
				document.cookie = cookie_name + '=' + cookie_value + "; path=/";
			}

			function getCookie(cookie_name) {
				var x, y;

				var val = document.cookie.split(';');

				for (var i = 0; i < val.length; i++) {
					x = val[i].substr(0, val[i].indexOf('='));
					y = val[i].substr(val[i].indexOf('=') + 1);
					x = x.replace(/^\s+|\s+$/g, ''); // 앞과 뒤의 공백 제거하기
					if (x == cookie_name) {
						return unescape(y); // unescape로 디코딩 후 값 리턴
					}
				}
			}

			function addCookie(id) {
				var items = getCookie('adview'); // 이미 저장된 값을 쿠키에서 가져오기
				var maxItemNum = 4; // 최대 저장 가능한 공고개수
				var expire = 7; // 쿠키값을 저장할 기간
				if (items) {
					var itemArray = items.split(',');
					if (itemArray.indexOf(id) != -1) {
						// 이미 존재하는 경우 종료
						console.log('Already exists.');
					} else {
						// 새로운 값 저장 및 최대 개수 유지하기
						itemArray.unshift(id);
						if (itemArray.length > maxItemNum)
							itemArray.length = 4;
						items = itemArray.join(',');
						setCookie('adview', items, expire);
					}
				} else {
					// 신규 id값 저장하기
					setCookie('adview', id, expire);
				}
			}
		</script>
		<!-- ######################################################################################################################## -->

		
		<script type="text/javascript">
			$(function() {
				var adId = $('#ad_id').val();
				$('#favorite-insert-btn').click(function() {
					$.ajax({
						type : 'POST',
						url : 'favorite/insert.do',
						data : adId,
						success : function() {
							alert("즐겨찾기 추가 완료");
						}
					});
				})
			})
		</script>
		 

		<!-- ######################################################################################################################## -->
		<!-- 여기까지 직접 작성 -->

</body>
</html>