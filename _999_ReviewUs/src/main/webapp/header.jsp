<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	ul, li {
		list-style: none;
	}
	
	a {
		text-decoration: none;
		color: black;
	}
	
	a:hover {
		color: blue;
	}
	
	nav	{
		margin-left: 15%;
	}
	
	nav ul{
		padding-left:0; 
	}
	
	nav ul li{
		float: left;
		display: inline-block;
	}
	nav * {
	  vertical-align: middle;
	}
	
	.main-div li a {
		font-size: 1.125rem;
	}
	
	.user-nav {
	/* flex */
		text-align:center;
		display:inline-block;
		justify-content: space-between;
		align-items: center;
	}
	
	.user-nav li {
		margin-left: 10px;
	}
	
	.user-nav li a {
		font-size: 0.9rem;
	}
</style>
<script src="${pageContext.request.contextPath }/js/jquery-3.6.1.min.js"></script>
</head>
<body>
	<header style="display: flex; align-items: center; background: lightgray;">
		<h1>
			<a href="/index.jsp"><img src="../images/(small)ReviewUs.png" width="200px"></a>
		</h1>
		<nav>
			<ul class="main-nav">
				<li>
					<a href="/board/getBoardList.do"><img src="../images/bars.png" width="30px">&ensp;BARS & PUBS&emsp;</a>
				</li>			
				<li>
					<a href="/board/getBoardList.do"><img src="../images/cupcake.png" width="30px">&ensp;DESSERT PLACES&emsp;</a>
				</li>			
				<li>
					<a href="/board/getBoardList.do"><img src="../images/restaurant.png" width="30px">&ensp;RESTAURANTS&emsp;</a>
				</li>			
				<li>
					<a href="/board/getBoardList.do"><img src="../images/hotel.png" width="30px">&ensp;HOTEL</a>
				</li>			
			</ul>
		</nav>
		<nav>
			<ul class="user-nav">
				<c:choose>
					<c:when test="${loginUser eq null}">
						<li>
							<a href="/user/login.do">로그인</a>
						</li>
						<li>
							<a href="/user/join.do">회원가입</a>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<a href="/user/mypage.do">${loginUser.userId }</a>
						</li>
						<li>
							<a href="/user/logout.do">로그아웃</a>
						</li>
					</c:otherwise>
				</c:choose>
			</ul>	
		</nav>
	</header>
</body>
</html>