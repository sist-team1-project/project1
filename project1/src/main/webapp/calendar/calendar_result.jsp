<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<table class="table">
  <tr height="50">
    <c:forEach var="w" items="${strWeek }">
      <th class="text-center">${w }</th>
    </c:forEach>
  </tr>
  <c:set var="week" value="${week }" />
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
    <c:set var="week" value="${week+1 }" />
    <c:if test="${week>6 }">
      <c:set var="week" value="0" />
      </tr>
      <tr height="50">
    </c:if>
  </c:forEach>
  </tr>
</table>