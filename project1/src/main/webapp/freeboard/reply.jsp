<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <div class="row roomy-20">
        <div class="col-sm-2 short-line">${r.u_id }</div>
        <div class="col-xs-8 col-sm-7">
          ${r.reply_content } 
        </div>
        <div class="col-xs-4 col-sm-3 reply_delete_btn">
          ${r.reply_date }&nbsp;&nbsp;
          <c:if test="${r.u_id==sessionScope.id}"><a href="../freeboard/reply_delete_ok.do?bid=${r.board_id }&rid=${r.reply_id }"><i class="fa fa-times" aria-hidden="true"></i></a></c:if>
        </div>
      </div>