function showAlert(content, func){
	$('#common-alert-content').html(content);
	if(func != undefined && func != null){
		$('#common-alert-btn').click(func);
	}
	$('#common-alert-trigger').trigger('click');
}
function showConfirm(content, func){
	$('#common-confirm-content').html(content);
	$('#common-confirm-btn').click(func);
	$('#common-confirm-trigger').trigger('click');
}


function admin_check()
{
  if(document.admin_login_form.admin_id.value==""){
      showAlert("아이디를 입력해 이 관리자야.");
      document.admin_login_form.admin_id.focus();
  }else if(document.admin_login_form.admin_pw.value==""){
     showAlert("비밀번호를 입력해 이 관리자야.");
     document.admin_login_form.admin_pw.focus(); 
  }else{
	  document.admin_login_form.submit();
  }
  
}



/* 관리자 이미지 업로드 */
function go_upload(number) {
	
	var theForm = document.image_upload_frm;

	if (theForm.title.value == '') {
		showAlert('제목을 입력하세요.');
		theForm.title.focus();
	} else if(number==1 && !(getFileType(theForm.image.value))){
		//showAlert("이미지 형식은 'jpg', 'jpeg', 'png' 확장자만 지원합니다.");
		showAlert("이미지 형식은 'jpg/jpeg' 확장자만 지원합니다.");
		theForm.image.value = '';
		theForm.image.focus();	
	} else if(number==2 && theForm.image.value != '' && !(getFileType(theForm.image.value))){
		//showAlert("이미지 형식은 'jpg', 'jpeg', 'png' 확장자만 지원합니다.");
		showAlert("이미지 형식은 'jpg/jpeg' 확장자만 지원합니다.");
		theForm.image.value = '';
		theForm.image.focus();	
	} else {
		
		theForm.encoding = "multipart/form-data";
		
		if(number==1){
			theForm.action = "admin_image_upload.do";
		}else if(number==2){
			theForm.action = "admin_image_update.do?index="+theForm.index.value+"&path="+theForm.path.value;
		}
		
		theForm.submit();
	}
}

//파일 확장자 확인 ◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈◈
function getFileType(filePath)
{
    var index = -1;
        index = filePath.lastIndexOf('.');
    var type = "";
    if(index != -1)
    {
        type = filePath.substring(index+1, filePath.len);
    }
    else
    {
        type = "";
    }
    
    type = type.toLowerCase();
    if(!(type == "jpg" || type == "jpeg" || type == "png")){
    	return false;
    }else{
    	return true;
    }
}


/* 관리자 리스트 검색 관련 */

function admin_go_search(number) {
	var theForm = document.frm;
	
	if(number==1)
		theForm.action =  "admin_member_list.do";
	if(number==2)
		theForm.action =  "admin_image_list.do";

	theForm.submit();
}
