<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
/*1. 공통부분 작성*/
body {
    line-height:  1.7;
    color: #ffffff;
}

a {
    text-decoration: none;
    color: inherit;
    text-decoration: none;
}

img {
    max-width: 100%;
}

hr {
    margin-top: 20px;
    color: inherit;
    height: 0;
    overflow: visible;
}

/*로그인 페이지*/
.form-wrapper {
    display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
    color: #000;
    max-width: 1200px;
    margin: 0 auto;
}

.loginForm {
    width: 410px;
    min-height: 200px;
    margin: 100px auto 100px;
    text-align: center;
    padding: 35px;
}

.loginForm input[type="text"], .loginForm input[type="password"] {
    border: 1px solid silver;
    margin-top: 20px;
    width: 100%;
}

.btn_wrap {
    margin-top: 30px;
}

/* SNS로그인 아이콘 */
.btnLogin .btn_wrap, .login_sns> a {
    display: inline-block;
    height: 42px;
    width: 42px;
    margin: 0 10px;
    font-size: 0;
    line-height: 0;
    vertical-align: top;
}

/* 아이디 저장 체크박스 */
.form_holder input[type='checkbox'] {
    float: left;
    margin-top: 10px;
}

.form_holder {
    text-align: left;
}

/* 아이디 비밀번호 찾기 */

.login-btn-wrap {
    margin-top: 30px;
    display: block;
    text-align: center;
}

/* 로그인 버튼 */
.btnLogin-button button[type="button"] {
    width: 100%;
    font-size: 16px;
    padding: 5px 25px;
}

</style>
</head>
<body>
	<jsp:include page="${pageContext.request.contextPath }/header.jsp"></jsp:include>
	
	<div class="form-wrapper">
		<form id="loginForm">
			<input type="hidden" id="joinMsg" value="${joinMsg }">
			<h3>로그인</h3>
			<div class="label-wrapper">
				<label for="userId">아이디</label>
			</div>
			<input type="text" id="userId" name="userId" required>
			<div class="label-wrapper">
				<label for="userPwd">비밀번호</label>
			</div>
      <div>
			<input type="password" id="userPwd" name="userPwd" required>
      </div>
      <div class="form_holder check">
          <input type="checkbox" id="idSaveCheck"><label for="idSaveCheck">아이디저장</label>
      </div>
			<div class="btnLogin-button">
				<button type="button" id="btnLogin">로그인</button>
			</div>
		</form>
	</div>
  <hr>
  <div class="btn_wrap login_sns" style="text-align: center;">
      <a href="#">
          <img src="/resources/images/naver_login.png" alt="네이버 로그인" width="30" height="30">
      </a>
      <a href="#" class="sns_f">
          <img src="/resources/images/fb_login.png" alt="페이스북 로그인" width="30" height="30">
      </a>
      <a href="#" class="sns_k">
          <img src="/resources/images/kakao_login.png" alt="구글 로그인" width="30" height="30">
      </a>
      <a href="#" class="sns_g">
          <img src="/resources/images/google_login.png" alt="구글 로그인" width="30" height="30">
      </a>
          
  </div>        
  <div class="login-btn-wrap">
      <a href="#">아이디 찾기</a>&emsp;
      <a href="#">비밀번호 찾기</a>
  </div> 
  <a href="/user/join.do">  <input type="button" id="register" value="회원가입"></a>
  
  
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
	
	<script>
		$(function() {
			//회원가입 성공 시 메시지 출력
			if($("#joinMsg").val() != "" && $("#joinMsg").val() != null) {
				alert($("#joinMsg").val());
			}
			
			//로그인 시 아이디나 비밀번호가 틀렸을 경우에
			//대비하여 폼 서브밋 대신 ajax로 처리
			$("#btnLogin").on("click", function() {
				$.ajax({
					url: "/user/login.do",
					type: "post",
					data: $("#loginForm").serialize(),
					success: function(obj) {
						console.log(obj);
						//id 체크
						if(obj == "idFail") {
							alert("존재하지 않는 아이디입니다.");
							$("#userId").focus();
							return;
						}
						
						//pw 체크
						if(obj =="pwFail") {
							alert("비밀번호가 틀렸습니다. 비밀번호를 확인해주세요.");
							$("#userPwd").focus();
							return;
						}
						
						//로그인 처리
						location.href="/index.jsp";
					},
					error: function(e) {
						console.log(e);
					}
				});
			});
		});

	</script>
</body>
</html>