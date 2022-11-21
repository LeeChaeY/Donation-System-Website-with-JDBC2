<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DisasterForm</title>
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
        }

        nav {
            background-color: antiquewhite;
            display: flex;
            align-items: center;
            justify-content: center;
            text-align: center;
        }

        nav>div {
            display: flex;
            align-items: center;
            justify-content: space-between;
            width: 70%;
        }

        .title {
            font-weight: bold;
            font-size: xx-large;
            color: brown;
            margin: 30px 0px;
        }

        #main-menu {
            display: flex;
            align-items: center;
            justify-content: center;
        }

        #main-menu,
        #sub-menu {
            margin: 0;
            padding: 0;
            list-style-type: none;
        }

        #main-menu>li {
            padding: 15px;
        }

        #main-menu>li>a {
            color: black;
            text-align: center;
            text-decoration: none;
            font-weight: 600;
        }

        #main-menu>li>a:hover {
            cursor: pointer;
            color: gray;
        }

        #sub-menu {
            height: 0;
            visibility: hidden;
            transition: all 0.15s ease;
            position: relative;
            z-index: 10;
        }

        #sub-menu>li {
            width: 115px;
            padding: 10px 0px;
            margin: 0 auto;
            text-align: center;
            background: brown;
            border-bottom: 1px solid rgba(255, 255, 255, 0.6);
        }

        #sub-menu>li>a {
            color: rgba(255, 255, 255, 0.6);
            text-decoration: none;
        }

        #main-menu>li:hover #sub-menu {
            visibility: visible;
        }

        #sub-menu>li>a:hover {
            cursor: pointer;
            color: lightgray;
        }

        .container {
            width: 70%;
            margin: 0 auto;
        }

        h2 {
            color: palevioletred;
        }

        form {
            width: 100%;
            margin: 0 auto;
        }

        form>div {
            margin: 15px;
        }

        span {
            color: orange;
        }

        input {
            border: 1px solid lightgray;
            padding: 5px;
            margin: 5px;
        }

        input[type="text"] {
            width: 50%;
        }

        input[type="file"] {
            font-size: medium;
            width: 50%;
        }

        input[type="date"] {
            width: 50%;
            font-size: large;
        }

        textarea {
            border: 1px solid lightgray;
            width: 50%;
            margin: 5px;
        }

        .btn {
            margin: 0 auto;
            display: flex;
            justify-content: center;
            align-items: center;
            text-align: center;
        }

        .btn>input {
            background-color: gray;
            border: none;
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
        <h2>CREATE DONATION(Disaster)</h2>

        <hr>

        <form name="form" method="POST" action="<c:url value='/donationForm/disaster' />">
            <div>
                <label for="title">제목<span>*</span></label>
                <br>
                <input type="text" id="title">
            </div>

            <div>
                <label for="region">지역<span>*</span></label>
                <br>
                <input type="text" id="region" name="region">
            </div>

            <div>
                <div>재난 재해 종류<span>*</span></div>
                <br>
                <input type="radio" name="type" id="typhoon"><label for="typhoon">태풍</label>
                <input type="radio" name="type" id="earthquake"><label for="earthquake">지진</label>
                <input type="radio" name="type" id="drought"><label for="drought">가뭄</label>
                <input type="radio" name="type" id="flood"><label for="flood">홍수(폭우)</label>
                <input type="radio" name="type" id="sortEtc"><label for="sortEtc">기타</label>
            </div>

            <div>
                <label for="disaterName">재난 재해 명칭<span>*</span></label>
                <br>
                <input type="text" id="disaterName" name="disaterName">
            </div>

            <div>
                <label for="damage_amount">피해 금액<span>*</span></label>
                <br>
                <input type="number" id="damage_amount" name="damage_amount">(단위: 원)
            </div>

            <div>
                <label>현재 상황<span>*</span></label>
                <br>
                <textarea name="present_condition" id="present_condition" rows="7"></textarea>
            </div>

            <div>
                <label for="photo">사진<span>*</span></label>
                <br>
                <input type="file" id="photo" name="photo">
            </div>

            <div>
                <label for="deadline">후원 마감일<span>*</span></label>
                <br>
                <input type="date" id="deadline">
            </div>

            <div>
                <label for="bank">후원 계좌 은행<span>*</span></label>
                <br>
                <input type="text" id="bank">
            </div>

            <div>
                <label for=account_holder>후원 계좌 예금주 이름<span>*</span></label>
                <br>
                <input type="text" id="account_holder" name="account_holder">
            </div>

            <div>
                <label for="account_number">후원 계좌 번호<span>*</span></label>
                <br>
                <input type="text" id="account_number" name="account_number">
            </div>

            <div>
                <label for="due_date">후원금 사용 마감일<span>*</span></label>
                <br>
                <input type="date" id="due_date" name="due_date">
            </div>

            <div>
                <label>사용 계획<span>*</span></label>
                <br>
                <textarea name="use_plan" id="use_plan" rows="7" placeholder="예) 전기 설비 : 300,000 등"></textarea>
            </div>

            <div>
                <label for="other_text">기타</label>
                <br>
                <textarea name="other_text" id="other_text" rows="7"></textarea>
            </div>

            <div class="btn">
                <input type="button" value="Create">
            </div>
        </form>
    </div>
</body>

</html>