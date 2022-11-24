<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 쓰기</title>
<style>
	 #container { width: 800px; margin: 0 auto;}
	 #category { width: 15%; height: 28px; margin-bottom: 10px;}
	 select option[value=""][disabled] {
	   display: none;
	 }
	 #title { margin-bottom: 10px; width:90%; height: 30px; border-width: 0 0 1px 0; font-size: 18px;}
	 #uploadFile { margin-top:8px;}
	 #star { margin-bottom: 10px;}
	 #boardForm div{
	     display: inline-block;
	     direction: rtl;
	     border:0;
	 }
	 #boardForm input[type=radio]{
	     display: none;
	 }
	 #boardForm>div label{
	     font-size: 1.8em;
	     color: transparent;
	     text-shadow: 0 0 0 #f0f0f0;
	 }
	 #boardForm>div label:hover{
	     text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
	 }
	 #boardForm>div label:hover ~ label{
	     text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
	 }
	 #boardForm>div input[type=radio]:checked ~ label{
	     text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
	 }
	 #content { margin-bottom: 5px;}
	 #searchGeo { margin-bottom: 5px; font-size: 18px;}
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
	<h3>리뷰 쓰기</h3>
	<form name="boardForm" id="boardForm" action="/board/insertBoard.do" method="post">
		<select name="boardCate" id="boardCate" required>
			<option value="" disabled selected>CATEGORY</option>
			<option value="HOTELS">HOTELS</option>
			<option value="DESSERT">DESSERT</option>
			<option value="BARS&PUBS">BARS&amp;PUBS</option>
			<option value="RESTAURANT">RESTAURANT</option>
		</select>
		<input type="text" name="boardTitle" id="boardTitle" placeholder="제목"><br>
		<label for="userId" id="userId">작성자: </label><input type="text" name="userId" value="${loginUser.userId}" readonly><br>
		<input type="file" name="uploadFile" id="uploadFile"><br>
		<div id="star">
			<span class="text-bold">별점을 선택해주세요</span>
			<input type="radio" name="boardStar" value="5" id="rate1"><label for="rate1">★</label>
			<input type="radio" name="boardStar" value="4" id="rate2"><label for="rate2">★</label>
			<input type="radio" name="boardStar" value="3" id="rate3"><label for="rate3">★</label>
			<input type="radio" name="boardStar" value="2" id="rate4"><label for="rate4">★</label>
			<input type="radio" name="boardStar" value="1" id="rate5"><label for="rate5">★</label>
		</div>
		<textarea name="boardMain" id="boardMain" cols="116" rows="30"></textarea>
		<input type="text" name="searchGeo" id="searchGeo" placeholder="상호를 검색하세요"><br>
		<div id="map" style="width:80%;height:240px;"></div>
	</form>
	<div id="btns">
		<button type="button" id="btnList" onclick="location.href='/board/getBoardList.do'">목록</button>
		<button type="button" id="btnDone" onclick="location.href='/board/insertBoard.do'">등록</button>
		<button type="button" id="btnCancel" onclick="history.back();">취소</button>
	</div>

</div>
<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=74dadd665477bb479ae9a0d793b563cd&libraries=services"></script>
<script>
	// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
	var infowindow = new kakao.maps.InfoWindow({zIndex:1});
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = {
	        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };  
	
	// 지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	// 장소 검색 객체를 생성합니다
	var ps = new kakao.maps.services.Places(); 
	
	// 키워드로 장소를 검색합니다
	ps.keywordSearch('하이디라오', placesSearchCB); 
	
	// 키워드 검색 완료 시 호출되는 콜백함수 입니다
	function placesSearchCB (data, status, pagination) {
	    if (status === kakao.maps.services.Status.OK) {
	
	        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
	        // LatLngBounds 객체에 좌표를 추가합니다
	        var bounds = new kakao.maps.LatLngBounds();
	
	        for (var i=0; i<data.length; i++) {
	            displayMarker(data[i]);    
	            bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
	        }       
	
	        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
	        map.setBounds(bounds);
	    } 
	}
	
	// 지도에 마커를 표시하는 함수입니다
	function displayMarker(place) {
	    
	    // 마커를 생성하고 지도에 표시합니다
	    var marker = new kakao.maps.Marker({
	        map: map,
	        position: new kakao.maps.LatLng(place.y, place.x) 
	    });
	
	    // 마커에 클릭이벤트를 등록합니다
	    kakao.maps.event.addListener(marker, 'click', function() {
	        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
	        infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
	        infowindow.open(map, marker);
	    });
	}
</script>
</body>
</html>