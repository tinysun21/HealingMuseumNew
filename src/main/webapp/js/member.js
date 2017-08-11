function go_save_member() {
	
	var regex = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
	
	if (document.formm.id.value == "") {
		showAlert("아이디를 입력해주세용 ^^");
		document.formm.id.focus();
	} else if (document.formm.id.value.length < 4) {
		showAlert("아이디를 4자이상 입력해주세용 ^^");
		document.formm.id.focus();
	} else if (document.formm.id.value.length > 16) {
		showAlert("아이디를 16자이하로 입력해주세용 ^^");
		document.formm.id.focus();
	} else if (document.formm.pw.value == "") {
		showAlert("비밀번호를 입력해주세용 ^^");
		document.formm.pw.focus();
	} else if ((document.formm.pw.value != document.formm.pwCheck.value)) {
		showAlert("비밀번호가 틀렸네용 ^^");
		document.formm.pw.focus();
	} else if (document.formm.email.value == "") {
		showAlert("이메일 입력해라 이자식아");
		document.formm.email.focus();
	} else if (regex.test(document.formm.email.value) === false) {
		showAlert("잘못된 이메일 형식입니다.");
		document.formm.email.focus();
	} else {
		showConfirm("회원가입을 진행하시겠습니까?", function(){
			document.formm.action = "join.do";		
			document.formm.submit();
		});
		
	}
}




// 아이디 공백 제거
function id_trim(_id) {
	var id = _id;
	document.formm.id.value = id.replace(/\s/gi, '');

}

// 한글 입력 방지
function fn_press_han(obj) {
	// 좌우 방향키, 백스페이스, 딜리트, 탭키에 대한 예외
	if (event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 37
			|| event.keyCode == 39 || event.keyCode == 46)
		return;
	// obj.value = obj.value.replace(/[\a-zㄱ-ㅎㅏ-ㅣ가-힣]/g, '');
	obj.value = obj.value.replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣]/g, '');
}



function go_next() {
	if (document.formm.okon1[0].checked == true) {
		document.formm.action = "NonageServlet?command=join_form";
		document.formm.submit();
	} else if (document.formm.okon1[1].checked == true) {
		showAlert('����� �����ϼž߸� �մϴ�.');
	}

}
