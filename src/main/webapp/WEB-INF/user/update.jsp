<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
        input[type="date"],
        input[type="email"] {
            width: 50%;
            border: 1px solid lightgray;
            height: 30px;
            margin: 10px;
        }


        .txtarea {
            width: 30%;
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

</head>

<body>
    <jsp:include page="./../navigation.jsp"/>

    <div class="container">
        <h2>Update</h2>

        <hr>

        <form action="">
            <div>
                <label for="name">이름<span>*</span></label>
                <br>
                <input type="text" id="name" class="txtarea">
            </div>

            <div>
                <label for="joinId">아이디<span>*</span></label>
                <br>
                <input type="text" id="joinId" class="txtarea">
            </div>

            <div>
                <label for="passwd">비밀번호<span>*</span></label>
                <br>
                <input type="text" id="passwd" class="txtarea">
            </div>

            <div class="phone">
                <label>연락처<span>*</span></label>
                <br>
                <input type="text"><span>-</span><input type="text"><span>-</span><input type="text">
            </div>

            <div>
                <label for="birth">생년월일<span>*</span></label>
                <br>
                <input type="date" id="birth">
            </div>

            <div>
                <label for="mail">이메일<span>*</span></label>
                <br>
                <input type="email" id="mail">
            </div>

            <div>
                <label id="location">주소<span>*</span></label>
                <br>
                <input type="text" id="location">
            </div>

            <div class="btn">
                <input type="button" value="Update">
            </div>
        </form>
    </div>
</body>

</html>