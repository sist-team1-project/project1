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
        })

        $('.we').click(function() {
            if ($('.we').is(":checked")) $('#we-checkall').prop("checked", false);
            if($('.we:checked').length==$('.we').length){
                $('#we-checkall').prop('checked',true);
                $('.we:checked').prop('checked',false);
            }
        })
        
 
        $('#search-btn').click(function() {
            let keyword = $('#keyword').val();
            let address = $('#sido').val() + " " + $('#gugun').val();
            let we = [];
            $('input[name=we]:checked').each(function() {
            	we.push($(this).val());
            })
            $.ajax({
                type : 'post',
                url : '../search/searchad_result.do',
                data : {"keyword" : keyword, "address" : address, "we" : we},
                success : function(result) {
                    $('#search-result').html(result);
                }
             })
        })
    })
  </script>
</head>
<body>
  <!-- 여기부터 -->
  <section>
    <div class="container container-pad">
      <div class="roomy-10 m-top-40 no-select">
        <h3>채용정보 상세검색</h3>
      </div>
      <div class="search-container">
      <form>
        <div class="row">
          <div class="col-xs-2">키워드</div>
          <div class="col-xs-10">
            <input type="text" id="keyword" name="keyword" placeholder="공고 제목으로 검색">
          </div>
        </div>
        <div class="row">
          <div class="col-xs-2">지역</div>
          <div class="col-xs-10">
            <select name="sido" id="sido"></select>
            <select name="gugun" id="gugun"></select>
          </div>
        </div>
        <div class="row">
          <div class="col-xs-2 col-sm-2">경력</div>
          <div class="col-xs-10 col-sm-4">
            <input type='checkbox' id="we-checkall" name="we" value="" checked> 전체<br> 
            <input type='checkbox' class="we" name="we" value="신입"> 신입<br>
            <input type='checkbox' class="we" name="we" value="경력"> 경력<br>
            <input type='checkbox' class="we" name="we" value="관계없음"> 관계없음<br>
          </div>
          
          <div class="col-xs-2 col-sm-2">학력</div>
          <div class="col-xs-10 col-sm-4">
            <input type='checkbox' name="edu" value="전체" checked> 전체 <input type='checkbox' name='학력' value="중졸이하"> 중졸이하 <input type='checkbox' name='학력' value="고졸"> 고졸<br>
            <input type='checkbox' name="edu" value="대졸(2~3년)"> 대졸(2~3년) <input type='checkbox' name='학력' value="대졸(4년)"> 대졸(4년)<br>
            <input type='checkbox' name="edu" value="석사"> 석사 <input type='checkbox' name='학력' value="박사"> 박사 <input type='checkbox' name='학력' value="학력무관"> 학력무관<br>
          </div>
        </div>
        <div class="row">
          <div class="col-xs-2">기업형태</div>
          <div class="col-xs-10">
            <input type='checkbox' name='' value="전체" checked> 전체 
            <input type='checkbox' name='기업형태' value="대기업"> 대기업 
            <input type='checkbox' name='기업형태' value="중견기업"> 중견기업 
            <input type='checkbox' name='기업형태' value="중소기업"> 중소기업
          </div>
        </div>
        <div class="row">
          <div class="col-xs-2">근무형태</div>
          <div class="col-xs-10">
            <input type='checkbox' name='근무형태' value="주 5일"> 주 5일 
            <input type='checkbox' name='근무형태' value="주 6일"> 주 6일 
            <input type='checkbox' name='근무형태' value="주 4일"> 주 4일 
            <input type='checkbox' name='근무형태' value="주 3일"> 주 3일 
            <input type='checkbox' name='근무형태' value="주 2일"> 주 2일 
            <input type='checkbox' name='근무형태' value="주 1일"> 주 1일 
            <input type='checkbox' name='근무형태' value="주 7일"> 주 7일
          </div>
        </div>
        <div class="row">
          <div class="col-xs-2">자격면허</div>
          <div class="col-xs-10">
            <input type="button" class="btn btn-default" value="자격증 선택">
          </div>
        </div>
        <div class="row">
          <div class="col-xs-2">외국어</div>
          <div class="col-xs-10">
            외국어 <input type="button" class="btn btn-default" value="외국어 선택">
          </div>
        </div>
        <div class="row">
          <div class="col-xs-2">마감일</div>
          <div class="col-xs-10">
            <input type="date" id="start" name="deadline"> ~ <input type="date" id="end" name="deadline">
          </div>
        </div>
        <div class="row p-top-20">
          <div class="col-xs-12 text-center"><input type="button" id="search-btn" class="btn btn-primary" value="제출"></div>
        </div>
      </form>
      </div>
      
      <div id="search-result">
      
      </div>
    </div>
  </section>
  <!-- 여기까지 직접 작성 -->
</body>
</html>