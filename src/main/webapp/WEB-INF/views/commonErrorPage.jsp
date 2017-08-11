<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp"%>

  <article class="articleWithSub messagePage">
 	<div id="register_success">
 
	 	<!-- <img src="images/exclamation_256.png">  -->
	 	<div id="success_message">
		 	<h3>
		 		<c:choose>
		 		<c:when test="${not empty resMd.resMsg}">
		 		 	<c:out value="${resMd.resMsg}"></c:out>
		 		</c:when>
		 		<c:otherwise>
		 			시스템 처리 중 오류가 발생하였습니다.
		 			<br><br>
		 			잠시 후 다시 시도해 주시기 바랍니다. 
		 		</c:otherwise>
		 		</c:choose>
		 		<br><br><br><br>
		 		이용에 불편을 드려 죄송합니다.
		 	</h3>
	 	</div>
 	</div>
 	
	<div class="clear"></div>
    
  </article>
<%@ include file="footer.jsp"%>