<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<link href="css/member.css" rel="stylesheet">
	<title>Home</title>
</head>
<body>
<h1>
	관리자 작업용 PAGE에 오신것을 환영합니다! <br>
	다들 고생이 많으십니다..!<br>
	조금만 더 힘내서 멋지게 한번 만들어봅시다ㅎㅎㅎ
</h1>

<P>  The time on the server is ${serverTime}. </P>

<div class="clear" style="height:50px;"></div>

	<div id="buttons" style="width:250px; margin:0 auto;">
 			
 			<input type="button" class="submit" value="HOME"  onclick="location.href='index.do'">
	 		<br>
	 		<br>
	 		<input type="button" class="submit" value="Fullscreen Page"  onclick="location.href='fullscreen_page.do'">
	 		<br>
	 		<br>
	 		
	 		
       	
       		
       	
     </div>






</body>
</html>
