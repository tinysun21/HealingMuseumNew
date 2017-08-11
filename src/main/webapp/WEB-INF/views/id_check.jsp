<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 체크</title>

<style type="text/css">
body{   
  background-color:#e74c3c;
  font-family: Arial;
  color: white;
}
#wrap{     
  margin: 0px 50px;
}
h3 {
  font-family: Arial;
  font-size: 20px;
  font-weight: normal;
}
input[type=button], input[type=submit] {
  margin-left: 10px;
}
div {
clear:both;
	text-align: center;
}
.divResult{
	padding-top: 20px;
}
form div input {
	height: 30px; /* 버튼의 세로 크기를 정의합니다. */
	width: 90px; /* 버튼의 가로 크기를 정의합니다. */
	border: none !important; /* 버튼의 테두리를 정의합니다. */
	border-radius: 5px; /* border-radius를 통해 버튼을 둥그렇게 만듭니다. */
	font-size: 16px; /* 글꼴 사이즈를 조절합니다. */
	font-weight: bold;
	margin-top:20px;
	color: #882d17;
	background-color: #FFB85A;
	cursor:pointer;
	
	-webkit-transition: all 0.3s;
	-moz-transition: all 0.3s;
	transition: all 0.3s;
	
}
</style>

<script type="text/javascript">
function idok(){
  opener.formm.id.value="${id}"; 
  opener.formm.reid.value="${id}";
  self.close();
}

function reidok() {
	
	//var theForm = opener.formm;
	
	//showAlert(theForm.reid.value);
	if (document.reformm.id.value =="") {
		
		showAlert('아이디를 입력해 주세요.');
		document.reformm.id.focus();
		
	} else if (document.reformm.id.value.length < 4) {
		showAlert("아이디를 4자 이상으로 입력해주세용 ^^");
		document.reformm.id.focus();
		return;
	} else if (document.reformm.id.value.length > 20) {
		showAlert("아이디를 20자 이하로 입력해주세용 ^^");
		document.reformm.id.focus();
		return;
	} else {
		document.reformm.submit();
	}
	
}
</script>
</head>
<body>
<div id="wrap">
  <h3>ID 중복확인</h3>
  <form method=post name=reformm style="margin-right:0 " action="id_check.do" >
    User ID <input type=text name="id" value=""> 
            <input type="button" value="검색" class="submit" onclick="reidok()"><br>     
    <div class="divResult" style="margin-top: 20px">   
      <c:if test="${message == 1}">
        <script type="text/javascript">
          opener.document.formm.id.value="";
        </script>
        ${id}<br>
        이미 사용중인 아이디입니다.
      </c:if>
      
      <c:if test="${message== -1}">
        ${id}<br>
        사용 가능한 ID입니다.<br>
        <input type="button" value="사용하기" class="cancel" onclick="idok()">
      </c:if>
      
      <!--<c:if test="${message == ''}">
        검색어를 넣어 주세요
        <input type="button" value="사용" class="cancel" onclick="idok()">
      </c:if>-->
    </div>
  </form>
</div>  
</body>
</html>