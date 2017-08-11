<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp"%>

<script type="text/javascript" src="js/admin.js"></script>

<c:if test="${result == 1}">
	<script type="text/javascript">
		showAlert("입장을 환영합니다.\n${memberDTO.id}님 어서오십시오.", function(){
			location.href = 'index.do';
		});
		
	</script>
</c:if>

<c:if test="${result == 0}">
	<script type="text/javascript">
		showAlert("로그인에 실패하였습니다.\n아이디가 존재하지 않습니다.", function(){
			history.go(-1);
		});
		
	</script>
</c:if>

<c:if test="${result == -1}">
	
	<script type="text/javascript">
		showAlert("로그인에 실패하였습니다.\n비밀번호가 틀렸습니다!", function(){
			history.go(-1);
		});
	</script>
</c:if>

<c:if test="${result == 10}">
	
	<script type="text/javascript">
		showAlert("어서오세요 ${memberDTO.id} 관리자님.", function(){
			location.href = 'admin_member_list.do';
		});
	</script>
</c:if>


<c:if test="${result == -2}">
	
	<script type="text/javascript">
		showAlert("로그아웃 되었습니다.\n감사합니다.", function(){
			location.href = 'index.do';
		});
	</script>
</c:if>

</body>
</html>
