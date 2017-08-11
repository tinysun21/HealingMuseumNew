<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html class="ie ie6 no-js" lang="en"> <![endif]-->
<!--[if IE 7 ]>    <html class="ie ie7 no-js" lang="en"> <![endif]-->
<!--[if IE 8 ]>    <html class="ie ie8 no-js" lang="en"> <![endif]-->
<!--[if IE 9 ]>    <html class="ie ie9 no-js" lang="en"> <![endif]-->
<!--[if gt IE 9]><!--><html class="no-js" lang="en"><!--<![endif]-->
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <title>Fullscreen Background Image Slideshow with CSS3</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <meta name="description" content="Fullscreen Background Image Slideshow with CSS3 - A Css-only fullscreen background image slideshow" />
        <meta name="keywords" content="css3, css-only, fullscreen, background, slideshow, images, content" />
        <meta name="author" content="Codrops" />
        <link rel="shortcut icon" href="../favicon.ico"> 
        
        <link rel="stylesheet" type="text/css" href="css/style3.css" />
        
		<script type="text/javascript" src="js/modernizr.custom.86080.js"></script>
		<script type="text/javascript" src="js/admin.js"></script>
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script>
		<script src="js/jquery.keyframes.min.js"></script>
		
    </head>
    <body id="page" >		
        <ul class="cb-slideshow">
        	
        	<c:forEach items="${showList}" var="showList">
	        	<li>
	        		<c:if test="${empty showList.PATH}">
	        		<span></span>
		        	<div>
			        	<h3>등록된 이미지가 존재하지 않습니다.</h3>
	        		</div>
	        		
	        		</c:if>
	        		
	        		<c:if test="${!empty showList.PATH}">
		        		<span style="background-image: url(${showList.PATH})"></span> 
		        		<div>
				        	<h3>${showList.CONTENT}</h3>
		        		</div>
	        		</c:if>
	        		
	        	</li>
        	</c:forEach>
        	
        </ul>
        <div class="container">              		 
				<div id="menu_slide_button" class="menu_slide_button" onclick="goHomeConfirm()" style="cursor: pointer; margin:10px; position:absolute; left:5px;">
					<img src="images/home_icon_yellow_small.png" alt="home"/>
				</div>
				<div onclick="menuSlide()" style="cursor: pointer; margin:10px;  position:absolute; left:45px;">
					<img src="images/music_player_yellow_small.png" alt="music-player" />
				</div>
				<div onclick="toggleFullScreen(document.body)" style="cursor: pointer; margin:10px;  position:absolute; left:90px;">
					<img src="images/expand_yellow_small.png" alt="fullscreen-btn"/>
				</div>		
				<p class="codrops-demos" action="#" method="post" id="slideMenu">
					<audio controls="controls" autoplay="autoplay" loop="loop" preload="auto">
					    <source src="http://52.68.52.65:12010/resources/bgm/Even_Now.mp3" type="audio/mp3" />
					    <source src="http://52.68.52.65:12010/resources/bgm/Lost_Stars.mp3" type="audio/mp3" />
					    <source src="http://52.68.52.65:12010/resources/bgm/Someone_Like_You.mp3" type="audio/mp3" />
					</audio>	
				</p>
        </div>
    </body>
    
<script  type="text/javascript">

var userImageCnt = 0;
window.onload = function() {

	userImageCnt = $('.cb-slideshow li').length;
	console.log("userImageCnt="+userImageCnt);
	//var totalDuration = userImageCnt*12;
	
	nextKeyframes(0);
	
}

function nextKeyframes(n){
	$ (".cb-slideshow li").eq(n).find("span").keyframes({
	    '0%': {
	    	opacity: 0
	    },
	    '75%': {
	    	opacity: 1,
	    	transform: 'scale(1.08)'
	    },
	    '100%': {
	    	opacity: 0,
	    	transform: 'scale(1.1)'
	    }
	  }, {
		duration: 12000,
	    easing: 'ease',
	    //count: 1,
	    direction: 'alternate'
	}, function () {
		if(n < (userImageCnt-1)){
			console.log("n = " +n);
			nextKeyframes(n+1);
		}else{
			console.log("(0) n = " +n);
			nextKeyframes(0);
		}
	});
	
	$ (".cb-slideshow li").eq(n).find("div").keyframes({
		'0%': {
	    	opacity: 0,
	    	transform: 'translateY(-120%)'
	    },
	    '75%': {
	    	opacity: 1,
	    	transform: 'translateY(-20%)'
	    },
	    '100%': {
	    	opacity: 0,
	    	transform: 'translateY(30%)'
	    }
	  }, {
		duration: 12000,
	    easing: 'ease',
	    //count: 1,
	    direction: 'alternate'
	}, function () {
		if(n < (userImageCnt-1)){
			console.log("n = " +n);
			nextKeyframes(n+1);
		}else{
			console.log("(0) n = " +n);
			nextKeyframes(0);
		}
	});
}


$(function() {
	var button = document.getElementById('toggle-animation'), el = document
			.getElementById('element');

	$(".cb-slideshow").onclick = function() {
		el.classList.toggle('paused');
	}
});

function toggleFullScreen(elem) {
    // ## The below if statement seems to work better ## if ((document.fullScreenElement && document.fullScreenElement !== null) || (document.msfullscreenElement && document.msfullscreenElement !== null) || (!document.mozFullScreen && !document.webkitIsFullScreen)) {
    if ((document.fullScreenElement !== undefined && document.fullScreenElement === null) || (document.msFullscreenElement !== undefined && document.msFullscreenElement === null) || (document.mozFullScreen !== undefined && !document.mozFullScreen) || (document.webkitIsFullScreen !== undefined && !document.webkitIsFullScreen)) {
        if (elem.requestFullScreen) {
            elem.requestFullScreen();
        } else if (elem.mozRequestFullScreen) {
            elem.mozRequestFullScreen();
        } else if (elem.webkitRequestFullScreen) {
            elem.webkitRequestFullScreen(Element.ALLOW_KEYBOARD_INPUT);
        } else if (elem.msRequestFullscreen) {
            elem.msRequestFullscreen();
        }
    } else {
        if (document.cancelFullScreen) {
            document.cancelFullScreen();
        } else if (document.mozCancelFullScreen) {
            document.mozCancelFullScreen();
        } else if (document.webkitCancelFullScreen) {
            document.webkitCancelFullScreen();
        } else if (document.msExitFullscreen) {
            document.msExitFullscreen();
        }
    }
}
/*
function toggleFullScreen() {
	if ((document.fullScreenElement && document.fullScreenElement !== null)
			|| (!document.mozFullScreen && !document.webkitIsFullScreen)) {
		if (document.documentElement.requestFullScreen) {
			document.documentElement.requestFullScreen();
		} else if (document.documentElement.mozRequestFullScreen) {
			document.documentElement.mozRequestFullScreen();
		} else if (document.documentElement.webkitRequestFullScreen) {
			// alert("여기안됨");
			document.documentElement
					.webkitRequestFullScreen(Element.ALLOW_KEYBOARD_INPUT);
		}
	} else {
		if (document.cancelFullScreen) {
			document.cancelFullScreen();
		} else if (document.mozCancelFullScreen) {
			document.mozCancelFullScreen();
		} else if (document.webkitCancelFullScreen) {
			document.webkitCancelFullScreen();
		}
	}
}
*/
function menuSlide() {
	var toggle = $('#menu_slide_button').data('toggle');
	if(toggle == "Y"){
		$('#menu_slide_button').data('toggle', 'N');
		$('#slideMenu').animate({top : "-200px" }, 500);
	}else{
		$('#menu_slide_button').data('toggle', 'Y');
		$('#slideMenu').animate({top : "50px"	}, 500);
	}
	
}


function goHomeConfirm(){
	if (confirm("감상을 멈추고 내 갤러리로 이동하시겠습니까?")) {
		//location.href='index.do';
		location.href='user_image_list.do';
	}
}
</script>
</html>