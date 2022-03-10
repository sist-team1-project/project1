<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="sist.com.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" href="../css/home.css">
  <link rel="stylesheet" href="../css/search_company.css">
  <script type="text/javascript">
  
    $(function() {
        $('#search-button').click(function() {
            let search = $('#search-input').val(); // value값을 읽어 온다 
            if (search.trim() == "")  { // 검색어 입력이 없는 경우
                $('#search-input').focus();
                return;
            }
            
            $.ajax({
                type : 'post',
                url : '../search/searchcompany_result.do',
                data : {"search" : search},
                success : function(result) {
                    $('#result').html(result);
                }
            }) 
        })
    })
  </script>
</head>
<body>
  <section>
    <div class="container container-pad">
      <div class="row roomy-20 m-top-100 text-center no-select">
        <h4><b>현장감이 느껴지는 생생한 면접 후기</b></h4>
        <h5>기업명을 검색하여</h5>
        <h5><span class="text-finence">기업정보</span>와 <span class="text-finence">면접후기</span>를 확인 해보세요!</h5>
      </div>
      <div class="search-container">
        <div class="search-bar">
          <input type="text" id="search-input" name="search" placeholder="회사 이름으로 검색하세요">
          <button type="button" id="search-button"><i class="fa fa-search"></i></button>
        </div>
      </div>
      
      
      <div id="result" >

      </div>
      
      <!-- 인기기업 9개 -->
      <div class="row roomy-20 m-top-100">
        <div class="no-select"><h4>&nbsp;&nbsp;<b>자리JOB기</b>의 추천 기업 한번 보실래요?</h4></div>
        <c:forEach var="c" items="${company }" varStatus="status">
          <div class="col-md-4 pad-5">
            <div class="unit c-container">
              <table>
                <tr>
                  <td class="c-logo">
                    <img class="c-logoimg" src="${c.c_logo }">
                  </td>
                  <td class="c-content">
                    <a href="../company/company.do?cid=${c.c_id }">
                      <div class="c-name short-line"><b>${c.c_name }</b></div>
                      <div class="short-container"><span class="short-multi-line small-font">${review[status.index] }</span></div>
                    </a>
                  </td>
                </tr>
              </table>
            </div>
          </div>
        </c:forEach>
      </div>
      <!-------------->
    </div>
  </section>
</body>
</html>