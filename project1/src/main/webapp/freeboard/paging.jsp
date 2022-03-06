<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="sist.com.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

    <div class="row roomy-20">
      <div class="page no-select">
        <ul>
          <c:if test="${startPage>1 }">
            <li class="paging" data-page="${startPage-1 }"><i class="fa fa-caret-left" aria-hidden="true"></i></li>
          </c:if>
          <c:forEach var="i" begin="${startPage }" end="${endPage }">
            <c:if test="${i==curPage }">
              <li class="current">${i }</li>
            </c:if>
            <c:if test="${i!=curPage }">
              <li class="paging" data-page="${i }">${i }</li>
            </c:if>
          </c:forEach>
          <c:if test="${endPage<totalPage }">
            <li class="paging" data-page="${endPage+1 }"><i class="fa fa-caret-right" aria-hidden="true"></i></li>
          </c:if>
        </ul>
      </div>
    </div>
    