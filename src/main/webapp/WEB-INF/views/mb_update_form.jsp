<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="user_sub_menu.jsp"%>

<article class="articleWithSub imageUpload">
<h2 class="h2WithSub">게시글 보기</h2>   

<!-- [1] 파일을 업로드 하기 위해서는 폼태그를 post 방식으로 전송하고,
인코딩 타입을 multipart/form-data 로 지정해야 한다. -->
<form name="mb_write_form" >
<table id="mb_write_table">

<tr>
  <th>제 목</th>
  <td colspan="1">
       <input type="text" name="subject" size="47" maxlength="70" value="${mboardDTO.SUBJECT}"  style="color: #882d17;" >
  </td>
</tr>

<tr>
  <th></th>
  <td colspan="3">
  <input type="hidden" value="${mboardDTO.INX}" name="inx">
  
  </td>
</tr>



<tr>
  <th>내용</th>
  <td colspan="3">
    <textarea name="content" rows="8" cols="70">${mboardDTO.CONTENT}</textarea>
  </td>
</tr>

<tr>
  <th></th>
  <td colspan="3">
  </td>
</tr>

 
</table>

	
	<div id="buttonGroup" style="width:730px !important; margin-top:50px;">
		
		<c:if test="${sessionScope.memberDTO.ID == mboardDTO.ID}">
			<input class="adminBtn" type="button" value="수정하기" onClick="go_board_update()">    
			<input class="adminBtn" type="button" value="삭제" onClick="go_board_delete()">      
			<input class="adminBtn" type="button" value="취소" onClick="location.href='board_list.do'">
		</c:if>
		
		<c:if test="${sessionScope.memberDTO.ID != mboardDTO.ID}">          
			<input class="adminBtn" type="button" value="리스트로" onClick="location.href='board_list.do'">
		</c:if>
		
	</div>


</form> 
</article>

<script>

function go_board_update() {
	
	var theForm = document.mb_write_form;

	if (theForm.subject.value == '') {
		showAlert('제목을 입력하세요.');
		theForm.subject.focus();
		
		return false;
	} 
	
	showConfirm("게시글을 수정하시겠습니까?", function(){
		theForm.action = "mb_update.do";
		theForm.submit();
	});

}

function go_board_delete() {
	
	var theForm = document.mb_write_form;

	showConfirm("게시글을 삭제 하시겠습니까?", function(){
		theForm.action = "mb_delete.do";
		theForm.submit();
	});

}


</script>

<%@ include file="footer.jsp"%>

