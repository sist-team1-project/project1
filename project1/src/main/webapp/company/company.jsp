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
    <div class="container container-pad">
      <!-- 기업 상세 정보 -->
      <div class="row roomy-20 m-top-20 room">
        <div class="col-md-2">
          <div class="logo-container"><img class="clogo" src="${company.c_logo }"></div>
        </div>
        <div class="col-md-5">
          <table>
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
      <!-------------->
    </div>
  </section>
  
  
  
  
  <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=993898e018571ba82000b5385c58f0aa&libraries=services"></script>
  <script>
  var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
      mapOption = {
          center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
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