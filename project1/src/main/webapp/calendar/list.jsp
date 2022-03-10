<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
    $(function() {
    	$('span').css("cursor","pointer");
    	$('span').click(function() {
    		let year = $(this).attr("data-year");
    		let month = $(this).attr("data-month");
    		$.ajax({
                type:'get',
                url : '../calendar/list.do',
                data : {"year" : year, "month" : month},
                success : function(result){
                    $('#calendar').html(result);
                }
            });
        });
    })
</script>

<div class="m-top-30 roomy-20 text-center no-select">
  <h4>
    <span data-year="${year }" data-month="${month-1}"><i class="fa fa-chevron-left" aria-hidden="true"></i></span>
    <span class="date"><b>${year }년 ${month }월</b></span>
    <span data-year="${year }" data-month="${month+1}"><i class="fa fa-chevron-right" aria-hidden="true"></i></span>
  </h4>
</div>

<table class="table">  
  <tr>
    <c:forEach var="w" items="${strWeek }">
      <th class="text-center">${w }</th>
    </c:forEach>
  </tr>
  <c:forEach var="i" begin="1" end="${lastday }" varStatus="status">
    <c:if test="${i==1 }">
      <tr>
      <c:forEach var="j" begin="1" end="${week-1 }">
        <td class="text-center">&nbsp;</td>
      </c:forEach>
    </c:if>
    <td>
      <div>${i }</div>
      <c:forEach var="a" items="${ad[status.index-1] }" varStatus="status2">
        <div class="title short-line"><a href="../ad/ad.do?adid=${a.ad_id}">${a.ad_title }</a></div>
      </c:forEach>
    </td>
    <c:set var="week" value="${week+1 }"/>
    <c:if test="${week>7 }">
      <c:set var="week" value="1"/>
      </tr>
      <tr height="50">
    </c:if>
  </c:forEach>
</table>