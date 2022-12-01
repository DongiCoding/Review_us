<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 쓰기</title>
<style>
	 #container { width: 800px; margin: 0 auto; padding-top: 30px; padding-bottom: 100px;}
	 #category { width: 15%; height: 28px; margin-bottom: 10px;}
	 select option[value=""][disabled] {
	   display: none;
	 }
	 #title { margin-bottom: 10px; width:100%; height: 30px; border-width: 0 0 1px 0; font-size: 18px;}
	 #boardMain { margin-bottom: 5px;}
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
			<option value="NOTICE" selected>NOTICE</option>
		</select>
		<input type="text" name="boardTitle" id="boardTitle" placeholder="제목"><br>
		<label for="userId" id="userId">작성자: </label><input type="text" name="userId" value="${loginUser.userId}[관리자]" readonly><br>
		<textarea name="boardMain" id="boardMain" cols="116" rows="30"></textarea>
		<div id="btns">
			<button type="button" id="btnList" onclick="location.href='/admin/getAdboardlist.do'">목록</button>
			<button type="button" id="btnDone">등록</button>
			<button type="button" id="btnCancel" onclick="history.back();">취소</button>
		</div>
	</form>

</div>
<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
<script>
console.log("#boardCate")
	$("#btnDone").click(function() {
		if(!$("#boardTitle").val()) {
			alert("제목을 입력해주세요.");
			$("#boardTitle").focus();
			return false;
		}
		if(!$("#boardMain").val()) {
			alert("내용을 입력해주세요.");
			$("#boardMain").focus();
			return false;
		}
		$.ajax({
				url: "/board/insertAdminboard.do",
				type: "post",
				data: $("#insertAdminboardForm").serialize(),
				success: function(obj) {
					console.log(obj);
					$("#insertAdminboardForm").submit();
				},
				error: function(e) {
					console.log(e);
				}
		});				
	})
</script>
</body>
</html>