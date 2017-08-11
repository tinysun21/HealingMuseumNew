<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp"%>
<%@ include file="admin_sub_menu.jsp"%>

  <article class="articleWithSub">
 	<div id="result_div">
 		
 		<c:if test="${image_upload_result==1}">
	 	<img src="images/check_256.png">
	 	</c:if>
	 	<c:if test="${image_upload_result==0}">
	 	<img src="images/exclamation_256.png">
	 	</c:if>
	 	
	 	<div id="result_message">
	 	
	 		<c:if test="${image_upload_result==1}">
	 		<h3>이미지가 정상적으로 등록되었습니다.<br><br>
			등록한 이미지는 '컨텐츠 관리'의 '이미지 리스트'에서 조회가 가능합니다.<br><br></h3>
			</c:if>
			
			<c:if test="${image_upload_result==0}">
	 		<h3>이미지 등록에 실패하였습니다.<br>
			다시 한번 등록을 시도해주세요<br><br>
			만약 계속해서 등록에 실패한다면<br>최연석 이자식한테 문의주시기 바랍니다.<br><br></h3>
			</c:if>
			
			
	 	</div>
 	</div>

	
  <div style="text-align:center;">
  	<input type="button" value="확인" onclick="location.href='admin_image_list.do'" class="adminBtn">
  </div>

  <div class="clear"></div>

  </article>
<%@ include file="footer.jsp" %>  