<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="sist.com.model.*,sist.com.dao.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <link rel="stylesheet" href="../css/home.css">
</head>
<body>
<!-- 여기부터 -->
  <section>
    <div class="container">
      <div class="row m-top-40">
        <c:if test="${sessionScope.id!=null }">
          <h5 class="text-center">${sessionScope.name }님 안녕하세요!</h5>
        </c:if>
      </div>
      
     <c:if test="${not empty cookieList}">
      <!---- 최근 본 공고 목록(cookie) ---->
        <div class="row roomy-20" id="cookieDiv">
          <div class="no-select">
            <h4>&nbsp;&nbsp;<i class="fa fa-address-card" aria-hidden="true"></i> 최근 본 공고</h4>
          </div>  
          <div id="cookieView">
            <c:forEach var="ck" items="${cookieList}" varStatus="status">
              <div class="col-md-3 pad-5 cookieArea">
                <div class="unit c-container">
                  <div class="small-font no-select">
                    <span class="lightyellowtag">${ck.ad_end} </span>
                    <span class="greytag">${ck.ad_workplace} </span>
                   <a href="#" onclick="setCookie('adview', ${ck.ad_id}, -1, this)"><i class="fa fa-window-close c-delete"></i></a>
                  </div>
                  <div class="roomy-10 short-line">
                    <a href="../ad/ad.do?cid=${ck.c_id }&adid=${ck.ad_id}"><b>${ck.ad_title}</b></a>
                  </div>
                </div>
              </div>
            </c:forEach>
          </div>      
        </div>
      <!-------------------->
     </c:if>
     
      <!-- 인기기업 9개 -->
      <div class="row roomy-20">
        <div class="no-select"><h4>&nbsp;&nbsp;<i class="fa fa-thumbs-o-up" aria-hidden="true"></i> BEST 기업 & 면접 리뷰</h4></div>
        <c:forEach var="c" items="${company }" varStatus="status">
          <div class="col-md-4 pad-5">
            <div class="unit c-container">
              <table>
                <tr>
                  <td class="c-logo">
                    <img class="c-logoimg" src="${c.c_logo }">
                  </td>
                  <td class="c-content">
                    <a href="../company/company.do?cid=${c.c_id }">
                      <div class="c-name short-line"><b>${c.c_name }</b></div>
                      <div class="short-container"><span class="short-multi-line small-font">${review[status.index] }</span></div>
                    </a>
                  </td>
                </tr>
              </table>
            </div>
          </div>
        </c:forEach>
      </div>
      <!-------------->
      
      <!--  대기업 리스트 -->
      <div class="row roomy-20">
        <div class="col-md-12 no-select"><h4><i class="fa fa-building-o" aria-hidden="true"></i> 대기업</h4></div>
        <div class="col-md-12">
          <div class="multi-slider">
            <c:forEach var="b" items="${bigCompany }" varStatus="status">
              <a href="../company/company.do?cid=${b.c_id }"><img class="slider-logo" src="${b.c_logo }" alt=""></a>
            </c:forEach>
          </div> 
        </div>
      </div>
      <!-------------->
        
      <!-- 채용공고 top9 -->
      <div class="row roomy-20">
        <div class="col-md-12 no-select"><h4><i class="fa fa-handshake-o" aria-hidden="true"></i> BEST 공고</h4></div>
        <div class="col-md-12 adplus"><h6><a href="../ad/ad.do">더 많은 공고를 보고싶다면?&nbsp;<i class="fa fa-hand-o-right" aria-hidden="true"></i></a></h6></div>
        <c:forEach var="a" items="${ad }" varStatus="status">
          <div class="col-md-6 pad-5">
            <div class="unit">
              <div class="small-font no-select">
                <span class="greentag"> ${a.ad_end } </span>&nbsp;
                <span class="greytag"> ${a.ad_we } </span>&nbsp;
                <span class="greytag"> ${a.ad_education } </span>
                <c:choose>
                  <c:when test="${sessionScope.id==null }">
                    <a href="#" onclick="return confirm('먼저 로그인을 진행해주세요')"><i class="fa fa-star-o favorite"></i></a>
                  </c:when>
                  <c:when test="${sessionScope.id!=null }">
                    <a href="#"><i class="fa fa-star favorite f-update"></i></a>                  
                  </c:when>
                </c:choose>
              </div>
              <div class="roomy-10 short-line">
                <a href="../ad/ad.do?cid=${a.c_id }&adid=${a.ad_id}"><b>${a.ad_title }</b></a>
              </div>
              <div class="small-font no-select">
                <span class="lightgreytag">${adCname[status.index] }</span>&nbsp;
                <span class="lightgreytag">${a.ad_workplace }</span>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
      <!-------------->  
       
      <!-- 책 추천 홍보 -->   
      <div class="row">     
        <div class="col-md-12 book pad-5 no-select">
          해당 공고와 관련된 자격증이 걱정이세요? 공고에서 요구하는 자격증의 수험서도 함께 추천 받아보세요!
        </div> 
      </div>
      <!-------------->
      
      <!-- 마감 임박 공고 -->
      <div class="row roomy-20">
        <div class="col-md-12 no-select"><h4><i class="fa fa-calendar-check-o" aria-hidden="true"></i> 마감 임박 공고</h4></div>
        <c:forEach var="a" items="${adEnd }" varStatus="status">
          <div class="col-md-4 pad-5">
            <div class="unit">
              <div class="small-font no-select">
                <span class="redtag"> ${a.ad_end } </span>&nbsp;
                <span class="greytag"> ${a.ad_we } </span>&nbsp;
                <span class="greytag"> ${a.ad_education } </span>
                <c:choose>
                  <c:when test="${sessionScope.id==null }">
                    <a href="#" onclick="return confirm('먼저 로그인을 진행해주세요')"><i class="fa fa-star-o favorite"></i></a>
                  </c:when>
                  <c:when test="${sessionScope.id!=null }">
                    <a href="#"><i class="fa fa-star favorite f-update"></i></a>                  
                  </c:when>
                </c:choose>
              </div>
              <div class="roomy-10 short-line">
                <a href="../ad/ad.do?cid=${a.c_id }&adid=${a.ad_id}"><b>${a.ad_title }</b></a>
              </div>
              <div class="small-font no-select">
                <span class="lightgreytag">${adEndCname[status.index] }</span>&nbsp;
                <span class="lightgreytag">${a.ad_workplace }</span>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
      <!-------------->
      
      <!-- 자유 게시판 -->
      <div class="row roomy-20">
        <div class="col-md-12 no-select"><h4><i class="fa fa-user-circle-o" aria-hidden="true"></i> 자유 게시판</h4></div>
        <div class="col-md-6 pad-5">
          <div class="unit">
            <div class="row board-title pad-5 no-select">
              <div class="col-xs-7"><b>조회수 순</b>&nbsp;<sup>HOT</sup></div>
              <div class="col-xs-5"><a href="../freeboard/freeboard.do"><span class="boardplus">더보기</span></a></div>
            </div>
            <c:forEach var="bv" items="${freeBoardVisits }" varStatus="status">
              <div class="pad-5 short-line"><span class="post-category">[${bv.board_category}] </span><a href="../freeboard/detail.do?bid=${bv.board_id }">${bv.board_title }</a></div>
            </c:forEach>
          </div>
        </div>
        <div class="col-md-6 pad-5">
          <div class="unit">
            <div class="row pad-5 no-select">
              <div class="col-xs-7"><b>댓글 순</b>&nbsp;<sup>HOT</sup></div>
              <div class="col-xs-5"><a href="../freeboard/freeboard.do"><span class="boardplus">더보기</span></a></div>
            </div>
            <c:forEach var="br" items="${freeBoardReply }" varStatus="status">
              <div class="pad-5 short-line"><span class="post-category">[${br.board_category}] </span><a href="../freeboard/detail.do?bid=${br.board_id }">${br.board_title }</a></div>
            </c:forEach>
          </div>
        </div>
      </div>
      <!-------------->  
      
    </div>
  </section>
  <!-- 슬라이더 -->
  <script type="text/javascript" src="../css/slick/slick.js" charset="utf-8"></script>
  <script type="text/javascript" src="../css/slick/slick.min.js" charset="utf-8"></script>
  <script type="text/javascript">
	$('.multi-slider').slick({
		dots : true,
		infinite : false,
		speed : 300,
		slidesToShow : 5,
		slidesToScroll : 5,
		centeredSlides : true,
		accessibility : false,
		responsive : [ {
			breakpoint : 1024,
			settings : {
				slidesToShow : 4,
				slidesToScroll : 4,
				infinite : true,
				dots : true
			}
		}, {
			breakpoint : 600,
			settings : {
				slidesToShow : 3,
				slidesToScroll : 3
			}
		}, {
			breakpoint : 480,
			settings : {
				slidesToShow : 1,
				slidesToScroll : 1
			}
		} ]
	});
	
    function setCookie(cookie_name, value, days, obj) {
        var exdate = new Date();
        exdate.setDate(exdate.getDate() + days);
        // 설정 일수만큼 현재시간에 만료값으로 지정

        var cookie_value = escape(value) + ((days == null) ? '' : '; expires=' + exdate.toUTCString());
        document.cookie = cookie_name + '=' + cookie_value+"; path=/";
        
        // 클릭한 X아이콘에서 가장 가까운 클래스 pad-5를 찾아 삭제
        $(obj).closest('.cookieArea').remove();
        
        if ($('.cookieArea').length == 0) {
        	// 최근 본 공고를 모두 지위서 0개인 경우에는 '최근 본 공고' 글자까지 삭제
 			$('#cookieDiv').remove();
		}
      }

  </script>
</body>
</html>