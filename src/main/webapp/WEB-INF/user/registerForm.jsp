<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RegisterForm</title>
    <style>
       .container {
            width: 70%;
            margin: 0 auto;
        }

        h2 {
            color: indianred;
        }

        form {
            width: 100%;
            margin: 0 auto;
        }

        form>div {
            margin: 10px;
        }

        span {
            color: orange;
        }

        input[type="text"],
        input[type="password"],
        input[type="date"],
        input[type="email"] {
            width: 50%;
            border: 1px solid lightgray;
            height: 30px;
            margin: 10px;
        }

        .phone input {
            width: 10%;
        }

        .btn {
            margin: 0 auto;
            display: flex;
            justify-content: center;
            align-items: center;
            text-align: center;
        }

        .btn>input {
            border: none;
            background-color: gray;
            color: white;
            padding: 10px;
            border-radius: 3px;
            transition: all 0.3s ease;
        }

        .btn>input:hover {
            background-color: lightgray;
            color: black;
            cursor: pointer;
        }
    </style>
    
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    
    <script>
       function execDaumPostcode() {
           new daum.Postcode({
               oncomplete: function (data) {
                   // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
   
                   // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                   // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                   var addr = ''; // 주소 변수
                   var extraAddr = ''; // 참고항목 변수
   
                   //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                   if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                       addr = data.roadAddress;
                   } else { // 사용자가 지번 주소를 선택했을 경우(J)
                       addr = data.jibunAddress;
                   }
   
                   // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                   if (data.userSelectedType === 'R') {
                       // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                       // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                       if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                           extraAddr += data.bname;
                       }
                       // 건물명이 있고, 공동주택일 경우 추가한다.
                       if (data.buildingName !== '' && data.apartment === 'Y') {
                           extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.bsuildingName);
                       }
                       // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                       if (extraAddr !== '') {
                           extraAddr = ' (' + extraAddr + ')';
                       }
                       // 조합된 참고항목을 해당 필드에 넣는다.
                       document.getElementById("extraAddress").value = extraAddr;
   
                   } else {
                       document.getElementById("extraAddress").value = '';
                   }
   
                   // 우편번호와 주소 정보를 해당 필드에 넣는다.
                   document.getElementById('postcode').value = data.zonecode;
                   document.getElementById("address").value = addr;
   
                   // 커서를 상세주소 필드로 이동한다.
                   document.getElementById("detailAddress").focus();
               }
           }).open();
       }
    
      function userCreate() {
         if (form.userName.value == "") {
            alert("이름을 입력하십시오.");
            form.userName.focus();
            return false;
         }
         if (form.userId.value == "") {
            alert("사용자 ID를 입력하십시오.");
            form.userId.focus();
            return false;
         } 
         if (form.password.value == "") {
            alert("비밀번호를 입력하십시오.");
            form.password.focus();
            return false;
         }
         var phoneExp = /^\d{2,3}-\d{3,4}-\d{4}$/;
         if(phoneExp.test(form.phone.value)==false) {
            alert("전화번호 형식이 올바르지 않습니다.");
            form.phone.focus();
            return false;
         }
         if (form.birthday.value == "") {
            alert("생년월일을 선택하십시오.");
            form.birthday.focus();
            return false;
         }
         var emailExp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
         if(emailExp.test(form.email.value)==false) {
            alert("이메일 형식이 올바르지 않습니다.");
            form.email.focus();
            return false;
         }
         if(form.postcode.value == ""){
        	 alert("우편번호를 입력하십시오.");
             form.postcode.focus();
             return false;
         }
         if(form.address.value == ""){
        	 alert("주소를 입력하십시오.");
             form.address.focus();
             return false;
         }
         if(form.detailAddress.value == ""){
        	 alert("상세주소를 입력하십시오.");
             form.detailAddress.focus();
             return false;
         }
         if(form.extraAddress.value == ""){
        	 alert("참고항목을 입력하십시오.");
             form.extraAddress.focus();
             return false;
         }
         form.submit();
      }
   </script>
</head>

<body>
    <jsp:include page="./../navigation.jsp"/>
    
    <div class="container">
        <h2>Join membership</h2>

        <hr>

        <form name="form" method="POST" action="<c:url value='/user/register' />">
            <div>
                <label for="name">이름<span>*</span></label>
                <br>
                <input type="text" class="txtarea" name="userName">
            </div>

            <div>
                <label for="joinId">아이디<span>*</span></label>
                <br>
                <input type="text" class="txtarea" name="userId">
            </div>

            <div>
                <label for="passwd">비밀번호<span>*</span></label>
                <br>
                <input type="password" class="txtarea" name="password">
            </div>

            <div class="phone">
                <label>연락처<span>*</span></label>
                <br>
                <input type="text" style="width:240" name="phone" >  <!--   <input type="text"><span>-</span><input type="text"><span>-</span><input type="text">-->
            </div>

            <div>
                <label for="birth">생년월일<span>*</span></label>
                <br>
                <input type="date" name="birthday">
            </div>

            <div>
                <label for="mail">이메일<span>*</span></label>
                <br>
                <input type="email" name="email">
            </div>

            <div>
                <label id="location">주소<span>*</span></label>
                <br>
                <input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
                <input type="text" id="postcode" placeholder="우편번호" name="postcode"><br>
                <input type="text" id="address" placeholder="주소" name="address"><br>
                <input type="text" id="detailAddress" placeholder="상세주소" name="detailAddress"><br>
                <input type="text" id="extraAddress" placeholder="참고항목" name="extraAddress">
            </div>

            <div class="btn">
                <input type="button" value="Join" onClick="userCreate()">
            </div>
        </form>
    </div>
</body>

</html>