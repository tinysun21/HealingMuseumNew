<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${admin_image_result == 1}">
	<script type="text/javascript">
		showAlert("이미지 수정 성공!");
		location.href = 'admin_image_list.do';
	</script>
</c:if>


<c:if test="${admin_image_result == 0}">
	
	<script type="text/javascript">
		showAlert("이미지 수정 실패!\n다시한번 시도해주세요.");
		history.go(-1);
	</script>
</c:if>