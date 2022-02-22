<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="sist.com.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" href="../css/search_company.css">
  <link rel="stylesheet" href="../css/home.css">
</head>
<body>
  <section>
    <div class="container container-pad">
      <div class="row roomy-20 m-top-70 text-center no-select">
        <h4><b>현장감이 느껴지는 생생한 면접 후기</b></h4>
        <h5>기업명을 검색하여</h5>
        <h5><span class="text-finence">기업정보</span>와 <span class="text-finence">면접후기</span>를 확인 해보세요!</h5>
      </div>
      <div class="search-container">
        <div class="search-bar">
          <input type="text" class="search-input" placeholder="회사 이름으로 검색하세요">
          <button type="submit" class="search-button"><i class="fa fa-search"></i></button>
        </div>
      </div>
      <div class="row m-top-40">
        <c:forEach var="c" items="${company }" varStatus="status">
          <div class="col-md-4 pad-5">
            <div class="room">
              <table>
                <tr>
                  <td class="c-logo"><img class="c-logoimg" src="${c.c_logo }"></td>
                  <td class="c-content short-container"><a href="../company/company.do?cid=${c.c_id }">
                      <div class="c-name short-line">
                        <b>${c.c_name }</b>
                      </div>
                      <div class="short-multi-line small-font">${review[status.index] }</div>
                  </a></td>
                </tr>
              </table>
            </div>
          </div>
        </c:forEach>
      </div>
    </div>
  </section>

          
  <div class="search_container">
    <p class="search_title">현장감이 느껴지는 생생한 면접 후기</p>
    <p>기업명을 검색하여</p>
    <p>
      <span class="font_bold">기업정보</span>와 <span class="font_bold">면접후기</span>를 확인 해보세요!
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
  <div class="row roomy-20 m-top-20">
    <div class="no-select">
      <h4>
        &nbsp;&nbsp;<i class="fa fa-thumbs-o-up" aria-hidden="true"></i> BEST 기업 & 면접 리뷰
      </h4>
    </div>
    <c:forEach var="c" items="${company }" varStatus="status">
      <div class="col-md-4 pad-5">
        <div class="room">
          <table>
            <tr>
              <td class="c-logo"><img class="c-logoimg" src="${c.c_logo }"></td>
              <td class="c-content short-container"><a href="../company/company.do?cid=${c.c_id }">
                  <div class="c-name short-line">
                    <b>${c.c_name }</b>
                  </div>
                  <div class="short-multi-line small-font">${review[status.index] }</div>
              </a></td>
            </tr>
          </table>
        </div>
      </div>
    </c:forEach>
  </div>
</body>
</html>