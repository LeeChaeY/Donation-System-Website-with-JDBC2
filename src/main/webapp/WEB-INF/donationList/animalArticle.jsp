<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AnimalView</title>
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

        .container .desc {
            color: indianred;
        }

        .container-title {
            text-align: center;
        }

        .container .writer {
            text-align: right;
            font-style: italic;
        }

        .container .declare {
            text-align: right;
            margin: 10px;
        }

        .container .declare>button {
            background-color: red;
            color: white;
            border: none;
            border-radius: 3px;
        }
        
         .container .update {
            text-align: right;
            margin: 10px;
        }

        .container .update>a {
            background-color: cadetblue;
            color: white;
            border: none;
            border-radius: 3px;
            text-decoration: none;
        }

        .container .declare>a {
            background-color: lightcoral;
        }

        .container .deadline {
            color: cadetblue;
            text-align: center;
        }

        .container .imgPost {
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .container .info-title {
            font-weight: bold;
            font-size: x-large;
            text-align: center;
            padding: 20px;
            margin: 20px 0;
        }

        .container .info1>div {
            display: flex;
            padding: 5px;
            margin: 5px;
        }

        .container .info .basic {
            font-weight: bold;
            width: 15%;
        }

        .container .info2>div,
        .container .info3>div {
            padding: 5px;
            margin: 5px;
        }

        .container .donation {
            text-align: center;
            padding: 30px;
            margin: 10px 0;
        }

        .container .donation .account {
            font-size: x-large;
            font-weight: bold;
            margin: 20px;
        }

        .container .donation>.account>span {
            color: cornflowerblue;
        }

        .container .donation button {
            border: none;
            border-radius: 5px;
            padding: 10px;
            color: white;
            background-color: darkblue;
            transition: all 0.3s ease;
        }

        .container .donation button:hover {
            background-color: cornflowerblue;
        }

        .container .donater table {
            text-align: center;
            margin: 50px auto;
            width: 70%;
            border-collapse: collapse;
        }

        .container .donater table th,
        .container .donater table td {
            border: 1px solid black;
            height: 30px;
            width: 50%;
        }

        .container .receipt {
            text-align: center;
        }

        .container .comment>div {
            padding: 10px;
        }

        .container .comment>.comment-title {
            font-weight: bold;
            font-size: large;
        }

        .container .comment>form {
            text-align: right;
            padding: 10px;
        }

        .container .comment>form>button {
            background-color: yellowgreen;
            border: none;
            border-radius: 3px;
            color: white;
            padding: 5px;
        }

        .container .comment>form>button:hover {
            background-color: lightgreen;
        }

        .container .comment>.line {
            border-bottom: 1px dotted green;
        }

        .container .comment>.line>.person {
            display: flex;
            padding: 5px;
        }

        .container .comment>.line>.person>div {
            padding: 5px;
        }

        .container .comment>.line>.person>.person-time {
            color: gray;
        }

        .container .comment>.line>button {
            border: none;
            padding: 5px;
            border-radius: 3px;
            float: right;
        }

        button {
            transition: all 0.3s ease;
        }

        button:hover {
            cursor: pointer;
        }
    </style>
</head>

<body>
    <jsp:include page="./../navigation.jsp"/>

    <div class="container">
        <h2 class="desc">Donation for animals</h2>

        <h2 class="container-title">${article.title }</h2>

        <div class="writer">작성자 id : ${article.userId }</div>
		
		<!-- [20221120] insert, delete 추가, 신고 수정(글쓴이는 자신을 신고x) from 나현  -->
		<c:if test="${sessionScope.userId eq article.userId }">
			<div class="update">
			  	<a href="<c:url value='/donationList/animalArticleUpdate' >
			  				<c:param name='userId' value='${article.userId}'/>
			  				<c:param name='articleId' value='${article.articleId}'/>
			  			</c:url>">수정하기</a>
			  	<a href="<c:url value='/donationList/animalArticleDELTE' />">삭제하기</a>		  
			</div>
		</c:if>

		<c:if test="${sessionScope.userId ne article.userId }">
	        <div class="declare">
	            <button onclick="confirm('신고하시겠습니까?')">신고하기</button>
	        </div>
		</c:if>
		
        <hr>

        <h2 class="deadline">[후원 마감일] ${article.deadline }</h2>

        <hr>

        <div class="imgPost">
            <img src="../img/cat.jpg" alt="">
        </div>

        <div>
            <h2 class="info-title">후원 기본 정보</h2>

            <div class="info">
                <div class="info1">
                    <div>
                        <div class="basic">이름</div>
                        <div>${article.name }</div>
                    </div>

                    <div>
                        <div class="basic">지역</div>
                        <div>${article.area }</div>
                    </div>

                    <div>
                        <div class="basic">종</div>
                        <div>${article.type }</div>
                    </div>

                    <div>
                        <div class="basic">나이</div>
                        <div>${article.age }</div>
                    </div>

                    <div>
                        <div class="basic">몸무게</div>
                        <div>${article.weight }</div>
                    </div>

                    <div>
                        <div class="basic">성별</div>
                        <div>${article.gender }</div>
                    </div>

                    <div>
                        <div class="basic">중성화 유무</div>
                        <div>${article.gender }</div>
                    </div>

                    <div>
                        <div class="basic">현재 상황</div>
                        <div>${article.currentStatus }</div>
                    </div>
                </div>

                <div class="info2">
                    <div>
                        <div class="basic">건강 상태</div>
                        <div>
                            ${article.healthStatus }
                        </div>
                    </div>

                    <div>
                        <div class="basic">성격</div>
                        <div>
                            ${article.personality }
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <hr>

        <div>
            <h2 class="info-title">후원금 사용 계획</h2>

            <div class="info info3">
                <div>
                    <div class="basic">후원 마감일</div>
                    <div>${article.deadline }</div>
                </div>

                <div>
                    <div class="basic">후원금 사용 마감일</div>
                    <div>${article.dueDate }</div>
                </div>

                <div>
                    <div class="basic">사용 예산안</div>
                    <div>
                        ${article.usePlan }
                    </div>
                </div>

                <div>
                    <div class="basic">기타</div>
                    <div>
                        ${article.otherText }
                    </div>
                </div>
            </div>
        </div>

        <hr>

        <div class="donation">
            <div class="account">
                <span>후원 계좌 : </span>
                <span>${article.bankName }</span>
                <span>${article.accHolder }</span>
                <span>${article.accNum }</span>
            </div>

            <button>후원하기</button>
        </div>

        <hr>

        <div class="donater">
            <h2 class="info-title">후원금 입금 내역</h2>

            <table>
                <tr>
                    <th>이름</th>
                    <th>후원금액(단위 : 원)</th>
                </tr>

                <tr>
                    <td>김민수</td>
                    <td>15,000</td>
                </tr>

                <tr>
                    <td>박지운</td>
                    <td>1,000</td>
                </tr>

                <tr>
                    <td>이진우</td>
                    <td>50,000</td>
                </tr>

                <tr>
                    <td>김민지</td>
                    <td>10,000</td>
                </tr>

                <tr>
                    <td>이예진</td>
                    <td>55,000</td>
                </tr>

                <tr>
                    <td>최수하</td>
                    <td>1,000</td>
                </tr>

                <tr>
                    <td>임지민</td>
                    <td>500</td>
                </tr>

                <tr>
                    <td>이경원</td>
                    <td>35,000</td>
                </tr>

                <tr>
                    <td>박지은</td>
                    <td>10,000</td>
                </tr>

                <tr>
                    <td>이주원</td>
                    <td>15,000</td>
                </tr>

                <tr>
                    <th colspan="2">총액: ${article.otherText }</th>
                </tr>
            </table>
        </div>

        <hr>

        <div class="receipt">
            <h2 class="info-title">후원금 사용 내역</h2>
            <div>
                <img src="../img/receipt.jpg" alt="">
            </div>
        </div>

        <hr>

        <div class="comment">
            <div class="comment-title">댓글</div>

            <form action="">
                <textarea name="" id="" style="width: 100%;" rows="5"></textarea>
                <button>댓글 작성</button>
            </form>

            <div class="line">
                <button>신고</button>

                <div class="person">
                    <div class="person-id">id[작성자]</div>
                    <div class="person-time">2022-10-24 16:00</div>
                </div>

                <div class="person-content">덕분에 후원이 많이 이뤄지고 있습니다.</div>
            </div>

            <div class="line">
                <button>신고</button>

                <div class="person">
                    <div class="person-id">id[후원자]</div>
                    <div class="person-time">2022-10-20 11:00</div>
                </div>

                <div class="person-content">영수증 인증 서둘러 부탁드립니다.</div>
            </div>

            <div class="line">
                <button>신고</button>

                <div class="person">
                    <div class="person-id">id[후원자]</div>
                    <div class="person-time">2022-10-01 19:00</div>
                </div>

                <div class="person-content">상태는 호전 되었나요?</div>
            </div>
        </div>
    </div>
</body>

</html>