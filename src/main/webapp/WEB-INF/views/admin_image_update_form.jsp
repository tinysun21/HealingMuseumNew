<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="admin_sub_menu.jsp"%>

<article class="articleWithSub imageUpload">
<h2 class="h2WithSub">이미지 수정</h2>   
<h4 style="margin:-20px 0  40px 0;">등록한 이미지를 수정할 수 있습니다.</h4>
<!-- [1] 파일을 업로드 하기 위해서는 폼태그를 post 방식으로 전송하고,
인코딩 타입을 multipart/form-data 로 지정해야 한다. -->
<form name="image_upload_frm" method="post" enctype="multipart/form-data">
<table id="upload_table">

<tr>
  <th>타이틀</th>
  <td colspan="3">
       <input type="text" name="title" size="47" maxlength="70" value="${imageDTO.title}"  style="color: #882d17;" >
  </td>
</tr>

<tr>
  <th></th>
  <td colspan="3"><input type="hidden" name="index" value="${imageDTO.index}" ></td>
</tr>

<tr>
  <th>키워드</th>
  <td colspan="3">
  <select name="keyword" style="width:100px;">
    <option value="기쁨" <c:if test="${imageDTO.keyword == '기쁨'}"> selected="selected" </c:if> >기쁨</option>
	<option value="슬픔" <c:if test="${imageDTO.keyword == '슬픔'}"> selected="selected" </c:if> >슬픔</option>
	<option value="화남" <c:if test="${imageDTO.keyword == '화남'}"> selected="selected" </c:if> >화남</option>
	<option value="즐거움" <c:if test="${imageDTO.keyword == '즐거움'}"> selected="selected" </c:if> >즐거움</option>
  </select>    

<tr>
  <th></th>
  <td colspan="3"><input type="hidden" name="path" value="${imageDTO.path}" ></td>
</tr>

<tr>
  <th>상세설명</th>
  <td colspan="3">
    <textarea name="info" rows="8" cols="70">${imageDTO.info}</textarea>
  </td>
</tr>

<tr>
  <th></th>
  <td colspan="3">
  </td>
</tr>


<tr>
  <th>이미지</th>
  <td colspan="3">
  	<div>
	<a href="${imageDTO.path}" target="_blank" title="${imageDTO.info}"><img src="${imageDTO.path}" width="200" height="140"></a>
	</div>
    <input type="file" name="image" size="47" id="flUpload"><br>
    <font color="red" size="2em">*이미지(사진) 형식은 jpg/jpeg 형식만 가능하며, 용량은 10MB 이내여야 합니다.</font>
  </td>
</tr>    
</table>
<div id="buttonGroup" style="width:730px !important; margin-top:50px;">
	<input class="adminBtn" type="button" value="이미지 수정" onClick="go_upload(2)">           
	<input class="adminBtn" type="button" value="취소" onClick="location.href='admin_image_list.do'">
</div>
</form> 
</article>
<%@ include file="footer.jsp"%>
</body>

<script>
$(document).ready(function() {
	jQuery.browser = {};
	(function () {
	    jQuery.browser.msie = false;
	    jQuery.browser.version = 0;
	    if (navigator.userAgent.match(/MSIE ([0-9]+)\./)) {
	        jQuery.browser.msie = true;
	        jQuery.browser.version = RegExp.$1;
	    }
	})();
	
	$("#flUpload").change(function () {
		var iSize = 0;

		if(jQuery.browser.msie){
			var objFSO = new ActiveXObject("Scripting.FileSystemObject");
			var sPath = $("#flUpload")[0].value;
			var objFile = objFSO.getFile(sPath);
			var iSize = objFile.size;
			iSize = iSize/ 1024;
		}else {
			iSize = ($("#flUpload")[0].files[0].size / 1024);
		}

		if(iSize > (1024*10)){
			$("#flUpload")[0].value="";
			showAlert("이미지 용량은 10MB를 넘길 수 없습니다.")
		}

	}); 
});
</script>

</html>