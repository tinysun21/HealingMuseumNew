<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp"%>
      
<c:if test="${mypage_result == 2}">
<script type="text/javascript">
	showAlert("정상적으로 회원정보수정이<br>완료되었습니다.", function(){
		location.href = 'mypage_form.do';
	});	
</script>
</c:if>

<c:if test="${mypage_result == -2}">
<script type="text/javascript">
	showAlert("회원정보 수정 실패<br><br>정보수정에 문제가 발생했습니다.<br>다시한번 시도해주세요.", function(){
		history.go(-1);
	});	
</script>
</c:if>
      
<c:if test="${mypage_result == 3}">
<script type="text/javascript">
	showAlert("정상적으로 회원탈퇴가 완료되었습니다.<br><br>그동안 이용해주셔서 감사합니다.", function(){
		location.href = 'index.do';
	});	
</script>
</c:if>

<c:if test="${mypage_result == -3}">
<script type="text/javascript">
	showAlert("회원탈퇴 실패 <br>회원탈퇴에 문제가 발생했습니다.<br>다시한번 시도해주세요.", function(){
		history.go(-1);
	});	
</script>
</c:if>