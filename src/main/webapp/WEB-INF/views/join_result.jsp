<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp"%>

<script type="text/javascript" src="js/admin.js"></script>
<c:if test="${result == 1}">
	<script type="text/javascript">
		showAlert('정상적으로 회원가입이 완료되었습니다.</br>특별한 힐링의 공간 HealingMuseum 회원이 되신 것을 진심으로 환영합니다.'
				,function(){location.href = 'index.do';
		});
		
	</script>
</c:if>

<c:if test="${result == 0}">
	<script type="text/javascript">	
		showAlert('회원가입 실패</br>회원가입 도중 문제가 발생했습니다.</br>잠시 후 다시 시도해주세요.'
			,function(){location.href = 'index.do';
		});		
	</script>
</c:if>

</body>
</html>