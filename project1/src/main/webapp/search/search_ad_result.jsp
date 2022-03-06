<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
$(function(){
    $("div").slice(0, 10).show();
    $("#load").click(function(e){
        e.preventDefault();
        $("div:hidden").slice(0, 10).show();
        if($("div:hidden").length == 0){
            alert("No more divs");
        }
    });
});
</script>
<c:forEach var="a" items="${ad }" varStatus="status">
  <div>
  <a href="../ad/detail.do">${a.ad_title }
  </div>
</c:forEach>
<a href="#" id="load">더 보기</a>