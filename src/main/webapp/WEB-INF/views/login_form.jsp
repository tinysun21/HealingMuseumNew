<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원관리</title>
<script type="text/javascript" src="js/member.js"></script>
</head>
<body>

	<h2>로그인</h2>
	<form action="login.do" method="post" name="login_frm">
		<table>
			<tr>
				<td>ID</td>
				<td><input type="text" name="id" value=""></td>
			</tr>

			<tr>
				<td>Password</td>
				<td><input type="password" name="pw"></td>
			</tr>

			<tr>
				<td colspan="2" align="center">
				<input type="button" value="로그인" onclick="login_check()">&nbsp;&nbsp; 
				<input type="reset" value="취소">&nbsp;&nbsp;
				<td colspan="2" align="center">
				<input type="button" value="회원가입"
					onclick="location.href='join_form.do'">
				</td>
			</tr>
			
			<tr> <td colspan="2"> ${message}</td></tr>
		</table>
	</form>
</body>
</html>