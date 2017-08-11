<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp"%>

<c:if test="${board_result == 1}">
	<script type="text/javascript">
		showAlert("게시글이 등록 되었습니다.", function(){
			location.href = 'board_list.do';
		});
		
	</script>
</c:if>

<c:if test="${board_result == -1}">
	<script type="text/javascript">
		showAlert("게시글 등록 실패<br>죄송합니다.다시한번 등록해주세요.", function(){
			history.go(-1);
		});
	</script>
</c:if>

<c:if test="${board_result == 2}">
	<script type="text/javascript">
		showAlert("게시글이 수정 되었습니다.", function(){
			location.href = 'board_list.do';
		});
	</script>
</c:if>

<c:if test="${board_result == -2}">
	<script type="text/javascript">
		showAlert("게시글 수정 실패<br>죄송합니다.다시한번 시도해주세요.", function(){
			history.go(-1);
		});
	</script>
</c:if>

<c:if test="${board_result == 3}">
	<script type="text/javascript">
		showAlert("게시글이 삭제 되었습니다.", function(){
			location.href = 'board_list.do';
		});
	</script>
</c:if>

<c:if test="${board_result == -3}">
	<script type="text/javascript">
		showAlert("게시글 삭제 실패<br>죄송합니다.다시한번 시도해주세요.", function(){
			history.go(-1);
		});
	</script>
</c:if>
