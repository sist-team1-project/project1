<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="sist.com.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <div class="container">
    <div class="row m-top-40 roomy-20">
      <div class="col-md-12 text-center">
        <h4><a href="">${year }년 ${month }월</a></h4>
      </div>
    </div>
    <table class="table">
      <tr height="50">
        <c:forEach var="w" items="${strWeek }">
          <th class="text-center">${w }</th>
        </c:forEach>
      </tr>
      <c:set var="week" value="${week }"/>
      <c:forEach var="i" begin="1" end="${lastday }">
        <c:if test="${i==1 }">
          <tr height="50">
          <c:forEach var="j" begin="1" end="${week }">
            <td class="text-center">&nbsp;</td>
            <%-- 빈칸 (요일까지) --%>
          </c:forEach>
        </c:if>
        <c:if test="${i==rdays[i]}">
          <td class="text-center danger">${i }</td>
        </c:if>
        <c:if test="${i!=rdays[i] }">
          <td class="text-center">${i }</td>
        </c:if>
        <c:set var="week" value="${week+1 }"/>
        <c:if test="${week>6 }">
          <c:set var="week" value="0"/>
            </tr>
            <tr height="50">
          </c:if>
        </c:forEach>
      </tr>
    </table>
  </div>
</body>
</html>