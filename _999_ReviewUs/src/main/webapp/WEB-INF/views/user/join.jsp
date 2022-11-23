<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰어스 계정 만들기</title>
<style>
#container {
    margin: 100px auto;
    width: 525px; height: 465px;
    border: 1px #E0E0E0 solid;
    border-radius: 10px;
}

#review_us_logo {
    width: 230px;
    margin: 25px 10px 5px 25px;
}

h3 {
    margin: 10px 10px 10px 25px;
    font-weight: 600;
}

input[type="text"] {
    width: 176px; height: 20px;
    margin: 10px 10px 10px 25px;
    padding: 6px 8px;
    font-size: 15px;
}

input[type="password"] {
    width: 176px; height: 20px;
    margin: 10px 0px 10px 25px;
    padding: 6px 8px;
    font-size: 15px;
}

button[type="button"] {
    height: 35px;
    font-size: 15px;
}

#selectEmail {
    height: 20px;
}

button[type="submit"] {
    height: 35px;
    font-size: 15px;
    margin-left: 435px;
}
</style>
</head>
<body>
<div id="container">
        <form id="joinForm" action="/user/join.do" method="post">
            <img src="../UI_images/(small)ReviewUs.png" id="review_us_logo">
            <h3>리뷰어스 계정 만들기</h3>
            <div>
                <input type="text" autocomplete="off" spellcheck="false" aria-label="아이디" placeholder="아이디" id="userId" name="userId" required>
                <button type="button" id="btnIdCheck">중복확인</button>
            </div><br>
            <input type="password" autocomplete="off" spellcheck="false" aria-label="비밀번호" placeholder="비밀번호" id="userPw" name="userPw" required>
            <input type="password" autocomplete="off" spellcheck="false" aria-label="비밀번호 확인" placeholder="비밀번호 확인" id="userPwCheck" name="userPwCheck" required><br><br>
            <div>
                <input type="text" autocomplete="off" spellcheck="false" aria-label="닉네임" placeholder="닉네임" id="userNickname" name="userNickname" required>
                <button type="button" id="btnNicknameCheck">중복확인</button>
            </div>
            <br>
            <input type="text" autocomplete="off" spellcheck="false" aria-label="이메일" placeholder="이메일" required><span style="font-size: 15px;">@</span>
            <input type="text" autocomplete="off" spellcheck="false" aria-label="도메인주소" id="selectEmail2" name="selectEmail2" style="width:100px; margin-left: 4px;" disabled value="naver.com" required>
            <select name="selectEmail" id="selectEmail">
                <option value="1">직접입력</option>
                <option value="naver.com">naver.com</option>
                <option value="gmail.com">gmail.com</option>
                <option value="kakao.com">kakao.com</option>
                <option value="daum.net">daum.net</option>
                <option value="hanmail.com">hanmail.com</option>
                <option value="nate.com">nate.com</option>
                <option value="dreamwiz.com">dreamwiz.com</option>
            </select>
            <br><br>
            <div>
                <button type="submit">만들기</button>
            </div>
        </form>
    </div>
    <script type="text/javascript">
        //이메일 입력방식 선택
        $('#selectEmail').change(function(){
            $("#selectEmail option:selected").each(function () {
                if($(this).val()== '1'){ //직접입력일 경우
                    $("#selectEmail2").val('');                        //값 초기화
                    $("#selectEmail2").attr("disabled",false); //활성화
                }else{ //직접입력이 아닐경우
                    $("#selectEmail2").val($(this).text());      //선택값 입력
                    $("#selectEmail2").attr("disabled",true); //비활성화
                }
            });
        });
    </script>
</body>
</html>