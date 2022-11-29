<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feed</title>
    <style>
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
     <script>
	function ViewArticle(targetUri) {
		location.href = targetUri;
	}
	</script>
</head>

<body>
    <jsp:include page="./../navigation.jsp"/>

    <div class="container">
        <div class="board">
            <div class="post">VIEWS</div>

            <select name="" id="">
                <option value="">동물</option>
                <option value="">재난 재해</option>
                <option value="">사회 취약 계층</option>
            </select>
        </div>

        <hr>
		
		<table>
			    <tr>
			    <c:forEach var="article" items="${articleList}" varStatus="status">
			    	<c:if test="${status.index % 4 == 0}">
			    		</tr><tr>
			    	</c:if>
			    		<td onclick="location.href='<c:url value='/donationList/${article.category}' >
			  							<c:param name='articleId' value='${article.articleId}'/>
			  						</c:url>'">
                			<div>${article.title}</div>
                    		<img src="../img/cat.jpg" alt="">
                		</td>
			    </c:forEach>
				</tr>
        </table>
        
    </div>
</body>

</html>