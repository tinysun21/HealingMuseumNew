<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<meta charset="UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

  <nav id="sub_menu">

    <h3>Menu</h3>
    <div class="wrapper-demo">
    
	    <div id="dd" class="wrapper-dropdown-5" tabindex="1"><a>내 정보</a>
	    
	    	<div class="dropdown">
	    	<div id="main_empty_bottom" style="height:20px;"></div>
	    	<a href="mypage_form.do">내 정보 수정</a>
	    	</div>
	    	
		</div>
		
		<div id="dd2" class="wrapper-dropdown-5" tabindex="1"><a>나만의 갤러리</a>
	    	    	
	    	<div class="dropdown">
	    	<div id="main_empty_bottom" style="height:20px;"></div>
	    	<a href="user_image_upload_form.do">이미지 등록</a>
	    	</div>
    		
    		<div class="dropdown">
    		<a href="user_image_list.do" >이미지 리스트</a>
			</div>
			
			<div class="dropdown">
    		<a href="#" onclick="showSlide()">감상하기</a>
			</div>
			
		</div>
		
		<div id="dd3" class="wrapper-dropdown-5" tabindex="1"><a>커뮤니티</a>
	    	    	
	    	<div class="dropdown">
	    	<div id="main_empty_bottom" style="height:20px;"></div>
	    	<a href="board_list.do">게시판</a>
	    	</div>

		</div>
		
		
		
  </nav>
  
<script type="text/javascript">
function DropDown(el) {
	this.dd = el;
	this.dd2 = el;
	this.dd3 = el;
	this.initEvents();
}
	DropDown.prototype = {
		initEvents : function() {
			var obj = this;

			obj.dd.on('click', function(event){
				$(this).toggleClass('active');
				event.stopPropagation();
			});	
			obj.dd2.on('click', function(event){
				$(this).toggleClass('active');
				event.stopPropagation();
			});	
			obj.dd3.on('click', function(event){
				$(this).toggleClass('active');
				event.stopPropagation();
			});	
		}
	}

	$(function() {

		var dd = new DropDown( $('#dd') );
		var dd = new DropDown( $('#dd2') );
		var dd = new DropDown( $('#dd3') );
		
		$(document).click(function() {
			// all dropdowns
			$('.wrapper-dropdown-5').removeClass('active');
		});

	});

	
function showSlide(){
	showConfirm("선택한 사진들을 감상하시겠습니까?", function(){
		$("#listForm").attr("action", "<c:url value='user_image_slide.do'/>");
		$("#listForm").submit();
		
		/*
		var $form = $('<form></form>');
	    $form.attr('action', 'user_image_slide.do');
	   	$form.attr('method', 'post');
	    //$form.attr('target', '_result_ifr');
	    $form.appendTo('body');
	    if($('#keyword').length){
	    	var keyword = $('<input type="hidden" value="' + $('#keyword').val() + '" name="keyword">');
	    	$form.append(keyword);
	    }    
	    $form.submit();
	    */
	});
}
</script> 