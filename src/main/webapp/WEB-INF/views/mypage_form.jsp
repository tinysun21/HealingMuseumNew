<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="user_sub_menu.jsp"%>

<article class="articleWithSub imageUpload">
		<h2 class="h2WithSub">내 정보 관리</h2>
		<h4 style="margin:-20px 0  40px 0;">내 정보를 수정하거나 회원 탈퇴를 할 수 있습니다.</h4>
		
		<form class="mypageForm" id="update" action="mypage_form_update.do" method="post" name="update_formm">
			
			<fieldset>
				<legend>내 정보</legend>
				<label>아이디</label>
				<input type="text" name="id" size="12" readonly="readonly"
					onblur="id_trim(this.value)" onkeydown="fn_press_han(this);"
					style="ime-mode: disabled;" value="${memberDTO.ID}" disabled> <br>
										
				<label>비밀번호</label>
				<input	type="password" name="pw"><br> 
				<label>비밀번호 확인</label>
				<input type="password" name="pwCheck"><br> 
				
				<label>성별</label>
					<input type="radio" name="gender" value="M" <c:if test="${memberDTO.GENDER == 'M'}"> checked="checked" </c:if>>남성
					<input type="radio" name="gender" value="W"  <c:if test="${memberDTO.GENDER == 'W'}"> checked="checked" </c:if>>여성
					<input type="radio" name="gender" value="E" <c:if test="${memberDTO.GENDER == 'E'}"> checked="checked" </c:if>>기타<br><br>
			
				
				<label>E-Mail</label> 
				<input type="text"	name="email" onkeydown="fn_press_han(this);"
					style="ime-mode: disabled;" value="${memberDTO.EMAIL}"><br>
			</fieldset>

			<div class="clear"></div>
			<div id="buttonGroup" style="width:730px !important; margin-top:50px;">
				<input type="button" value="정보 수정" class="adminBtn" onclick="go_save_update()"> 
				<input type="button" value="탈퇴" class="adminBtn"  onclick="go_delete_member();"> 	
				
			</div>
		</form>
</article>

<script>
function go_save_update() {
	
	var email = 'pondol-_if.79@naver.com';
	var regex = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
	
	if (document.update_formm.pw.value == "") {
		showAlert("비밀번호를 입력해주세용 ^^");
		document.update_formm.pw.focus();
	} else if ((document.update_formm.pw.value != document.update_formm.pwCheck.value)) {
		showAlert("비밀번호가 틀렸네용 ^^");
		document.update_formm.pw.focus();
	} else if (document.update_formm.email.value == "") {
		showAlert("이메일 입력해라 이자식아");
		document.update_formm.email.focus();
	} else if (regex.test(document.update_formm.email.value) === false) {
		showAlert("잘못된 이메일 형식입니다.");
		document.update_formm.email.focus();
	} else {	
		showConfirm("내 정보를 수정하시겠습니까?", function(){
			document.update_formm.id.disabled = false;
			document.update_formm.action = "mypage_form_update.do";
			document.update_formm.submit();
		});
	}
}

function go_delete_member() {
	showConfirm("정말로 탈퇴 하시겠습니까?<br><br>※등록한 이미지도 모두 삭제됩니다.", function(){
		document.update_formm.action = "delete_member.do";
		document.update_formm.submit();
	});
}
</script>

<%@ include file="footer.jsp"%>