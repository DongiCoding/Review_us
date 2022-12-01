<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath }/js/jquery-3.6.1.min.js"></script>
<style>
#container {
   width: 1000px;
   margin: 0 auto;
}

/* nav */
#nav {
   margin-top: 20px;
   margin-left: 575px;
   color: black;
}

ul li {
   list-style: none;
}

#nav a {
   text-decoration: none;
}

#nav a:hover {
   font-weight: bold;
   text-decoration: underline;
}

/* header */
header {
   margin: 20px auto;
   text-align: left;
   margin-left: 70px;
}

#T_img {
   width: 530px;
   height: 100px;
}

/* body */
#body {
   width: 100%;
}

/* main_left bar */
#main_left {
   width: 25%;
   float: left;
}

.ml {
   width: 93%;
   height: 122px;
   padding: 10px;
   font-size: 1.5em;
   font-weight: bold;
   border: 1px solid black;
   line-height: 150px;
   text-align: center;
}

#main_left a {
   text-decoration: none;
   color: black;
   text-shadow: 3px -3px #E0E0E0;
}

#main_left a:hover {
   color: red;
}



/* Main right */
#main_r {
   width: 60%;
   float: right;
   margin-left: 5px;
}

#tr td { text-align:center; }
#tr td a { color:black; text-decoration:none; }

/* 좋아요 */
.like {
   width: 25px;
   text-align: right;
   margin-left: 180px;
}

.pagination {
   list-style: none;
   width: 70%;
   display: inline-block;
}

.pagination a { color:black; text-decoration:none;}

.pagination_button {
   float: left;
   margin-left: 5px;
}

/* footer */
#footer {
   clear: both;
}
</style>
</head>
<body>
   <div id="container">
      <!-- nav -->
      <div id="nav">
         <ul id="nav_ul">
            <c:choose>
               <c:when test="${loginUser eq null }">
                  <li class="nav_li"><a href="/user/login.do" style="color: black">&ensp;로그인</a>
                      <a href="/user/join.do" style="color: black">&ensp;회원가입</a>
                  </li> 
               </c:when>
               <c:otherwise>
                  <li><a href="/board/getBoardList.do">&ensp;로그아웃</a></li>
               </c:otherwise>
            </c:choose>
         </ul>
      </div>

      <!-- header -->
      <header id="header">
         <a href="./index.do"><img src="../images/(small)ReviewUs.png"
            id="T_img"></a>
      </header>

      <div id="body">
         <!-- main_left -->
         <div id="main_left">
            <div class="ml">
               <a href="#">All
               </a>
            </div>
            <div class="ml">
               <a href="#">BARS
                  & PUBS </a>
            </div>
            <div class="ml">
               <a href="#">DESSERT & PLACES</a>
            </div>
            <div class="ml">
               <a href="#">RESTAURANTS</a>
            </div>
            <div class="ml">
               <a href="#">HOTELS</a>
            </div>
         </div>

         <!-- main_right -->
         <div id="main_r"
            style="display: flex; flex-direction: column; justify-content: center; align-items: center;">
            <form id="searchForm" action="/board/getBoardList.do" method="post">
               <!-- 페이지 번호:hidden 이유 -->
               <table border="1" style="width: 700px; border-collapse: collapse;">
                  <tr>
                     <td align="right"><select name="searchCondition">
                           <option value="all"
                              <c:if test="${searchCondition eq 'all' || searchCondition eq '' || searchCondition eq null}">
                                 selected="selected"
                              </c:if>>전체</option>
                           <option value="title"
                              <c:if test="${searchCondition eq 'title' }">
                                 selected="selected"
                              </c:if>>제목</option>
                           <option value="main"
                              <c:if test="${searchCondition eq 'main' }">
                                 selected="selected"
                              </c:if>>내용</option>
                           <option value="id"
                              <c:if test="${searchCondition eq 'id' }">
                                 selected="selected"
                              </c:if>>작성자</option>
                     </select> <input type="text" name="searchKeyword"
                        value="${searchKeyword }">
                        <button type="button" id="btnSearch">검색</button></td>
                  </tr>
               </table>
            </form>

            <table class="mr" id="boardTable" border="1"
               style="width: 700px; border-collapse: collapse;">
               <tr>
                  <th style="background: skyblue; width: 10%;">번호</th>
                  <th style="background: skyblue; width: 45%;">제목</th>
                  <th style="background: skyblue; width: 16%;">작성자</th>
                  <th style="background: skyblue; width: 16%;">작성일</th>
                  <th style="background: skyblue; width: 13%;">조회수</th>
               </tr>
               <!-- var에는 아무거나 상관없음 -->
               <c:forEach items="${boardList }" var="board">
                  <tr id="tr">
                     <td>${board.boardNo }</td>
                     <td>
                        <!-- ? 다음에 오는 BoardNO 콜할려면 @RequestParam("boardNo") 필요 --> <a
                        href="/board/updateBoardCnt.do?boardNo=${board.boardNo }">${board.boardTitle }</a>
                     </td>
                     <td>${board.userId }</td>
                     <td><fmt:formatDate value="${board.boardRgd }"
                           pattern="yyyy-MM-dd" /></td>
                     <td>${board.boardCnt }</td>
                  </tr>
               </c:forEach>
            </table>
            <br />
            <div style="text-align: center;" class="mr">
               <ul class="pagination">
                  <c:if test="${pageVO.prev }">
                     <li class="pagination_button"><a
                        href="${pageVO.cri.pageNum - 1 }">이전</a></li>
                  </c:if>

                  <c:forEach var="num" begin="${pageVO.startPage }" end="${pageVO.endPage }">
                     <li class="pagination_button"><a href="${num }">${num }</a>
                     </li>
                  </c:forEach>

                  <c:if test="${pageVO.next }">
                     <li class="pagination_button"><a href="${pageVO.cri.pageNum + 1 }">다음</a></li>
                  </c:if>
               </ul>
            </div>
            <br /> <a href="/board/insertBoard.do">새 글 등록</a>
         </div>
         <div id="footer">
            <jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
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