<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    #container { width:1000px; margin:0 auto; }

    /* nav */
    #nav { 
      text-align:right; color:black;
    }
    #nav li { display:inline-block; margin:0 10px;}
    #nav a { text-decoration:none; }
    #nav a:hover { font-weight:bold; text-decoration:underline;}


    /* header */
    header { margin:20px auto; text-align:left; margin-left:70px;}
    #T_img { width:530px; height:100px;}

	/* body */
	#body { width:100%;}

    /* main_left bar */
    #main_left { 
      width:25%;  margin-left:50px;
      float:left; 
 
    } 
    .ml { 
      width:93%; height:122px; padding:10px;
      font-size:1.5em; font-weight:bold;  border:1px solid black;
      line-height:150px; text-align:center;
    }
    
    #main_left a { text-decoration:none; color:black; text-shadow:3px -3px #E0E0E0;}
    #main_left a:hover { color:red;}

    /* 별점 */
    #myform { margin:-25px 0 0 50px;}

    #myform fieldset{
    display: inline-block;
    direction: rtl;
    border:0;
    }
    #myform fieldset legend{
        text-align: right;
    }
    #myform input[type=radio]{
        display: none;
    }
    #myform label{
        font-size: 3em;
        color: transparent;
        text-shadow: 0 0 0 #f0f0f0;
    }
    #myform label:hover{
        text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
    }
    #myform label:hover ~ label{
        text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
    }
    #myform input[type=radio]:checked ~ label{
        text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
    }
	
	/* Main right */
	#main_r { width:60%; float:right; margin-left:5px;}
	
    /* 좋아요 */
    .like  { width:25px; text-align:right; margin-left:180px; }



    .pagination {
		list-style: none;
		width: 70%;
		display: inline-block;
	}
	
	.pagination_button {
		float: left;
		margin-left: 5px;
	}
	
	/* footer */
	#footer { clear:both;}
</style>
</head>
<body>
  <div id="container">
    <!-- nav -->
    <div id="nav">
      <ul type="none">
        <li><a href="/user/join.do" style="color:black"><img src="../images/login.png" width="27">&ensp;로그인</a></li>
      </ul>
    </div>

    <!-- header -->
    <header id="header">
      <a href="./index.do"><img src="../images/(small)ReviewUs.png" id="T_img"></a>
    </header>
    
    <div id="body">
      <!-- main_left -->
      <div id="main_left">
        <div class="ml"><a href="#"><img src="../images/all.png" width="25" >All  </a></div>
        <div class="ml"><a href="#"><img src="../images/bars.png" width="25" >BARS & PUBS  </a></div>
        <div class="ml"><a href="#"><img src="../images/cupcake.png" width="25">DESSERT PLACES</a></div>
        <div class="ml"><a href="#"><img src="../images/restaurant.png" width="25">RESTAURANTS</a></div>
        <div class="ml"><a href="#"><img src="../images/hotel.png" width="25">HOTELS</a></div>
      </div>

      <!-- main_right -->
     <div id="main_r" style="display: flex; flex-direction: column; justify-content: center; align-items: center;">
		<form class="mr" id="searchForm" action="/board/getBoardList.do" method="post">
			<input type="hidden" name="pageNum" value="${pageVO.cri.pageNum }">
			<input type="hidden" name="amount" value="${pageVO.cri.amount }">
			<table border="1" style="width: 700px; border-collapse: collapse;">
				<tr>
					<td align="right">
						<select name="searchCondition">
							<option value="all"
								<c:if test="${searchCondition eq 'all' || searchCondition eq '' || searchCondition eq null}">
									selected="selected"
								</c:if>
							>전체</option>
							<option value="title"
								<c:if test="${searchCondition eq 'title' }">
									selected="selected"
								</c:if>
							>제목</option>
							<option value="content"
								<c:if test="${searchCondition eq 'content' }">
									selected="selected"
								</c:if>
							>내용</option>
							<option value="writer"
								<c:if test="${searchCondition eq 'writer' }">
									selected="selected"
								</c:if>
							>작성자</option>
						</select>
						<input type="text" name="searchKeyword" value="${searchKeyword }">
						<button type="button" id="btnSearch">검색</button>
					</td>
				</tr>
			</table>
		</form>
		
		<table class="mr" id="boardTable" border="1" style="width: 700px; border-collapse: collapse;">
			<tr>
				<th style="backgrond: skyblue; width: 100px;">번호</th>
				<th style="backgrond: skyblue; width: 200px;">제목</th>
				<th style="backgrond: skyblue; width: 150px;">작성자</th>
				<th style="backgrond: skyblue; width: 150px;">등록일</th>
				<th style="backgrond: skyblue; width: 100px;">조회수</th> 
			</tr>
			<c:forEach items="${boardList }" var="board">
				<tr>
					<td>${board.boardNo }</td>
					<td>
						<a href="/board/updateBoardCnt.do?boardNo=${board.boardNo }">${board.boardTitle }</a>
					</td>
					<td>${board.boardWriter }</td>
					<td>
						<fmt:formatDate value="${board.boardRegdate }" pattern="yyyy-MM-dd"/>
					</td>
					<td>${board.boardCnt }</td>
				</tr>
			</c:forEach>
		</table>
		<br/>
		<div style="text-align:center;" class="mr">
			<ul class="pagination">
				<c:if test="${pageVO.prev }">
					<li class="pagination_button">
						<a href="${pageVO.cri.pageNum - 1 }">이전</a>
					</li>
				</c:if>
				
				<c:forEach var="num" begin="${pageVO.startPage }" end="${pageVO.endPage }">
					<li class="pagination_button">
						<a href="${num }">${num }</a>
					</li>
				</c:forEach>
				
				<c:if test="${pageVO.next }">
					<li class="pagination_button">
						<a href="${pageVO.cri.pageNum + 1 }">다음</a>
					</li>
				</c:if>
			</ul>
		</div>
		<br/>
		<a href="/board/insertBoard.do">새 글 등록</a>
	</div>
	<div id="footer">
		<jsp:include page="${pageContext.request.contextPath }/footer.jsp" ></jsp:include>
	</div>
	
	<script>
		$(function() {
			$(".pagination a").on("click", function(e) {
				e.preventDefault();
				
				$("input[name='pageNum']").val($(this).attr("href"));
				$("#searchForm").submit();
			});
			
			$("#btnSearch").on("click", function() {
				$("input[name='pageNum']").val(1);
				
				$("#searchForm").submit();
			});
		});
	</script>
  </div>
 </div>
</body>
</html>