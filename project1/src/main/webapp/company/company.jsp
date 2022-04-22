<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <link rel="stylesheet" href="../css/company.css">
 <script type="text/javascript">
    $(function() {
    	
    	<!-------------- 리뷰 --------------->
        reviewList();
        
        $('#review-insert-btn').click(function() {
            
            let job = $('#review-job').val()
           
            if (job.trim() == "") {
                $('#review-job').focus();
                return;
            }
            
            let content = $('#review-content').val();
            
            if (content.trim() == "") {
                $('#reply-content').focus();
                return;
            }

             $.ajax({
                type:'POST',
                url : '../review/insert.do',
                data : $('#review-form').serialize(),
                success : function(){
                	reviewList();
               }
            });
        })
        
        $('#delete-btn').click(function() {
            var result = confirm('게시물을 삭제하시겠습니까?');
            if (result) {
                let bid = $('#bid').val();
                $.ajax({
                    type:'post',
                    url : '../freeboard/delete.do',
                    data : {"bid" : bid},
                    success : function(result){
                        alert("삭제되었습니다");
                        location.href = result; 
                    }
                });
            }
        })
        <!-------------------------------------------> 
        <!-------------- 맨 위로 이동 버튼 -------------->
        $(window).scroll(function() {
            if ($(this).scrollTop() > 100) {
                $('.scrollToTop').fadeIn();
            } else {
                $('.scrollToTop').fadeOut();
            }
        });
        $('.scrollToTop').click(function() {
            $('html, body').animate({
                scrollTop : 0
            }, 800);
            return false;
        });
        <!-------------------------------------------> 
    })
    
    function reviewList() {
        $('#review-content').val("");
        $('#review-job').val("");
        let cid = $('#cid').val();
        $.ajax({
            type : 'get',
            url : '../review/review.do',
            data : {"cid" : cid},
            success : function(result) {
                $('#review').html(result);
            }
        });
    }
  </script>
</head>
<body>
  <!-- 여기부터 -->
  <section>
    <div class="container container-pad">
      <!-- 기업 상세 정보 -->
      <div class="row m-top-50"><span class="cname">${company.c_name }</span></div>
      <div class="row roomy-20 row-border">
        <input type="hidden" id="cid" name="cid" value="${company.c_id }"> <!-- 회사 번호 -->
        <div class="col-md-3">
          <div class="logo-container">
            <img class="clogo" src="${company.c_logo }">
          </div>
        </div>
        <div class="col-md-9">
          <table class="table-top">
            <tr>
              <th class="top-table-left">업종</th>
              <td>${company.c_industry }</td>
            </tr>
            <tr>
              <th class="top-table-left">본사</th>
              <td>${company.c_address }</td>
            </tr>
            <tr>
              <th class="top-table-left">기업 규모</th>
              <td>${company.c_size }</td>
            </tr>
          </table>
        </div>
        </div>

      
      <!-- 위치 -->
      <div class="row m-top-40 no-select">
        <h3>
          <i class="fa fa-user" aria-hidden="true"></i>&nbsp;<b>위치</b>
        </h3>
      </div>
      <div class="row">
        <div id="map" style="width: 100%; height: 200px;"></div>
      </div>
      <!-------------->

      <!-- 진행중인 채용 공고 -->
      <div class="row m-top-40 no-select"><h3><i class="fa fa-user" aria-hidden="true"></i>&nbsp;<b>진행중인 채용 공고</b></h3></div>
      <div class="row text-center topborder bggrey title-deco">
        <div class="col-md-9 bggrey roomy-10">
          <b>채용공고명</b>
        </div>
        <div id="adend" class="col-md-3 bggrey roomy-10">
          <b>모집마감일</b>
        </div>
      </div>
      <c:forEach var="a" items="${adlist }" varStatus="status">
        <div class="row roomy-20 text-center">
          <a href="../ad/ad.do?adid=${a.ad_id}">
            <div class="col-md-9">${a.ad_title }</div>
            <div class="col-md-3"><b>${a.ad_end }</b></div>
          </a>
        </div>
      </c:forEach>
      <!-------------->

      <!-- 면접 후기 -->
      <div class="row m-top-40 no-select">
        <h3>
          <i class="fa fa-quote-right" aria-hidden="true"></i>&nbsp;<b>면접 후기</b>
        </h3>
      </div>
      <div class="row topborder roomy-10">
        <div id="review">
          
        </div>
      </div>
      <!-------------->
      
      <!-- 면접 후기 작성 -->
      <c:if test="${sessionScope.id!=null }">
        <div class="row m-top-40"><h3><i class="fa fa-pencil" aria-hidden="true"></i>&nbsp; <b>면접 후기 작성</b></h3></div>
        <div class="row roomy-20 topborder">
          <form id="review-form">
            <input type="hidden" name="uid" value="${sessionScope.id }">
            <input type="hidden" name="cid" value="${company.c_id }">
            <div class="col-md-12 roomy-10">
              <b>면접은 만족 하셨나요?</b>&nbsp;&nbsp;<input type="radio" value=1 name="goodbad" id="good" checked><label for="goodbad"> 만족</label> <input type=radio value=2 name=goodbad id="bad"><label for="bad"> 불만족</label><br><br>
              <b>지원하신 직무</b><br>
              <input id="review-job" type=text name=job><br><br>
              <b>경험하신 면접에 대하여 작성하여 주세요</b><br>
              <textarea id="review-content" rows="6" name=content></textarea>
              <input type="button" value="제출" id="review-insert-btn">
            </div>
          </form>
        </div>
      </c:if>
      <c:if test="${sessionScope.id==null }">
        <div class="row">     
          <div class="col-md-12 login-to-review no-select">
            로그인하여 리뷰를 작성해보세요!
          </div> 
        </div>
      </c:if>
      <!-------------->
      
      <div class="row roomy-40 text-center">
        <a href="../main/main.do" class="btn btn-primary">홈으로</a>
        <div class="row no-select text-center" style="margin-top: 20px;">조회수 ${company.c_visits }</div>
      </div>
    </div>
 
    <!-- 맨위로 올라가는 버튼 -->
    <div style="position: fixed; bottom: 50px; right: 60px;">
      <a href="#" class="scrollToTop"><i class="fa fa-chevron-up" aria-hidden="true"></i></a>
    </div>        
  </section>


  <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=cf7ea2881e3c2b76986cc65a16553a55&libraries=services"></script>
  <script type="text/javascript">
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
  	geocoder.addressSearch('${company.c_address }',
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
</body>
</html>