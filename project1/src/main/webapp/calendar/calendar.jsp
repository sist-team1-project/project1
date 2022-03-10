<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="sist.com.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/calendar.css">
<script type="text/javascript">
    $(function() {
    	$.ajax({
            type:'get',
            url : '../calendar/list.do',
            success : function(result){
            	$('#calendar').html(result);
            }
        });
    })
</script>
</head>
<body>
  <div class="container">
    <div class="row m-top-30 ">
      <div class="col-md-12 text-center">
        <h3><i class="fa fa-calendar" aria-hidden="true"></i>&nbsp;<b>이번달 채용공고 한눈에 보기!</b></h3>
      </div>
    </div>
    <div id="calendar">
    
    </div>
  </div>
</body>
</html>