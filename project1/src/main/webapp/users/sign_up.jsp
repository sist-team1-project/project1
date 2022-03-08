<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sign up</title>
 <link rel="stylesheet" href="../css/sign_up.css">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
 <div id="formContent">
    <div class="row">
    <div class="col-md-8">
      <section>      
        <h1 class="entry-title"><span>회원가입</span> </h1>
        <hr>
            <form method="post" action="../login/sign_up_ok.do" name="sign_up_frm" id="sign_up_frm">
        <div class="form-group">
          <label class="control-label col-sm-3">아이디 <span class="text-danger">*</span></label>
          <div class="col-md-8 col-sm-9">
              <div class="input-group">
              <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
              <div class="col-sm-12">
              <input type="email" class="form-control" name="id" id="id" placeholder="아이디를 입력하세요." value=""></div>
              <div class="col-sm-">
              <input type=button id="checkBtn" value="중복체크" class="btn btn-sm btn-primary"></div>
              
            </div> 
           <small> 아이디는 6~12자로 되어야 합니다. </small> </div>
        </div>
        
        <div class="form-group">
          <label class="control-label col-sm-3">비밀번호 <span class="text-danger">*</span></label>
          <div class="col-md-5 col-sm-8">
            <div class="input-group">
              <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
              <input type="password" class="form-control" name="password" id="password" placeholder="비밀번호를 입력하세요." value="">
           </div>
          <small>비밀번호는 8~20자로 되어야 합니다.</small>   
          </div>
        </div>
        <div class="form-group">
          <label class="control-label col-sm-3">비밀번호 확인 <span class="text-danger">*</span></label>
          <div class="col-md-5 col-sm-8">
            <div class="input-group">
              <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
              <input type="password" class="form-control" name="cpassword" id="cpassword" placeholder="비밀번호를 입력하세요." value="">
            </div>  
          </div>
        </div>
        <div class="form-group">
          <label class="control-label col-sm-3">이름 <span class="text-danger">*</span></label>
          <div class="col-md-8 col-sm-9">
            <input type="text" class="form-control" name="mem_name" id="mem_name" placeholder="이름을 입력하세요." value="">
          </div>
        </div>
        <div class="form-group">
          <label class="control-label col-sm-3">생년월일 <span class="text-danger">*</span></label>
          <div class="col-xs-8">
            <div class="form-inline">
              <div class="form-group">
                <select name="dd" class="form-control">
                  <option value="">일</option>
                  <option value="1" >1 </option><option value="2" >2 </option><option value="3" >3 </option><option value="4" >4 </option><option value="5" >5 </option><option value="6" >6 </option><option value="7" >7 </option><option value="8" >8 </option><option value="9" >9 </option><option value="10" >10 </option><option value="11" >11 </option><option value="12" >12 </option><option value="13" >13 </option><option value="14" >14 </option><option value="15" >15 </option><option value="16" >16 </option><option value="17" >17 </option><option value="18" >18 </option><option value="19" >19 </option><option value="20" >20 </option><option value="21" >21 </option><option value="22" >22 </option><option value="23" >23 </option><option value="24" >24 </option><option value="25" >25 </option><option value="26" >26 </option><option value="27" >27 </option><option value="28" >28 </option><option value="29" >29 </option><option value="30" >30 </option><option value="31" >31 </option>                </select>
              </div>
              <div class="form-group">
                <select name="mm" class="form-control">
                  <option value="">월</option>
                  <option value="1">1월</option><option value="2">2월</option><option value="3">3월</option><option value="4">4월</option><option value="5">5월</option><option value="6">6월</option><option value="7">7월</option><option value="8">8월</option><option value="9">9월</option><option value="10">10월</option><option value="11">11월</option><option value="12">12월</option>                </select>
              </div>
              <div class="form-group" >
                <select name="yyyy" class="form-control">
                  <option value="0">년도</option>
                  <option value="1955" >1955 </option><option value="1956" >1956 </option><option value="1957" >1957 </option><option value="1958" >1958 </option><option value="1959" >1959 </option><option value="1960" >1960 </option><option value="1961" >1961 </option><option value="1962" >1962 </option><option value="1963" >1963 </option><option value="1964" >1964 </option><option value="1965" >1965 </option><option value="1966" >1966 </option><option value="1967" >1967 </option><option value="1968" >1968 </option><option value="1969" >1969 </option><option value="1970" >1970 </option><option value="1971" >1971 </option><option value="1972" >1972 </option><option value="1973" >1973 </option><option value="1974" >1974 </option><option value="1975" >1975 </option><option value="1976" >1976 </option><option value="1977" >1977 </option><option value="1978" >1978 </option><option value="1979" >1979 </option><option value="1980" >1980 </option><option value="1981" >1981 </option><option value="1982" >1982 </option><option value="1983" >1983 </option><option value="1984" >1984 </option><option value="1985" >1985 </option><option value="1986" >1986 </option><option value="1987" >1987 </option><option value="1988" >1988 </option><option value="1989" >1989 </option><option value="1990" >1990 </option><option value="1991" >1991 </option><option value="1992" >1992 </option><option value="1993" >1993 </option><option value="1994" >1994 </option><option value="1995" >1995 </option><option value="1996" >1996 </option><option value="1997" >1997 </option><option value="1998" >1998 </option><option value="1999" >1999 </option><option value="2000" >2000 </option><option value="2001" >2001 </option><option value="2002" >2002 </option><option value="2003" >2003 </option><option value="2004" >2004 </option><option value="2005" >2005 </option><option value="2006" >2006 </option>                </select>
              </div>
            </div>
          </div>
        </div>
        <div class="form-group">
          <label class="control-label col-sm-3">성별 <span class="text-danger">*</span></label>
          <div class="col-md-8 col-sm-9">
            <label>
            <input name="gender" type="radio" value="Male" checked>
            남 </label>
               
            <label>
            <input name="gender" type="radio" value="Female" >
            여 </label>
          </div>
        </div>
        <div class="form-group">
          <label class="control-label col-sm-3">이메일 주소 <span class="text-danger">*</span></label>
          <div class="col-md-5 col-sm-8">
            <div class="input-group">
              <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
            <input type="text" class="form-control" name="contactnum" id="contactnum" placeholder="이메일 주소를 입력하세요." value="">
            </div>
          </div>
        </div>
        <div class="form-group">
          <label class="control-label col-sm-3">주소</label>
          <div class="col-md-5 col-sm-8">
            <input type="text" class="form-control" name="contactnum2" id="contactnum2" placeholder="주소를 입력하세요." value="">
          </div>
        </div>
        <div class="form-group">
          <label class="control-label col-sm-3">프로필사진 <br>
          <small>(optional)</small></label>
          <div class="col-md-5 col-sm-8">
            <div class="input-group"> <span class="input-group-addon" id="file_upload"><i class="glyphicon glyphicon-picture"></i></span>
              <input type="file" name="file_nm" id="file_nm" class="form-control upload" placeholder="" aria-describedby="file_upload">
            </div>
          </div>
        </div>
        <div class="form-group">
          <label class="control-label col-sm-3"> 비밀번호 찾기 질문/답변</label>
          <div class="col-xs-8">
            <div class="form-inline">
              <div class="form-group">
                <select name="dd" class="form-control">
                  <option value="">선택</option>
                  <option value="1" >기억에 남는 추억의 장소는? </option><option value="2" >자신의 인생 좌우명은? </option><option value="3" >자신의 보물 제1호는? </option><option value="4" >가장 기억에 남는 선생님 성함은? </option><option value="5" >타인이 모르는 자신만의 신체비밀이 있다면? </option><option value="6" >추억하고 싶은 날짜가 있다면? </option><option value="7" >받았던 선물 중 기억에 남는 독특한 선물은? </option><option value="8" >유년시절 가장 생각나는 친구 이름은? </option><option value="9" >인상 깊게 읽은 책 이름은? </option><option value="10" >읽은 책 중에서 좋아하는 구절이 있다면? </option><option value="11" >자신이 두번째로 존경하는 인물은? </option><option value="12" >친구들에게 공개하지 않은 어릴 적 별명이 있다면? </option><option value="13" >초등학교 때 기억에 남는 짝꿍 이름은? </option><option value="14" >다시 태어나면 되고 싶은 것은? </option><option value="15" >내가 좋아하는 캐릭터는? </option></select>
                    <input type="text" class="form-control" name="contactnum3" id="contactnum3" placeholder="답변을 입력하세요." value=""> 
         </div>
            <input name="Submit" type="submit" value="가입" class="btn btn-primary">
          </div>
        </div>
      </form>
    </div>
</div>
</div>
</body>
</html>