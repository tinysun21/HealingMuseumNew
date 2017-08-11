<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<title>회원가입 페이지</title>

	<div id="wrap">

		<h2>회원가입</h2>
		<form id="join" action="join.do" method="post" name="formm">
			<fieldset>
				<legend>Basic Info</legend>
				<label>아이디</label> 
				<input type="text" name="id" size="12" onblur="id_trim(this.value)" onkeydown="fn_press_han(this);"
					style="ime-mode: disabled;"> 
					<input type="hidden" name="reid"> 
					<input type="button" value="중복 체크" class="dup" onclick="idcheck()"><br> 
					
					<label>비밀번호</label> 
					<input
					type="password" name="pw"><br> 
					<label>비밀번호 확인</label>
				<input type="password" name="pwCheck"><br> 
				<label>성별</label>
				<input type="radio" name="gender" value="남" checked>남 
				<input	type="radio" name="gender" value="여">여
				<input type="radio" name="gender" value="기타" >기타<br> <br>
				<label>나이</label> 
				<select name="age">
					<option value="10대">10대</option>
					<option value="20대">20대</option>
					<option value="30대">30대</option>
					<option value="40대">40대</option>
					<option value="50대">50대</option>
					<option value="60대">60대</option>
					<option value="70대 이상">70대 이상</option>
				</select> <br> <br> 
				<label>E-Mail</label> 
				
				<input type="text"
					name="email" onkeydown="fn_press_han(this);"
					style="ime-mode: disabled;"><br>
			</fieldset>

			<div class="clear"></div>
			<div id="buttons">
				<input type="button" value="회원가입" class="submit" onclick="go_save_member()"> 
					<input type="reset" value="취소" class="cancel" onclick="location.href='index.do'" >
			</div>
		</form>

	</div>
<%@ include file="footer.jsp"%>