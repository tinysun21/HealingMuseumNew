<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<meta charset="UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

  <nav id="sub_menu">

    <h3>Menu</h3>
    <div class="wrapper-demo">
    
	    <div id="dd" class="wrapper-dropdown-5" tabindex="1"><a>회원 관리</a>
	    
	    	<div class="dropdown">
	    	<div id="main_empty_bottom" style="height:20px;"></div>
	    	<a href="admin_member_list.do">일반 유저</a>
	    	</div>
	    	
		</div>
		
		<div id="dd2" class="wrapper-dropdown-5" tabindex="1"><a>컨텐츠 관리</a>
	    	    	
	    	<div class="dropdown">
	    	<div id="main_empty_bottom" style="height:20px;"></div>
	    	<a href="admin_image_list.do">이미지 리스트</a>
	    	</div>
    		
    		<div class="dropdown">
    		<a href="admin_image_upload_form.do" >이미지 등록</a>
			</div>
		</div>
		
		<div id="dd3" class="wrapper-dropdown-5" tabindex="1"><a>게시판 관리</a>
	    	    	
	    	<div class="dropdown">
	    	<div id="main_empty_bottom" style="height:20px;"></div>
	    	<a href="admin_community_list">커뮤니티 게시판</a>
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


function withdrawl(){
	
	var message = "회원탈퇴를 진행하시겠습니까?\n\n회원탈퇴시 회원정보는 물론\n등록한 쿠폰/컨텐츠 모두 삭제됩니다. ";
	
	var result = confirm(message);
	
	
	if(result==true){
		location.href="TinysunServlet?command=withdrawl";
	}else{
		;
	}
}

function deleteCoupon(){
	
	var message = "쿠폰을 삭제하시겠습니까?\n\n*남아있는 쿠폰 정보가 모두 삭제되며,\n이미 고객에게 발급된 쿠폰은 삭제되지 않습니다.";
	
	var result = confirm(message);
	
	
	if(result==true){
		location.href="TinysunServlet?command=delete_coupon";
	}else{
		;
	}
}
</script> 