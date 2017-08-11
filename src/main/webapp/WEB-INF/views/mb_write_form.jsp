<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="user_sub_menu.jsp"%>

<article class="articleWithSub imageUpload">
<h2 class="h2WithSub">게시글 등록</h2>   

<!-- [1] 파일을 업로드 하기 위해서는 폼태그를 post 방식으로 전송하고,
인코딩 타입을 multipart/form-data 로 지정해야 한다. -->
<form name="mb_write_form"  >
<table id="mb_write_table" style="margin-top:70px !important">

<tr>
  <th>제 목</th>
  <td colspan="1">
       <input type="text" name="subject" size="47" maxlength="70" value=""  style="color: #882d17;" >
  </td>
</tr>

<tr>
  <th></th>
  <td colspan="3">
  </td>
</tr>



<tr>
  <th>문의글</th>
  <td colspan="3">
    <textarea name="content" rows="8" cols="70"></textarea>
  </td>
</tr>

<tr>
  <th></th>
  <td colspan="3">
  </td>
</tr>

 
</table>
<div id="buttonGroup" style="width:730px !important; margin-top:50px;">
	<input class="adminBtn" type="button" value="게시글 등록" onClick="go_board_write()">           
	<input class="adminBtn" type="button" value="취소" onClick="location.href='board_list.do'">
</div>
</form> 
</article>

<script>
function go_board_write() {

	var theForm = document.mb_write_form;

	if (theForm.subject.value == '') {
		showAlert('제목을 입력하세요.');
		theForm.subject.focus();
		
		return false;
	} 
	
	showConfirm("게시글을 등록하시겠습니까?", function(){
		theForm.action = "mb_write.do";
		theForm.submit();
	});
	
}

</script>
<%@ include file="footer.jsp"%>

