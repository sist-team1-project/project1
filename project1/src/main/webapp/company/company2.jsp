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
      <div class="row roomy-20 m-top-50 row-border">
        <div class="col-md-2">
          <div class="logo-container"><img class="clogo" src="${company.c_logo }"></div>
        </div>
        <div class="col-md-5">
          <table class="table-top">
            <tr>
              <td colspan="2" class="top-table-cname"><b>${company.c_name }</b></td>
            </tr>
            <tr>
              <th class="top-table-left">업종</th>
              <td>${company.c_industry }</td>
            </tr>
            <tr>
              <th class="top-table-left">기업 규모</th>
              <td>${company.c_size }</td>
            </tr>
            <tr>
              <th class="top-table-left">본사</th>
              <td>${company.c_address }</td>
            </tr>
          </table>
        </div>
        <div class="col-md-5">
          <div id="map" style="width:100%;height:190px;"></div>
        </div>
      </div>
      <!-------------->
      
      <!-- 진행중인 채용 공고 -->
      <div class="row m-top-40">
        <div class="no-select">
        <div class="mg-20">
        <h3><i class="fa fa-user" aria-hidden="true"></i>&nbsp;
        <b>진행중인 채용 공고</b></h3></div></div>
        <div class="text-center topborder bggrey title-deco">
          <div class="bggrey padding-10 col-md-9"><b>채용공고명</b></div>
          <div id="adend" class="bggrey padding-10 col-md-3"><b>모집마감일</b></div>
        </div>
      </div>
        <c:forEach var="a" items="${adlist }" varStatus="status">
        <div class="row roomy-20 text-center">
          <a href="../ad/ad.do?cid=${a.c_id }&adid=${a.ad_id}">
            <div class="col-md-9 underline">${a.ad_title }</div>
            <div class="col-md-3 underline">${a.ad_end }</div>
          </a>
        </div>
        </c:forEach>
      
      <!-- 면접 후기 작성 -->
      <div class="row m-top-40">
        <div><h3><i class="fa fa-pencil" aria-hidden="true"></i>&nbsp; <b>면접 후기 작성</b></h3></div>
        <div class="row room row-border topborder">
          <form action="index.html" method="post">
            <div class="padding-10">면접은 만족 하셨나요?</div>
            <div class="padding-5"><input type=radio value=1 name=goodbad checked>만족 <input type=radio value=2 name=goodbad>불만족</div>
            
            <div class="padding-10">지원하신 직무</div>
            <div class="padding-5"><input class="form-control" type=text name=job></div>
            
            <div class="padding-10">경험하신 면접에 대하여 작성하여 주세요</div>
            
            <div class="col-auto"><textarea class="form-control"></textarea></div>
            <div class="col-auto">
              <input type=submit value="제출" class="btn btn-primary mb-3">
            </div>
          </form>
        </div>
      </div>
      <!-------------->
      
      <!-- 면접 후기 -->
     <div class="row m-top-40">
          <div class="no-select">
          <h3><i class="fa fa-quote-right" aria-hidden="true"></i>&nbsp;<b>면접 후기</b></h3></div>
        <c:forEach var="r" items="${review }" varStatus="status">
          <div>
            <c:if test="${r.review_goodbad==1}">
              <div class="review-job">
                <i class="fa fa-thumbs-up" aria-hidden="true"></i>&nbsp;&nbsp;<b>${r.review_job }</b>
                <button class="helpful-btn">도움이됐어요</button>
                <button class="delete-btn">삭제</button>
              </div>
              <div class="review-content">${r.review_content }</div>
            </c:if>
            <c:if test="${r.review_goodbad==2}">
              <div class="review-job">
                <i class="fa fa-thumbs-down" aria-hidden="true"></i>&nbsp;&nbsp;<b>${r.review_job }</b>
                <button class="helpful-btn">도움이됐어요</button>
                <button class="delete-btn">삭제</button>
              </div>
              <div class="review-content">${r.review_content }</div>
            </c:if>
          </div>
        </c:forEach>
      </div>
      <!-------------->
    </div>
  </section>


  
  <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=cf7ea2881e3c2b76986cc65a16553a55&libraries=services"></script>
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
  

</body>
</html>