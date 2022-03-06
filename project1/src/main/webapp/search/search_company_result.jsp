<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="sist.com.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="m-top-40 result-container">
  <c:forEach var="c" items="${c_result }" varStatus="status">
    <div class="row roomy-20 row-border">
      <div class="col-sm-2">
        <div class="logo-container">
          <img class="clogo" src="${c.c_logo }">
        </div>
      </div>
      <div class="col-sm-7">
        <div class="content short-line company-name">
          <a href="../company/company.do?cid=${c.c_id }">${c.c_name }</a>
        </div>
        <div class="content short-line no-select">${c.c_industry }&nbsp;&nbsp;|&nbsp;&nbsp;${c.c_address }</div>
      </div>
      <div class="col-sm-3">
        <div class="review-pad review">기업리뷰 ${r_result[status.index] }개</div>
      </div>
    </div>
  </c:forEach>
</div>
