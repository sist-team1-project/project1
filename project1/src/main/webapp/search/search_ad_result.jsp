<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
    $(function() {
    	$('.paging').css("cursor","pointer");
        $('.paging').click(function() {
            let url = $(this).attr('data-page');
            $.ajax({
                type : 'get',
                url : '../search/searchad_result.do?' + url,
                success : function(result) {
                    $('#result').html(result);
                }
            });
        })
    })
</script>
<!--  공고 리스트  -->
<div id="list">
  <c:forEach var="a" items="${ad }" varStatus="status">
    <div class="row roomy-10">
      <div class="col-sm-3">${company[status.index] }</div>
      <div class="col-sm-7">
        <div class="ad-title"><a href="../ad/ad.do?adid=${a.ad_id}">${a.ad_title }</a></div>
        <div class="small-font list-content">${a.ad_we } | ${a.ad_education } | ${a.ad_worktype }</div>
        <div class="small-font list-content">${a.ad_workplace } </div>
      </div>
      <div class="col-sm-2 ad_end">
        <c:if test="${today==a.ad_end }"><b>마감일</b></c:if> <!-- 오늘일 경우 -->
        <c:if test="${today!=a.ad_end }"><b>${a.ad_end }</b></c:if> <!-- 오늘이 아닐 경우 -->
      </div>
    </div>
    <hr>
  </c:forEach>
</div>
<!-- ----------- -->


<!--    페이징    -->
<div class="row roomy-10">
  <div class="page no-select">
    <ul>
      <c:if test="${startPage>1 }">
        <li class="paging" data-page="${url }&page=${startPage-1 }"><i class="fa fa-caret-left" aria-hidden="true"></i></li>
      </c:if>
      <c:forEach var="i" begin="${startPage }" end="${endPage }">
        <c:if test="${i==curPage }">
          <li class="current">${i }</li>
        </c:if>
        <c:if test="${i!=curPage }">
          <li class="paging" data-page="${url }&page=${i }">${i }</li>
        </c:if>
      </c:forEach>
      <c:if test="${endPage<totalPage }">
        <li class="paging" data-page="${url }&page=${endPage+1 }"><i class="fa fa-caret-right" aria-hidden="true"></i></li>
      </c:if>
    </ul>
  </div>
</div>
<!-- ----------- -->