<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="user_sub_menu.jsp"%>

<article class="articleWithSub memberlist">
<h2 class="h2WithSub"> 게시판 </h2>
<form name="frm" method="post" id="listForm">
	<input type="hidden" id="pageIndex" name="pageIndex" value="<c:out value='${iparam.pageIndex}'/>"/>
<table>
  <tr id="memberTitle" >
  <td width="100%">
  
  	<input style="float:left;" class="listBtn" type="button" name="btn_total" value="전체보기 " onClick="location.href='board_list.do'">
  	
      제목&nbsp;&nbsp;
     <input type="text" name="subject" size="30" id="subject">
     <input class="btn" type="button" name="btn_search" value="검색" onClick="searchList(1)">
     <input style="float:right;" class="listBtn" type="button" name="btn_total" value="글쓰기 " onClick="location.href='mb_write_form.do'">
     
  </td>
  </tr>
</table>
<table class="memberListTop" style="margin-top:-30px;">
    <tr>
        <th style="width:5%;">번호</th>
        <th style="width:65%;">제목</th>   	
     	<th style="width:10%;">아이디</th>
     	<th style="width:20%;">등록일</th>
     	
    </tr>
    <c:choose>
    <c:when test="${empty boardList}">
    <tr>
      <td width="100%" colspan="10" align="center" height="23">
        등록된 글이 없습니다.
      </td>      
    </tr>
    </c:when>
	<c:otherwise>
		<fmt:parseNumber var="var1" value="${pageInfo.totalRecordCount - pageInfo.firstRecordIndex}" integerOnly="true" /> 
		<c:forEach items="${boardList}" var="boardDTO">
	    <tr> 	
	      <!-- 번호 --> 
	   	  <td lign="center"> ${boardDTO.INX}</td>
	      <!-- 제목 -->
	      <td height="23" align="center" > <a href="#" onClick="location.href='mb_update_form.do?inx=${boardDTO.INX}'" >${boardDTO.SUBJECT}	</a>
	      </td> 
	      <!-- 아이디 -->
	      <td align="center">${boardDTO.ID}</td> 
	   	  <!-- 등록일 -->
	   	  <td><fmt:formatDate value="${boardDTO.INDATE}" type="date"/></td>
	    </tr>
	  	</c:forEach>
	</c:otherwise>	
</c:choose>  
</table>
<%-- Paging --%>
<div class="paging">
	<ts:pagination paginationInfo="${pageInfo}" jsFunction="searchList" />
</div>
</form> 
</article>

<script>

//목록 조회 처리
function searchList(pageIndex) {
	$("#pageIndex").val(pageIndex);
	$("#listForm").attr("action", "<c:url value='board_list.do'/>");
	$("#listForm").submit();
}
</script>
<%@ include file="footer.jsp"%>