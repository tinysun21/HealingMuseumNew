<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ts" uri="/ts-tags"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Healing Museum - Fullscreen Image Blur Effect with HTML5</title>
        <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, 
			width=device-width, height=device-height">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <meta name="description" content="Fullscreen Image Blur Effect with HTML5 - Blurry Image Transition for Galleries with Canvas and Fallback" />
        <meta name="keywords" content="healing, museum, photo, gallary, fullscreen" />
        <meta name="author" content="HealingMuseum" />
        <link rel="shortcut icon" href="images/favicon.ico"> 
        
        <link rel="stylesheet" type="text/css" href="css/demo.css" />
        <link rel="stylesheet" type="text/css" href="css/tik.css" />
        <link rel="stylesheet" type="text/css" href="css/admin.css" />
        <link rel="stylesheet" type="text/css" href="css/codrops.css" />
        
        
        <link href='http://fonts.googleapis.com/css?family=Miltonian|Bitter' rel='stylesheet' type='text/css' />
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
        <script type="text/javascript" src="js/modernizr.custom.21750.js"></script>
		<script type="text/javascript" src="js/member.js"></script>
		<script type="text/javascript" src="js/admin.js"></script>
		<script type="text/javascript" src="js/login_view/modernizr.custom.js"></script>
		        
        <!--  로그인 관련 -->
        <link rel="stylesheet" type="text/css" href="css/default.css" />
		<link rel="stylesheet" type="text/css" href="css/component.css" />	
		
        <noscript>
            <style>
                .bx-loading{display:none;}
            </style>
        </noscript>
    </head>
    <body>
    	
    	<!-- Common PopUp -->
    	<div class="md-modal md-effect-1" id="modal-1">
			<div class="md-content" id="common-popup">
				<!-- <h3 id="common-alert-title"></h3>  -->
				<div>
					<p id="common-alert-content"></p>
					<!-- 
					<ul>
						<li><strong>Read:</strong> modal windows will probably tell you something important so don't forget to read what they say.</li>
						<li><strong>Look:</strong> a modal window enjoys a certain kind of attention; just look at it and appreciate its presence.</li>
						<li><strong>Close:</strong> click on the button below to close the modal.</li>
					</ul>
					 -->
					<button class="md-close" id="common-alert-btn">OK</button>
				</div>
			</div>
		</div>
		<button class="md-trigger" id="common-alert-trigger" data-modal="modal-1" style="display:none;"></button>
		
    	<div class="md-modal md-effect-11" id="modal-11">
			<div class="md-content" id="common-popup">
				<!-- <h3 id="common-confirm-title"></h3>  -->
				<div>
					<p id="common-confirm-content"></p>
					<!-- 
					<ul>
						<li><strong>Read:</strong> modal windows will probably tell you something important so don't forget to read what they say.</li>
						<li><strong>Look:</strong> a modal window enjoys a certain kind of attention; just look at it and appreciate its presence.</li>
						<li><strong>Close:</strong> click on the button below to close the modal.</li>
					</ul>
					 -->
					 <div>
					 	<button class="md-close" style="width:40%;margin-right:20px;" id="common-confirm-btn">OK</button>
						<button class="md-close" style="width:40%;margin-left:20px;">Cancel</button>
					</div>
				</div>
			</div>
		</div>
		<button class="md-trigger" id="common-confirm-trigger" data-modal="modal-11" style="display:none;"></button>

		
		
    	<div class="md-modal md-effect-7" id="modal-7">
			<div class="md-content">
				<div class="md-head">
					<h3>Login</h3>
					
					<button class="md-close" id="md-close-x">X</button>
				</div>
				<form class="md-content-2" method="post" name="login_frm" id="login_frm">
					<div class="md-content-3">
						<ul>
							
							<li><strong>ID</strong><input type="text" value="" name="id" size="20"></li> 
							<li><strong>Password</strong> <input type="password" value=""  name="pw" size="20" id="pw"> </li>
						</ul>
						
					</div>
				</form>
				<div class="md-login">
					<button onclick="login_check()">login</button> <!-- <button onclick="#">아이디·비밀번호 찾기</button> -->
				</div>
			</div>
		</div>
		
		
		<div class="md-modal md-effect-16" id="modal-16">
			<div class="md-content ex1">
				<h3>Sign In</h3>
				<div class="join_view">
					<form id="join" action="join.do" method="post" name="formm">
					
					
					<ul>
						<label>아이디</label> 
							<input type="text" name="id" size="12" onblur="id_trim(this.value)" onkeydown="fn_press_han(this);"style="ime-mode: disabled;"> 
							<input type="hidden" name="reid"> 
					</ul>
							<input type="button" value="중복 확인" class="dup" onclick="idcheck()">
					
					<ul>
						<label>비밀번호</label> 
							<input	type="password" name="pw">
					</ul>
					<ul>
							<label>비밀번호 확인</label>
							<input type="password" name="pwCheck">
					</ul>
					<ul>
						<label>성별</label>
							<input type="radio" name="gender" value="M" checked>남 
							<input	type="radio" name="gender" value="W">여
							<input type="radio" name="gender" value="E" >기타
					</ul>
					
					<ul>
						<label>E-Mail</label> 
							<input type="text" name="email" onkeydown="fn_press_han(this);" style="ime-mode: disabled; width:240px;">
					</ul>
		
					<!-- <div class="clear"></div> -->
					
						
					
					</form>
					
					<div class="md-sign-in">
							<button onclick="go_save_member(1)">회원가입</button>
	<!-- <input type="button" value="회원가입" class="submit" onclick="go_save_member(1)"> --> 
						</div>
					
					<button class="md-close ex2" id="md-close-x">X</button>
				</div>
			</div>
		</div>
		
		
        <div class="container">
            <div class="header">
            	<div class="left">
            		<img src="images/home_icon.png" style="max-height:35px;" alt="home" onclick="location.href='index.do'"/>
            		<div class="lang">
						<p>
							<span style="vertical-align: middle;">LANGUAGE </span>
							<select id="locale" name="locale" style="width:150px;" onchange="chang_lang();">
								<option value="ko" <c:if test="${sessionScope.locale == 'ko'}">selected</c:if>><spring:message code='web.jsp.text.lang.kr'/></option>
								<option value="ja" <c:if test="${sessionScope.locale == 'ja'}">selected</c:if>><spring:message code='web.jsp.text.lang.ja'/></option>
								<option value="en" <c:if test="${sessionScope.locale == 'en'}">selected</c:if>><spring:message code='web.jsp.text.lang.en'/></option>
							</select>
						</p>
					</div>
            	</div>
                <div class="right">
               	<c:choose>
					<c:when test="${!empty memberDTO}">	
					
						<c:if test="${memberDTO.MTYPE==0}">
							<li style="color:#ABABAB; display:inline; margin-right: 10px;">
						    ${memberDTO.ID}님 환영합니다.
						    </li>
						    <button onclick="logout()">LOGOUT</button>  
						   	<button onclick="location.href='user_image_list.do'">My page</button>	
						</c:if>
						
						
						<c:if test="${memberDTO.MTYPE==1}">
							<li style="color:#ABABAB; display:inline; margin-right: 10px;">
						    ${memberDTO.ID}관리자님 환영합니다.
						    </li>
						    <button onclick="location.href='logout.do'">LOGOUT</button>  
						   	<button onclick="location.href='admin_member_list.do'">My page</button>	
						</c:if>
			
               		</c:when>
 
               		
				    <c:otherwise>
					   	<button id="login" class="md-trigger"  data-modal="modal-7">로그인</button>
               			<button class="md-trigger" data-modal="modal-16">회원가입</button>
				    </c:otherwise>       
				</c:choose>
                </div>
                
                
                <div class="clr"></div>
            </div>
            </div>
            
            <div class="md-overlay"></div><!-- the overlay element -->

		<script src="js/login_view/classie.js"></script>
		<script src="js/login_view/modalEffects.js"></script>
			
		<script>
			// this is important for IEs
			var polyfilter_scriptpath = '/js/login_view/';
			
			// Enter를 클릭할 경우 로그인을 실행한다.
			$('#pw').keyup(function(e) {
			    if (e.keyCode == 13) login_check();        
			});
			
			// 아이디 중복 체크
			function idcheck() {
				if (document.formm.id.value == "") {
					showAlert('아이디를 입력해 주세요.');
					document.formm.id.focus();
					return false;
				}
				if (document.formm.id.value.length < 4) {
					showAlert("아이디를 4자 이상으로 입력해주세용 ^^");
					document.formm.id.focus();
					return false;
				}
				if (document.formm.id.value.length > 20) {
					showAlert("아이디를 20자 이하로 입력해주세용 ^^");
					document.formm.id.focus();
					return false;
				}
				
				var url = "id_check.do?id=" + document.formm.id.value;
				window.open(url, "_blank_1",
					"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=500, height=250");
			}
			
			// 로그인 유효성체크
			function login_check() {
				if (document.login_frm.id.value.length == 0) {
					showAlert("아이디를 입력하라우");
					document.login_frm.id.focus();
					
					return false;
				}
				if (document.login_frm.pw.value == "") {
					showAlert("비밀번호 날래 넣으라우");
					document.login_frm.pw.focus();
					
					return false;
				} 
					
				// 모든 조건이 만족
				$.ajax({
				    type:"POST",  
				    url:'<c:url value="login.json"/>',      
				    data:$("#login_frm").serialize(),
				    success:function(res){	
				    	if(res.resCd != "0000"){
				    		showAlert(res.resMsg, function (e) {
				    			document.login_frm.pw.value = "";
								return false;
							});
				    		
				    		return;
				    	}
				    	
				    	location.href = 'index.do';
				    	
				    	/*
				    	showAlert("입장을 환영합니다.</br>${memberDTO.ID}님 어서오십시오.", function(){
			    			location.href = 'index.do';
			    		});
				    	*/
				    }
				});
				
				
				//document.login_frm.action = "login.do";
				//document.login_frm.submit();
			}
			
			// 로그인 유효성체크
			function logout() {
				showConfirm("로그아웃 하시겠습니까?", function(){
					location.href='logout.do';
				});
			}
		</script>
		<script src="js/login_view/cssParser.js"></script>
		<script src="js/login_view/css-filters-polyfill.js"></script>
  <!--헤더파일 들어가는 곳 끝 -->