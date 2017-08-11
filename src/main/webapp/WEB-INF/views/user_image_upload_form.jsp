<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="user_sub_menu.jsp"%>

<article class="articleWithSub imageUpload">
<h2 class="h2WithSub">이미지 등록</h2>   
<h4 style="margin:-20px 0  40px 0;">나만의 이미지를 등록할 수 있습니다.</h4>
<!-- [1] 파일을 업로드 하기 위해서는 폼태그를 post 방식으로 전송하고,
인코딩 타입을 multipart/form-data 로 지정해야 한다. -->
<form name="image_upload_frm" method="post" enctype="multipart/form-data">
<table id="upload_table">

<tr>
  <th>Title</th>
  <td colspan="3">
       <input type="text" name="title" size="72" maxlength="70" style="color: #882d17;" >
  </td>
</tr>

<tr>
  <th></th>
  <td colspan="3">
  </td>
</tr>

<tr>
  <th>Keyword</th>
  <td colspan="3">
    <input type="text" name="keyword" size="20" maxlength="8" style="color: #882d17;" >
    <font color="red" size="2em">*키워드는 8자 이내여야 합니다.</font>
  </td>
<tr>
  <th></th>
  <td colspan="3">
  </td>
</tr>

<tr>
  <th>Content</th>
  <td colspan="3">
    <textarea name="content" rows="8" cols="70"></textarea>
    <br>
    <font color="red" size="2em">*감상 시에 이미지와 함께 표시됩니다.(줄바꿈 시에는 &lt;br&gt; 태그를 이용해주세요.)</font>
  </td>
</tr>

<tr>
  <th></th>
  <td colspan="3">
  </td>
</tr>


<tr>
  <th>Image</th>
  <td width="343" colspan="3">
  <div>
  	<a target="_blank" id="imgPrevAtag"><img width="200" height="140" id="imgPrev" style="display:none;"></a>
  </div>
<!--  [2] 파일 업로드를 하기 위한 input 태그는 타입 속성 값을 file로 지정해야 한다.  -->
    <input type="file" name="image" size="47" id="flUpload">
    <br>
    <font color="red" size="2em">*이미지(사진) 형식은 jpg/jpeg/png 형식만 가능하며, 용량은 10MB 이내여야 합니다.</font>
  </td>
</tr>    
</table>
<div id="buttonGroup" style="width:730px !important; margin-top:50px;">
	<input class="adminBtn" type="button" value="이미지 등록" onClick="user_go_upload(1)">           
	<input class="adminBtn" type="button" value="취소"  onClick="location.href='user_image_list.do'">
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
			
			return false;
		}
		
		if ($("#flUpload")[0].files && $("#flUpload")[0].files[0]) {
        	var reader = new FileReader();
        	reader.onload = function (e) {
        		$("#imgPrev").attr('src', e.target.result);
        		$("#imgPrevAtag").attr('href', e.target.result);        		
        		$("#imgPrev").show();
            }
        	reader.readAsDataURL($("#flUpload")[0].files[0]);
       	}else{
       		$("#imgPrev").hide();
       	}

	}); 
});

function checkAndSetImgFile(obj, width, height, maxSize, name, errMsg){
    var file, img;
    img = new Image();
    if(obj.files){
        if ((file = obj.files[0])) {
            img.onload = function () {
            	if(width == 0) width = img.width;
            	if(height == 0) height = img.height;
            	if(maxSize == 0) maxSize = 99999;

//            	if(img.width != width || img.height != height || Math.round(obj.files[0].size/1024) > maxSize){
//            		alert(errMsg);
//            		obj.value = "";
//            		return false;
//            	}
//            	else{
//            		$('#' + name).val(obj.value.substring(obj.value.lastIndexOf('\\')+1));
//            	}
            };
            img.src = _URL.createObjectURL(file);
        }

        if(obj == null || obj.value === '') {
        	// 선택을 취소할 경우 값이 없다.
        	return false;
        }

        var lastFileName = obj.value.substring(obj.value.lastIndexOf('\\')+1);
        var fileType = getFileType(lastFileName);

		if(!(fileType.toLowerCase() == "png") &&
				!(fileType.toLowerCase() == "jpg") &&
				!(fileType.toLowerCase() == "jpeg") &&
				!(fileType.toLowerCase() == "gif")) {
			alert_model(errMsg, function (e) {
				return false;
			});
			return false;
		}

		var fileSize = obj.files[0].size;
		if(fileSize < 1) {
			alert_model(errMsg, function (e) {
				return false;
			});
			return false;
		}

		$('#' + name).val(lastFileName);
	}
}

/* 유저 이미지 업로드 */
function user_go_upload() {
	
	var theForm = document.image_upload_frm;

	if (theForm.title.value == '') {
		showAlert('이미지의 제목을 입력해주세요.');
		theForm.title.focus();
	} else if(!(getFileType(theForm.image.value))){
		showAlert("이미지 형식은 'jpg/jpeg/png' 확장자만 지원합니다.");
		theForm.image.value = '';
		theForm.image.focus();	
	} else {
		showConfirm("이미지를 등록하시겠습니까?", function(){
			theForm.encoding = "multipart/form-data";
			theForm.action = "user_image_upload.do";
			theForm.submit();
		})
	}
}

</script>

</html>