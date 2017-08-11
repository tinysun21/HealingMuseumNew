<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp"%>

<c:if test="${user_image_result == 1}">
	<script type="text/javascript">
		showAlert("이미지를 수정하였습니다.", function(){
			location.href = 'user_image_list.do';
		});		
	</script>
</c:if>


<c:if test="${user_image_result == 0}">
	<script type="text/javascript">
		showAlert("이미지 수정 실패!\n다시한번 시도해주세요.", function(){
			history.go(-1);
		});
	</script>
</c:if>


<c:if test="${user_image_result == -1}">	
	<script type="text/javascript">
		showAlert("이미지 수정 실패!\n현재 로그인 상태가 아닙니다.", function(){
			location.href = 'index.do';
		});		
	</script>
</c:if>



<c:if test="${user_image_result == 2}">
	
	<script type="text/javascript">
		showAlert("이미지가 정상적으로 삭제되었습니다.", function(){
			location.href = 'user_image_list.do';
		});
	</script>
</c:if>

<c:if test="${user_image_result == -2}">
	
	<script type="text/javascript">
		showAlert("이미지 삭제에 실패하였습니다.\n다시한번 시도해주세요.", function(){
			history.go(-1);
		});
	</script>
</c:if>