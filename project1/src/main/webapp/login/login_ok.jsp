<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>

  <c:when test="${result=='FAIL' }">
    <script>
    alert("로그인 정보가 틀렸습니다");
    history.back();
   </script>
  </c:when>
  <c:otherwise>
    <c:redirect url="../main/main.do"/>
  </c:otherwise>
</c:choose>