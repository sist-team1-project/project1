<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="sist.com.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <link rel="stylesheet" href="../css/company.css">
  
</head>
<body>
<!-- 여기부터 -->
  <section>
    <div class="container">
      <!-- 기업 상세 정보 -->
      <div class="row roomy-20 m-top-50 row-border">
        <div class="col-md-2">
          <div class="logo-container"><img class="clogo" src="${company.c_logo }"></div>
        </div>
        <div class="col-md-5">
          <table class="table-top">
            <tr>
              <th class="top-table-left">기업명</th>
              <td class="small-font">${company.c_name }</td>
            </tr>
            <tr>
              <th class="top-table-left">업종</th>
              <td class="small-font">${company.c_industry }</td>
            </tr>
            <tr>
              <th class="top-table-left">기업 규모</th>
              <td class="small-font">${company.c_size }</td>
            </tr>
            <tr>
              <th class="top-table-left">본사</th>
              <td class="small-font">${company.c_address }</td>
            </tr>
          </table>
        </div>
        <div class="col-md-5">
          <div id="map" style="width:100%;height:180px;"></div>
        </div>
      </div>
      
      <div class="row m-top-40 row-padding">
        <div class="no-select"><h4>진행중인 채용 공고</h4></div>
        <div class="text-center topborder bggrey">
          <div class="bggrey pad-10 col-md-10"><b>채용공고명</b></div>
          <div class="bggrey pad-10 col-md-2"><b>모집마감일</b></div>
        </div>
      </div>
        <c:forEach var="a" items="${ad }" varStatus="status">
        <div class="row pad-10 text-center row-padding">
          <div class="col-md-10">${a.ad_title }</div>
          <div class="col-md-2"><b>${a.ad_end }</b></div>
        </div>
        </c:forEach>
      
      <div class="row m-top-40 row-padding">
        <div class="no-select"><h4>면접 후기 작성</h4></div>
        <form method=post action="">
          <span>면접은 만족 하셨나요?</span><br>
          <input type=radio value=1 name=goodbad checked> 만족
          <input type=radio value=2 name=goodbad> 불만족
          <br><span>지원하신 직무</span><br>
          <input type=text name=job size=30>
        </form>
      </div>
      
      <div class="row m-top-20 row-padding">
        <c:forEach var="r" items="${review }" varStatus="status">
          <div class="review-container">
          <c:if test="${r.review_goodbad==1}">
            <div class="review-job">
              <i class="fa fa-thumbs-up" aria-hidden="true"></i>&nbsp;&nbsp;${r.review_job }
            </div>
            <div class="review-content">${r.review_content }</div>
          </c:if>
          <c:if test="${r.review_goodbad==2}">
            <div class="review-job">
              <i class="fa fa-thumbs-down" aria-hidden="true"></i>&nbsp;&nbsp;${r.review_job }
            </div>
            <div class="review-content">${r.review_content }</div>
          </c:if>
          </div>
        </c:forEach>
      </div>
      <!-------------->
    </div>
  </section>
  
  
  
  
  <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=&libraries=services"></script>
  <script>
  var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
      mapOption = {
          center: new kakao.maps.LatLng(37.499506307492915, 127.0332437153399), // 지도의 중심좌표
          level: 4 // 지도의 확대 레벨
      };  
  
  // 지도를 생성합니다    
  var map = new kakao.maps.Map(mapContainer, mapOption); 
  
  // 주소-좌표 변환 객체를 생성합니다
  var geocoder = new kakao.maps.services.Geocoder();
  
  // 주소로 좌표를 검색합니다
  geocoder.addressSearch('${company.c_address }', function(result, status) {
  
      // 정상적으로 검색이 완료됐으면 
       if (status === kakao.maps.services.Status.OK) {
  
          var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
  
          // 결과값으로 받은 위치를 마커로 표시합니다
          var marker = new kakao.maps.Marker({
              map: map,
              position: coords
          });
  
  
          // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
          map.setCenter(coords);
      } 
  });    
  </script>
    </script>
</body>
</html>