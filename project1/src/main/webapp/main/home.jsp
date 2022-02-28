<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="sist.com.model.*"%>
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
      
      <!-- 인기기업 9개 -->
      <div class="row roomy-20 m-top-20">
        <c:if test="${sessionScope.id!=null }">
          <div class="roomy-10">
            <h5 class="text-center">${sessionScope.name }님 안녕하세요!</h5>
          </div>
        </c:if>
        <div class="no-select"><h4>&nbsp;&nbsp;<i class="fa fa-thumbs-o-up" aria-hidden="true"></i> BEST 기업 & 면접 리뷰</h4></div>
        <c:forEach var="c" items="${company }" varStatus="status">
          <div class="col-md-4 pad-5">
            <div class="room">
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
        <div class="no-select"><h4>&nbsp;&nbsp;<i class="fa fa-building-o" aria-hidden="true"></i> 대기업</h4></div>
        <div class="multi-slider">
          <c:forEach var="b" items="${bigCompany }" varStatus="status">
            <div>
              <a href="../company/company.do?cid=${b.c_id }">
                <img class="slider-logo" src="${b.c_logo }" alt="">
              </a>
            </div>
          </c:forEach>
        </div> 
      </div>
      <!-------------->

      <!-- 채용공고 top9 -->
      <div class="row roomy-20">
        <div class="no-select"><h4>&nbsp;&nbsp;<i class="fa fa-handshake-o" aria-hidden="true"></i> BEST 공고</h4></div>
        <div class="post adplus">
          <h6><a href="../ad/ad.do">더 많은 공고를 보고싶다면?&nbsp;<i class="fa fa-hand-o-right" aria-hidden="true"></i></a></h6>
        </div>
        <c:forEach var="a" items="${ad }" varStatus="status">
          <div class="col-md-6 pad-5">
            <div class="room">
              <table>
                <tr>
                  <td class="small-font no-select">
                    <span class="greentag"> ${a.ad_end } </span>&nbsp;
                    <span class="greytag"> ${a.ad_we } </span>&nbsp;
                    <span class="greytag"> ${a.ad_education } </span>&nbsp;
                    <a href="#"><i class="fa fa-star-o favorite"></i></a>
                  </td>
                </tr>
                <tr>
                  <td>
                    <a class="short-line" href="../ad/ad.do?cid=${a.c_id }&adid=${a.ad_id}"><b>${a.ad_title }</b></a>
                  </td>
                </tr>
                <tr>
                  <td class="small-font no-select">
                    <span class="lightgreytag">${adCompany[status.index].c_name }</span>&nbsp;
                    <span class="lightgreytag">${adCompany[status.index].c_address }</span>
                  </td>
                </tr>
              </table>
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
        <div class="no-select"><h4>&nbsp;&nbsp;<i class="fa fa-calendar-check-o" aria-hidden="true"></i> 마감 임박 공고</h4></div>
        <c:forEach var="a" items="${adEnd }" varStatus="status">
          <div class="col-md-4 pad-5">
            <div class="room">
              <table>
                <tr>
                  <td class="small-font no-select">
                    <span class="redtag"> ${a.ad_end } </span>&nbsp;
                    <span class="greytag"> ${a.ad_we } </span>&nbsp;
                    <span class="greytag"> ${a.ad_education } </span>
                    <a href="#"><i class="fa fa-star-o favorite"></i></a>
                  </td>
                </tr>
                <tr>
                  <td>
                    <a class="short-line" href="../ad/ad.do?cid=${a.c_id }&adid=${a.ad_id}"><b>${a.ad_title }</b></a>
                  </td>
                </tr>
                <tr>
                  <td class="small-font no-select">
                    <span class="lightgreytag">${adEndCompany[status.index].c_name }</span>&nbsp;
                    <span class="lightgreytag">${adEndCompany[status.index].c_address }</span>
                  </td>
                </tr>
              </table>
            </div>
          </div>
        </c:forEach>
      </div>
      <!-------------->
      
      <!-- 자유 게시판 -->   
      <div class="row roomy-20">
        <div class="no-select"><h4>&nbsp;&nbsp;<i class="fa fa-user-circle-o" aria-hidden="true"></i> 자유 게시판</h4></div>
        <div class="col-md-6 pad-5">
          <div class="room">
            <table>
              <tr>
                <th class="post pad-5 no-select">
                조회수 순&nbsp;<sup>HOT</sup>
                </th>
                <th class="post postplus">
                  <a href="../freeboard/freeboard.do"><span>더보기</span></a> 
                </th>
              </tr>
              <c:forEach var="b" items="${freeBoardVisits }" varStatus="status">
                <tr>
                  <td class="pad-5 short-line"><span class="post-category">[${b.board_category}] </span><a href="#">${b.board_title }</a></td>
                </tr>
              </c:forEach>
            </table>
          </div>
        </div>
        <div class="col-md-6 pad-5">
          <div class="room">
            <table>
              <tr>
                <th class="post pad-5 no-select">
                댓글 순&nbsp;<sup>HOT</sup>
                </th>
                <th class="post postplus">
                  <a href="../freeboard/freeboard.do"><span>더보기</span></a>
                </th>
              </tr>
              <c:forEach var="b" items="${freeBoardVisits }" varStatus="status">
                <tr>
                  <td class="pad-5 short-line"><span class="post-category">[${b.board_category}] </span><a href="#">${b.board_title }</a></td>
                </tr>
              </c:forEach>
            </table>
          </div>
        </div>
      </div>
      <!-------------->  
      
    </div>
  </section>
</body>
</html>