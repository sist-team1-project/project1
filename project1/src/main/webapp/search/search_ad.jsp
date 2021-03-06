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
                '<input type="text" name="qual" value="" placeholder="????????? ??????"> <input type="button" class="btn btn-default btn-sm qual-remove" value="??????""><br>'                    
            );                            
            $('.qual-remove').on('click', function () { 
                $(this).prev().remove ();
                $(this).next ().remove ();
                $(this).remove ();
            });
        });
        
        $('.lang-add').click (function () {                                        
            $('.lang').append (                        
                '<input type="text" name="lang" value="" placeholder="?????? ??????"> <input type="button" class="btn btn-default btn-sm lang-remove" value="??????""><br>'                    
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
        
        // ??? ????????? ??????
        $.ajax({
            type : 'get',
            url : '../search/searchad_result.do',
            data : $('#search-form').serialize(),
            success : function(result) {
                $('#result').html(result);
            }
        });
        
        // ?????? ??????
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
  <!-- ???????????? -->
  <section>
    <div class="container container-pad">
      <div class="roomy-10 m-top-40 no-select">
        <h3><i class="fa fa-search" aria-hidden="true"></i>&nbsp;<b>???????????? ????????????</b></h3>
      </div>
      
      <!--  ?????????  -->
      <div class="search-container">
      <form method="get" id="search-form">
        <div class="row">
          <div class="col-xs-2 roomy-10">?????????</div>
          <div class="col-xs-10 roomy-10">
            <input type="text" name="keyword" placeholder="?????? ???????????? ??????">
          </div>
        </div>
        <div class="row">
          <div class="col-xs-2 roomy-10">??????</div>
          <div class="col-xs-10 roomy-10">
            <select name="sido" id="sido"></select>
            <select name="gugun" id="gugun"></select>
          </div>
        </div>
        
        <div class="row">
        
          <!-- ?????? -->
          <div class="col-xs-2 col-sm-2 roomy-10">??????</div>
          <div class="col-xs-10 col-sm-4 roomy-10">
            <input type='checkbox' id="we-checkall" name="we" value="??????|??????|????????????" checked> ??????<br> 
            <input type='checkbox' class="we" name="we" value="??????"> ??????<br>
            <input type='checkbox' class="we" name="we" value="??????"> ??????<br>
            <input type='checkbox' class="we" name="we" value="????????????"> ????????????<br>
          </div>
          <!-- --- -->
          
          <!-- ?????? -->
          <div class="col-xs-2 col-sm-2 roomy-10">??????</div>
          <div class="col-xs-10 col-sm-4 roomy-10">
            <input type='checkbox' id="edu-checkall" name="edu" value="????????????|??????|2~3???|4???|??????|??????|????????????" checked> ?????? 
            <input type='checkbox' class="edu" name='edu' value="????????????"> ???????????? 
            <input type='checkbox' class="edu" name='edu' value="??????"> ??????<br>
            
            <input type='checkbox' class="edu"  name="edu" value="2~3???"> ??????(2~3???) 
            <input type='checkbox' class="edu" name='edu' value="4???"> ??????(4???)<br>
            
            <input type='checkbox' class="edu" name="edu" value="??????"> ?????? 
            <input type='checkbox' class="edu"  name='edu' value="??????"> ?????? 
            <input type='checkbox' class="edu" name='edu' value="????????????"> ????????????<br>
          </div>
        </div>
        <!-- --- -->
        
        <!-- ???????????? -->
        <div class="row">
          <div class="col-xs-2 roomy-10">????????????</div>
          <div class="col-xs-10 roomy-10">
            <input type='checkbox' id="size-checkall" name="size" value="?????????|????????????|????????????|??????|-" checked> ?????? 
            <input type='checkbox' class="size" name="size" value="????????? "> ????????? 
            <input type='checkbox' class="size" name="size" value="???????????? "> ???????????? 
            <input type='checkbox' class="size" name="size" value="????????????"> ????????????
          </div>
        </div>
        <!-- --- -->
        
        <!-- ???????????? -->
        <div class="row">
          <div class="col-xs-2 roomy-10">????????????</div>
          <div class="col-xs-10 roomy-10">
            <input type='checkbox' id="worktype-checkall" name="worktype" value="??? 5???|??? 6???|??? 4???|??? 3???|??? 2???|??? 1???|??? 7???" checked> ??????
            <input type='checkbox' class="worktype" name="worktype" value="??? 5???"> ??? 5??? 
            <input type='checkbox' class="worktype" name="worktype" value="??? 6???"> ??? 6??? 
            <input type='checkbox' class="worktype" name="worktype" value="??? 4???"> ??? 4??? 
            <input type='checkbox' class="worktype" name="worktype" value="??? 3???"> ??? 3??? 
            <input type='checkbox' class="worktype" name="worktype" value="??? 2???"> ??? 2??? 
            <input type='checkbox' class="worktype" name="worktype" value="??? 1???"> ??? 1??? 
            <input type='checkbox' class="worktype" name="worktype" value="??? 7???"> ??? 7???
          </div>
        </div>
        <!-- --- -->
        
        <!-- ???????????? -->
        <div class="row">
          <div class="col-xs-2 roomy-10">????????????</div>
          <div class="col-xs-10 roomy-10 qual">
            <input type="text" name="qual" value="" placeholder="????????? ??????"> <input type="button" class="btn btn-default btn-sm qual-add" value="??????"><br> 
          </div>
        </div>
        <!-- --- -->
        
        <!-- ????????? -->
        <div class="row">
          <div class="col-xs-2 roomy-10">?????????</div>
          <div class="col-xs-10 roomy-10 lang">
            <input type="text" name="lang" value="" placeholder="?????? ??????"> <input type="button" class="btn btn-default btn-sm lang-add" value="??????"><br> 
          </div>
        </div>
        <!-- --- -->
        
        <!-- ????????? -->
        <div class="row roomy-10">
          <div class="col-xs-2 roomy-10">?????????</div>
          <div class="col-xs-10 roomy-10">
            <input type="date" name="date1"> ~ <input type="date" name="date2">
          </div>
        </div>
        <div class="row p-top-20">
          <div class="col-xs-12 text-center">
            <input type="button" id="search-btn" class="btn btn-primary" value="??????">&nbsp;&nbsp; <!-- ?????? ?????? -->
            <input type="reset" id="reset-btn" class="btn btn-default" value="?????????"> <!-- ????????? ?????? -->
          </div>
        </div>
        <!-- --- -->
        
      </form>
      </div>
      <!-- ----------- -->
      
    <div class="title m-top-40 m-bottom-10" id="result-title">
      <div class="row">
        <div class="col-sm-3">?????????</div>
        <div class="col-sm-7">????????????</div>
        <div class="col-sm-2">?????????</div>
      </div>
    </div>
    
      
      <!-- ?????? ?????? -->
      <div id="result">
      
      
      </div>
      <!-- --- -->
      
    </div>
  </section>
  <!-- ???????????? ?????? ?????? -->
</body>
</html>