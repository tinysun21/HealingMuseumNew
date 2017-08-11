<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="admin_sub_menu.jsp"%>

<article class="articleWithSub memberlist">
<h2 class="h2WithSub"> 유저 정보 </h2>
<form name="frm" method="post">
<table style="margin-top:-15px;">
  <tr id="memberTitle">
  <td width="100%">
  
  	<input style="float:left;" class="listBtn" type="button" name="btn_total" value="전체보기 " onClick="admin_go_search(1)">
  	
      아이디&nbsp;&nbsp;
     <input type="text" name="key" size="30" id="key">
     <input class="btn" type="button" name="btn_search" value="검색" onClick="admin_go_search(1)">
     
  </td>
  </tr>
</table>
<table class="memberListTop" style="margin-top:-40px;">
    <tr>
        <th style="width:20%;">가입일</th>
        <th style="width:20%;">아이디</th>   	
     	<th style="width:10%;">성별</th>
     	<th style="width:10%;">나이</th>
     	<th style="width:10%;">이메일</th>
     	<th style="width:20%;">최근접속</th>
     	<th style="width:10%;">접속횟수</th>
    </tr>
    <c:choose>
    <c:when test="${userListSize <= 0}">
    <tr>
      <td width="100%" colspan="10" align="center" height="23">
        가입한 유저가 없습니다.
      </td>      
    </tr>
    </c:when>
	<c:otherwise>
	<c:forEach items="${userList}" var="memberDTO">
    <tr>
    	
      <!-- 가입일 --> 
   	  <td><fmt:formatDate value="${memberDTO.rDate}" type="date"/></td>
      <!-- 아이디 -->
      <td height="23" align="center" >${memberDTO.id}</td> 
      <!-- 성별 -->
      <td align="center">${memberDTO.gender}</td> 
   	  <!-- 나이 -->
   	  <td align="center" >${memberDTO.age}</td>
   	  <!-- Email -->
   	  <td align="center" >
   	  	<a href="#" onClick="showAlert('${memberDTO.email}')">
    	보기     
   		</a>
   	  </td>	  
   	  <!-- 최근접속 --> 
      <td><fmt:formatDate value="${memberDTO.last_login}" type="date"/></td>
      <!-- 접속횟수 --> 
      <td align="center" >${memberDTO.login_count}</td>
    </tr>
    
    
    </c:forEach>
    <tr><td colspan="10" style="text-align: center;"> ${paging} </td></tr>
	</c:otherwise>	
</c:choose>  
</table>
</form> 
</article>
<%@ include file="footer.jsp"%>