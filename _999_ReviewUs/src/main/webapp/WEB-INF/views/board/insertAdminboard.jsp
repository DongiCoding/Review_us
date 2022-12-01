<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 쓰기</title>
<style>
	 #container { width: 800px; margin: 0 auto;}
	 #category { width: 15%; height: 28px; margin-bottom: 10px;}
	 select option[value=""][disabled] {
	   display: none;
	 }
	 #title { margin-bottom: 10px; width:90%; height: 30px; border-width: 0 0 1px 0; font-size: 18px;}
	 #content { margin-bottom: 5px;}
	 #btns { margin: 10px;}
	 #btns>button { width: 80px; height: 30px; font-size: 18px;}
	 #btnList { float: left;}
     #btnDone { float: right;}
     #btnCancel { float: right; margin-right: 10px;}
 	
</style>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath }/header.jsp"></jsp:include>
<div id="container">
	<h3>NOTICE 쓰기</h3>
	<form name="insertAdminForm" id="insertAdminboardForm" action="/board/insertAdminboard.do" method="post">
		<input type="hidden" id="notadminMsg" value="${notadminMsg }">
		<select name="boardCate" id="boardCate" required>
			<option value="NOTICE" disabled selected>NOTICE</option>
		</select>
		<input type="text" name="boardTitle" id="boardTitle" placeholder="제목"><br>
		<label for="userId" id="userId">작성자: </label><input type="text" name="userId" value="${loginUser.userId}[관리자]" readonly><br>
		<textarea name="boardMain" id="boardMain" cols="116" rows="30"></textarea>
	</form>
	<div id="btns">
		<button type="button" id="btnList" onclick="location.href='/board/getAdminboardList.do'">목록</button>
		<button type="button" id="btnDone" onclick="location.href='/board/insertAdminboard.do'">등록</button>
		<button type="button" id="btnCancel" onclick="history.back();">취소</button>
	</div>

</div>
<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
</body>
</html>