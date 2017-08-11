<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="admin_sub_menu.jsp"%>

<article class="articleWithSub memberlist">
<h2 class="h2WithSub"> 이미지 리스트 </h2>
<form name="frm" method="post">
<table style="margin-top:-15px;">
  <tr id="memberTitle">
  <td width="100%">
  
  	<input style="float:left;" class="listBtn" type="button" name="btn_total" value="전체보기 " onClick="admin_go_search(2)">
  	
      키워드&nbsp;&nbsp;
     <input type="text" name="key" size="30" id="key">
     <input class="btn" type="button" name="btn_search" value="검색" onClick="admin_go_search(2)">
     
  </td>
  </tr>
</table>
<table class="memberListTop" style="margin-top:-40px;">
    <tr>
    	<th style="width:20%;">이미지</th>
        <th style="width:5%;">번호</th>
        <th style="width:10%;">키워드</th>   	
     	<th style="width:25%;">타이틀</th>
     	<th style="width:20%;">등록일</th>
     	<th style="width:10%;">본 횟수</th>
     	<th style="width:10%;">상세</th>
    </tr>
    <c:choose>
    <c:when test="${imageListSize <= 0}">
    <tr>
      <td width="100%" colspan="10" align="center" height="23">
        등록한 이미지가 없습니다.
      </td>      
    </tr>
    </c:when>
	<c:otherwise>
	<c:forEach items="${imageList}" var="imageDTO">
    <tr>
    	
      <!-- 이미지(썸네일) --> 
   	  <td><a href="${imageDTO.path}" target="_blank" title="${imageDTO.info}"><img src="${imageDTO.path}" width="100" height="70"></a></td>
      <!-- 번호 -->
      <td height="23" align="center" >${imageDTO.index}</td> 
      <!-- 키워드 -->
      <td align="center">${imageDTO.keyword}</td> 
   	  <!-- 타이틀 -->
   	  <td align="center" >${imageDTO.title}</td>
   	  <!-- 등록일 --> 
      <td><fmt:formatDate value="${imageDTO.indate}" type="date"/></td>
      <!-- 본 횟수 --> 
      <td align="center" >${imageDTO.show_count}</td>
      <!-- 상세(수정) -->
      <td> <a href="admin_image_update_form.do?index=${imageDTO.index}" style="text-decoration: none; color:red;"> 수정 </a></td>
    </tr>
    
    
    </c:forEach>
    <tr><td colspan="10" style="text-align: center;"> ${paging} </td></tr>
	</c:otherwise>	
</c:choose>  
</table>
</form> 
</article>
<%@ include file="footer.jsp"%>