<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
    $(function() {
    	var tab = "<c:out value='${tab }'/>";
        $('.paging').css("cursor", "pointer")
        $('.paging').click(function() {
            let page = $(this).val();
            $.ajax({
                type : 'get',
                url : '../freeboard/freeboardlist.do',
                data : {"page":page,"tab":tab},
                success : function(result) {
                    $('#wrapping').html(result);
                }
            })
        })
    })
</script>
<div id="wrapping">
  <!--    리스트    -->
  <div id="list">
    <c:forEach var="b" items="${board }" varStatus="status">
      <div class="row roomy-15 border-bttom">
        <div id="board-title" class="col-sm-7 short-line">
  
          <!-- 게시물 카테고리 / 제목 -->
          <a href="../freeboard/detail.do?bid=${b.board_id }"><span class="category">[${b.board_category }]&nbsp;&nbsp;</span>${b.board_title }</a>
          <!-- ----------------- -->
  
          <!-- 댓글 수 -->
          <span class="rcount">(${rcount[status.index] })</span>
          <!-- ----------------- -->
  
          <!-- 오늘 날짜에는 new 표시 -->
          <fmt:formatDate value="<%=new java.util.Date()%>" pattern="yyyy-MM-dd" var="today" />
          <c:if test="${today==b.board_date }">
            <sup>new</sup>
          </c:if>
          <!-- ----------------- -->
        </div>
        <div id="board-uid" class="col-sm-2 text-center">${b.u_id }</div>
        <div id="board-date" class="col-sm-2 text-center">${b.board_date }</div>
        <div id="board-visits" class="col-sm-1 text-center">
          <span id="small">조회수 </span>${b.board_visits }</div>
      </div>
    </c:forEach>
  </div>
  
  <!-- 자유게시판에서만 글쓰기 -->
  <c:if test="${tab eq 'tab1' }">
    <!-- 글쓰기 버튼 로그인시에만 보이기 -->
    <c:if test="${sessionScope.id!=null }">
      <div class="row">
        <div class="post">
          <a href="../freeboard/insert.do" class="btn btn-primary">글쓰기</a>
        </div>
      </div>
    </c:if>
  </c:if>
  
  <!--    페이징    -->
  <div class="row roomy-20">
    <div class="page no-select">
      <ol>
        <c:if test="${startPage>1 }">
          <li class="paging" value="${startPage-1 }"><i class="fa fa-caret-left" aria-hidden="true"></i></li>
        </c:if>
        <c:set var="style" value="" />
        <c:forEach var="i" begin="${startPage }" end="${endPage }">
          <c:if test="${i==curPage }">
            <c:set var="style" value="class=current" />
            <li class="current">${i }</li>
          </c:if>
          <c:if test="${i!=curPage }">
            <li class="paging" value="${i }">${i }</li>
          </c:if>
        </c:forEach>
        <c:if test="${endPage<totalPage }">
          <li class="paging" value="${endPage+1 }"><i class="fa fa-caret-right" aria-hidden="true"></i></li>
        </c:if>
      </ol>
    </div>
  </div>

</div>