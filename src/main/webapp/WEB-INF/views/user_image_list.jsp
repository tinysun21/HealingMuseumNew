<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="user_sub_menu.jsp"%>

<article class="articleWithSub memberlist">
<h2 class="h2WithSub" style="float:left; width:700px;"> My Gallary </h2> 
<input style=" float: right; margin-top: 20px;width: 170px; height: 50px; font-size: 20px;" 
	class="listBtn" type="button" name="btn_total" value="감상하기 " onClick="showSlide()">

<form name="frm" method="post" id="listForm">
	<input type="hidden" id="pageIndex" name="pageIndex" value="<c:out value='${iparam.pageIndex}'/>"/>
<table style="margin-top:-15px;">
  <tr id="memberTitle">
  <td width="100%">
  
  	<input style="float:left;" class="listBtn" type="button" name="btn_total" value="전체보기 " onClick="searchUserImageList()">
  	
      키워드&nbsp;&nbsp;
     <input type="text" name="keyword" size="30" id="keyword">
     <input class="btn" type="button" name="btn_search" value="검색" onClick="searchUserImageList()">
     <input style="float:right;" class="listBtn" type="button" name="btn_total" value="이미지등록" onClick="location.href='user_image_upload_form.do'">
     
  </td>
  </tr>
</table>
<table class="memberListTop" id="userImageListTable" style="margin-top:-40px;display: block;height: 650px;overflow-y: scroll;">
    <tr>
    	<th style="width:6%; cursor:pointer;" onClick="showCheckFlag()">전체선택</th>
    	<th style="width:5%;">No</th>
    	<th style="width:15%;">이미지</th>
        <th style="width:12%;">키워드</th>   	
     	<th style="width:30%;">타이틀</th>
     	<th style="width:16%;">등록일</th>
     	<th style="width:4%;">감상횟수</th>
     	<th style="width:12%;">삭제</th>
    </tr>
    <c:choose>
    <c:when test="${empty imageList}">
    <tr>
      <td width="100%" colspan="10" align="center" height="23">
        등록한 이미지가 없습니다.
      </td>      
    </tr>
    </c:when>
	<c:otherwise>
	
	<fmt:parseNumber var="listCnt" value="${fn:length(imageList)}" integerOnly="true" /> 
	<c:forEach items="${imageList}" var="imageDTO" varStatus="imageDTOLoop">
    <tr>
      <!-- 감상(checkbox) -->
      <td class="ac"><input type="checkbox" name="showCheck" value="${imageDTO.INX}" checked="checked"></td>
      <!-- No --> 
      <td class="ac"><c:out value="${listCnt}"/><fmt:parseNumber var="listCnt" value="${listCnt - 1}" integerOnly="true" /></td>
      <!-- 이미지(썸네일) --> 
   	  <td><a href="${imageDTO.PATH}" target="_blank" title="${imageDTO.INFO}"><img src="${imageDTO.PATH}" height="50"></a></td>
      <!-- 키워드 -->
      <td align="center">${imageDTO.KEYWORD}</td> 
   	  <!-- 타이틀 -->
   	  <td align="center" ><a href="user_image_update_form.do?inx=${imageDTO.INX}" style="text-decoration: none; color:red;"> ${imageDTO.TITLE} </a></td>
   	  <!-- 등록일 --> 
      <td><fmt:formatDate value="${imageDTO.INDATE}" type="date"/></td>
      <!-- 본 횟수 --> 
      <td align="center" >${imageDTO.SHOW_COUNT}</td>
      <td><a href="#" onclick="deleteUserImage(${imageDTO.INX})"  style="text-decoration: none; color:red;"> 삭제하기 </a></td>
    </tr>
    </c:forEach>
	</c:otherwise>	
</c:choose>  
</table>
</form> 
</article>

<script>
/* 유저 이미지 검색 
function searchUserImage() {
	var theForm = document.frm;
	theForm.action =  "user_image_list.do";
	theForm.submit();
}
*/
//목록 조회 처리
function searchList(pageIndex) {
	$("#pageIndex").val(pageIndex);
	$("#listForm").attr("action", "<c:url value='user_image_list.do'/>");
	$("#listForm").submit();
}
function searchUserImageList() {
	$("#listForm").attr("action", "<c:url value='user_image_list.do'/>");
	$("#listForm").submit();
}

/* 유저 이미지 삭제 */
function deleteUserImage(_inx){
	showConfirm("해당 이미지를 삭제하시겠습니까?", function(){
		location.href="user_image_delete.do?inx="+_inx;
	});
}

function showCheckFlag(){
	var checkedStatus = true;
	var checkBoxCnt = $("input[type='checkbox'][name='showCheck']").length;
	var checkedCnt = $("input[type='checkbox'][name='showCheck']:checked").length;
	if(checkBoxCnt == checkedCnt){
		checkedStatus = false;
	}
	$('#userImageListTable tr').find('td:first :checkbox').each(function() {
	  $(this).prop('checked', checkedStatus);
	});
}
</script>
<%@ include file="footer.jsp"%>