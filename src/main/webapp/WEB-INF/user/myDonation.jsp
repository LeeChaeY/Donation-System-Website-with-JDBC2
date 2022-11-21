<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyDonation</title>
    <style>
        .container {
            width: 70%;
            margin: 0 auto;
        }

        .container h2 {
            text-align: left;
        }

        table {
            width: 90%;
            margin: 0 auto;
            border: 1px solid goldenrod;
            text-align: center;
            border-collapse: collapse;
        }

        th {
            background-color: gold;
            border: 1px solid goldenrod;
        }

        td {
            border: 1px solid goldenrod;
        }

        td:nth-child(1) {
            width: 25%;
        }

        td>img {
            width: 50%;
            height: 100px;
        }

        td:nth-child(2) {
            width: 40%;
        }

        td:nth-child(3),
        td:nth-child(4) {
            width: 10%;
        }

        button {
            background-color: gray;
            color: white;
            border-radius: 3px;
            border: none;
            transition: all 0.3s ease;
            padding: 10px;
        }

        button:hover {
            background-color: lightgray;
            color: black;
            cursor: pointer;
        }
    </style>
</head>

<body>
    <jsp:include page="./../navigation.jsp"/>

    <div class="container">
        <h2>내가 기부한 목록</h2>

        <hr>
        <table>
            <tr>
                <th>사진</th>
                <th>제목</th>
                <th>작성자</th>
                <th>기부 금액(단위 : 원)</th>
                <th>바로가기</th>
            </tr>

            <tr>
                <td>
                    <img src="../img/animal.png" alt="">
                </td>

                <td>동덕여대 인근 유기견</td>

                <td>dongduk</td>
                <td>5000</td>
                <td>
                    <button>Go to Site</button>
                </td>
            </tr>

            <tr>
                <td>
                    <img src="../img/cat.jpg" alt="">
                </td>

                <td>교통사고 당한 고양이를 위한 병원비가 필요합니다</td>

                <td>dongduk</td>
                <td>1500</td>
                <td>
                    <button>Go to Site</button>
                </td>
            </tr>

            <tr>
                <td>
                    <img src="../img/rain.jpg" alt="">
                </td>

                <td>이번 폭우로 인해 집이 망가졌습니다.</td>

                <td>somsome</td>
                <td>10000</td>
                <td>
                    <button>Go to Site</button>
                </td>
            </tr>
        </table>
    </div>
</body>

</html>