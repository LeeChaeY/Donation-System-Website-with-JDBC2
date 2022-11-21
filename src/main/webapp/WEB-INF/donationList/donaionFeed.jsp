<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feed</title>
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

        .container .board {
            padding: 10px;
            display: flex;
            justify-content: space-between;
        }

        .post {
            font-size: x-large;
            font-weight: bold;
            color: indianred;
        }

        select {
            width: 25%;
        }

        table {
            width: 100%;
            text-align: center;
            font-size: large;
        }

        td {
            width: 25%;
            padding: 10px;
        }

        td:hover {
            cursor: pointer;
            background-color: lightgray;
        }

        td img {
            width: 100%;
            height: 200px;
        }

        .bottom {
            display: flex;
            flex-direction: row;
            justify-content: center;
        }

        .bottom>input {
            margin: 10px;
        }
    </style>
</head>

<body>
    <jsp:include page="./../navigation.jsp"/>

    <div class="container">
        <div class="board">
            <div class="post">VIEWS</div>

            <select name="" id="">
                <option value="">悼拱</option>
                <option value="">犁抄 犁秦</option>
                <option value="">荤雀 秒距 拌摸</option>
            </select>
        </div>

        <hr>

        <table>
            <tr>
                <td>
                    <div>力格</div>
                    <img src="../img/cat.jpg" alt="">
                </td>

                <td>
                    <div>力格</div>
                    <img src="../img/disaster.jpg" alt="">
                </td>

                <td>
                    <div>力格</div>
                    <img src="../img/animal.png" alt="">
                </td>

                <td>
                    <div>力格</div>
                    <img src="../img/rain.jpg" alt="">
                </td>
            </tr>

            <tr>
                <td>
                    <div>力格</div>
                    <img src="../img/animal.png" alt="">
                </td>

                <td>
                    <div>力格</div>
                    <img src="../img/cat.jpg" alt="">
                </td>

                <td>
                    <div>力格</div>
                    <img src="../img/volunteer.jpg" alt="">
                </td>

                <td>
                    <div>力格</div>
                    <img src="../img/rain.jpg" alt="">
                </td>
            </tr>

            <tr>
                <td>
                    <div>力格</div>
                    <img src="../img/rain.jpg" alt="">
                </td>

                <td>
                    <div>力格</div>
                    <img src="../img/animal.png" alt="">
                </td>

                <td>
                    <div>力格</div>
                    <img src="../img/disaster.jpg" alt="">
                </td>

                <td>
                    <div>力格</div>
                    <img src="../img/cat.jpg" alt="">
                </td>
            </tr>
        </table>
    </div>
</body>

</html>