<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <link rel="stylesheet" href="../css/search_ad.css">
  <script type="text/javascript" src="../js/search.js"></script>
  <script type="text/javascript">
    $(function() {
    	
        $('#we-checkall').click(function() {
        	if ($('#we-checkall').is(":checked")) $('.we').prop("checked", false);
        	if ($('#we-checkall').not(":checked")) $('#we-checkall').prop("checked", true);
        })
        $('.we').click(function() {
            if ($('.we').is(":checked")) $('#we-checkall').prop("checked", false);
        })
        
        $('#edu-checkall').click(function() {
            if ($('#edu-checkall').is(":checked")) $('.edu').prop("checked", false);
            if ($('#edu-checkall').not(":checked")) $('#edu-checkall').prop("checked", true);
        })
        $('.edu').click(function() {
            if ($('.edu').is(":checked")) $('#edu-checkall').prop("checked", false);
        })

        $('#size-checkall').click(function() {
            if ($('#size-checkall').is(":checked")) $('.size').prop("checked", false);
            if ($('#size-checkall').not(":checked")) $('#size-checkall').prop("checked", true);
        })
        $('.size').click(function() {
            if ($('.size').is(":checked")) $('#size-checkall').prop("checked", false);
        })
        
        $('#worktype-checkall').click(function() {
            if ($('#worktype-checkall').is(":checked")) $('.worktype').prop("checked", false);
            if ($('#worktype-checkall').not(":checked")) $('#worktype-checkall').prop("checked", true);
        })
        $('.worktype').click(function() {
            if ($('.worktype').is(":checked")) $('#worktype-checkall').prop("checked", false);
        })
        
        $('.qual-add').click (function () {                                        
            $('.qual').append (                        
                '<input type="text" name="qual" value="" placeholder="자격증 입력"> <input type="button" class="btn btn-default btn-sm qual-remove" value="삭제""><br>'                    
            );                            
            $('.qual-remove').on('click', function () { 
                $(this).prev().remove ();
                $(this).next ().remove ();
                $(this).remove ();
            });
        });
        
        $('.lang-add').click (function () {                                        
            $('.lang').append (                        
                '<input type="text" name="lang" value="" placeholder="언어 입력"> <input type="button" class="btn btn-default btn-sm lang-remove" value="삭제""><br>'                    
            );                            
            $('.lang-remove').on('click', function () { 
                $(this).prev().remove ();
                $(this).next ().remove ();
                $(this).remove ();
            });
        });
        
        $('input[type=date]').prop('min', function(){
            return new Date().toJSON().split('T')[0];
        });
        
        // 첫 시작시 로딩
        $.ajax({
            type : 'get',
            url : '../search/searchad_result.do',
            data : $('#search-form').serialize(),
            success : function(result) {
                $('#result').html(result);
            }
        });
        
        // 검색 버튼
        $('#search-btn').click(function() {
        	$.ajax({
                type : 'get',
                url : '../search/searchad_result.do',
                data : $('#search-form').serialize(),
                success : function(result) {
                    $('#result').html(result);
                }
            });
        })

    })
  </script>
</head>
<body>
  <!-- 여기부터 -->
  <section>
    <div class="container container-pad">
      <div class="roomy-10 m-top-40 no-select">
        <h3><i class="fa fa-search" aria-hidden="true"></i>&nbsp;<b>채용정보 상세검색</b></h3>
      </div>
      
      <!--  검색창  -->
      <div class="search-container">
      <form method="get" id="search-form">
        <div class="row">
          <div class="col-xs-2 roomy-10">키워드</div>
          <div class="col-xs-10 roomy-10">
            <input type="text" name="keyword" placeholder="공고 제목으로 검색">
          </div>
        </div>
        <div class="row">
          <div class="col-xs-2 roomy-10">지역</div>
          <div class="col-xs-10 roomy-10">
            <select name="sido" id="sido"></select>
            <select name="gugun" id="gugun"></select>
          </div>
        </div>
        
        <div class="row">
        
          <!-- 경력 -->
          <div class="col-xs-2 col-sm-2 roomy-10">경력</div>
          <div class="col-xs-10 col-sm-4 roomy-10">
            <input type='checkbox' id="we-checkall" name="we" value="신입|경력|관계없음" checked> 전체<br> 
            <input type='checkbox' class="we" name="we" value="신입"> 신입<br>
            <input type='checkbox' class="we" name="we" value="경력"> 경력<br>
            <input type='checkbox' class="we" name="we" value="관계없음"> 관계없음<br>
          </div>
          <!-- --- -->
          
          <!-- 학력 -->
          <div class="col-xs-2 col-sm-2 roomy-10">학력</div>
          <div class="col-xs-10 col-sm-4 roomy-10">
            <input type='checkbox' id="edu-checkall" name="edu" value="중졸이하|고졸|2~3년|4년|석사|박사|학력무관" checked> 전체 
            <input type='checkbox' class="edu" name='edu' value="중졸이하"> 중졸이하 
            <input type='checkbox' class="edu" name='edu' value="고졸"> 고졸<br>
            
            <input type='checkbox' class="edu"  name="edu" value="2~3년"> 대졸(2~3년) 
            <input type='checkbox' class="edu" name='edu' value="4년"> 대졸(4년)<br>
            
            <input type='checkbox' class="edu" name="edu" value="석사"> 석사 
            <input type='checkbox' class="edu"  name='edu' value="박사"> 박사 
            <input type='checkbox' class="edu" name='edu' value="학력무관"> 학력무관<br>
          </div>
        </div>
        <!-- --- -->
        
        <!-- 기업형태 -->
        <div class="row">
          <div class="col-xs-2 roomy-10">기업형태</div>
          <div class="col-xs-10 roomy-10">
            <input type='checkbox' id="size-checkall" name="size" value="대기업|중견기업|중소기업|기타|-" checked> 전체 
            <input type='checkbox' class="size" name="size" value="대기업 "> 대기업 
            <input type='checkbox' class="size" name="size" value="중견기업 "> 중견기업 
            <input type='checkbox' class="size" name="size" value="중소기업"> 중소기업
          </div>
        </div>
        <!-- --- -->
        
        <!-- 근무형태 -->
        <div class="row">
          <div class="col-xs-2 roomy-10">근무형태</div>
          <div class="col-xs-10 roomy-10">
            <input type='checkbox' id="worktype-checkall" name="worktype" value="주 5일|주 6일|주 4일|주 3일|주 2일|주 1일|주 7일" checked> 전체
            <input type='checkbox' class="worktype" name="worktype" value="주 5일"> 주 5일 
            <input type='checkbox' class="worktype" name="worktype" value="주 6일"> 주 6일 
            <input type='checkbox' class="worktype" name="worktype" value="주 4일"> 주 4일 
            <input type='checkbox' class="worktype" name="worktype" value="주 3일"> 주 3일 
            <input type='checkbox' class="worktype" name="worktype" value="주 2일"> 주 2일 
            <input type='checkbox' class="worktype" name="worktype" value="주 1일"> 주 1일 
            <input type='checkbox' class="worktype" name="worktype" value="주 7일"> 주 7일
          </div>
        </div>
        <!-- --- -->
        
        <!-- 자격면허 -->
        <div class="row">
          <div class="col-xs-2 roomy-10">자격면허</div>
          <div class="col-xs-10 roomy-10 qual">
            <input type="text" name="qual" value="" placeholder="자격증 입력"> <input type="button" class="btn btn-default btn-sm qual-add" value="추가"><br> 
          </div>
        </div>
        <!-- --- -->
        
        <!-- 외국어 -->
        <div class="row">
          <div class="col-xs-2 roomy-10">외국어</div>
          <div class="col-xs-10 roomy-10 lang">
            <input type="text" name="lang" value="" placeholder="언어 입력"> <input type="button" class="btn btn-default btn-sm lang-add" value="추가"><br> 
          </div>
        </div>
        <!-- --- -->
        
        <!-- 마감일 -->
        <div class="row roomy-10">
          <div class="col-xs-2 roomy-10">마감일</div>
          <div class="col-xs-10 roomy-10">
            <input type="date" name="date1"> ~ <input type="date" name="date2">
          </div>
        </div>
        <div class="row p-top-20">
          <div class="col-xs-12 text-center">
            <input type="button" id="search-btn" class="btn btn-primary" value="검색">&nbsp;&nbsp; <!-- 검색 버튼 -->
            <input type="reset" id="reset-btn" class="btn btn-default" value="초기화"> <!-- 초기화 버튼 -->
          </div>
        </div>
        <!-- --- -->
        
      </form>
      </div>
      <!-- ----------- -->
      
    <div class="title m-top-40">
      <div class="row">
        <div class="col-sm-3">회사명</div>
        <div class="col-sm-7">채용정보</div>
        <div class="col-sm-2">마감일</div>
      </div>
    </div>
    
      
      <!-- 검색 결과 -->
      <div id="result">
      
      
      </div>
      <!-- --- -->
      
    </div>
  </section>
  <!-- 여기까지 직접 작성 -->
</body>
</html>