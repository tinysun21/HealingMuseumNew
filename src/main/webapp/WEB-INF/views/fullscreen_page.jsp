<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
		<title>Fullscreen Layout with Page Transitions</title>
		<meta name="description" content="Fullscreen Layout with Page Transitions" />
		<meta name="keywords" content="fullscreen layout, boxes, responsive, page transitions, css transitions, jquery, portfolio, template" />
		<meta name="author" content="Codrops" />
		<link rel="shortcut icon" href="../favicon.ico"> 
		<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet" />
		
		
		<link href="css/fullscreen_page.css" rel="stylesheet">
		<link href="css/style3.css" rel="stylesheet">
		<script type="text/javascript" src="js/boxlayout.js"></script>
		<script type="text/javascript" src="js/modernizr.custom.js"></script>
		<script type="text/javascript" src="js/modernizr.custom.86080.js"></script>
	</head>
	<body>
	
		<div class="container">	
			<div id="bl-main" class="bl-main">
			
				<section>
					<div class="bl-box" onclick="location.href='fullscreen_slide.do?keyword=1'">
						<h2 class="bl-icon bl-icon-about">happy</h2>
					</div>
					<!-- <i class="fa fa-car"></i> -->
					<span class="bl-icon bl-icon-close"></span>
				</section>
				
				
				<section id="bl-work-section">
					<div class="bl-box" onclick="location.href='fullscreen_slide.do?keyword=2'">
						<h2 class="bl-icon bl-icon-works">sad</h2>
					</div>
					<span class="bl-icon bl-icon-close"></span>
				</section>
				
				
				<section>
					<div class="bl-box" onclick="location.href='fullscreen_slide.do?keyword=3'">
						<h2 class="bl-icon bl-icon-blog">angry</h2>
					</div>
					<span class="bl-icon bl-icon-close"></span>
				</section>
				
				
				<section>
					<div class="bl-box" onclick="location.href='fullscreen_slide.do?keyword=4'" >
						<h2 class="bl-icon bl-icon-contact">joyful</h2>
					</div>
					<span class="bl-icon bl-icon-close"></span>
				</section>
				
				
			</div>
		</div><!-- /container -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
		<script src="js/boxlayout.js"></script>
		<script>
			$(function() {
				Boxlayout.init();
			});
		</script>
	</body>
</html>