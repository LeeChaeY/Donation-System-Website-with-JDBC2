<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<script>
function userUpdate(targetUri) {
	form.action = targetUri;
	form.submit();
}
</script>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyPage</title>
    <style>
        .container {
            width: 70%;
            margin: 0 auto;
        }

        .mypage {
            color: indianred;
            text-align: left;
        }

        .container .content h3 {
            text-align: left;
        }

        .container .content {
            padding: 10px;
        }

        .container .info1>div {
            text-align: left;
            display: flex;
            padding: 5px;
            margin: 5px;
        }

        .container .info .basic {
            font-weight: bold;
            width: 15%;
        }

        .container .btn {
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 10px;
        }

        .container input[type="button"] {
            background-color: gray;
            color: white;
            padding: 10px;
            border-radius: 3px;
            border: none;
            transition: all 0.3s ease;
        }

        .container input[type="button"]:hover {
            background-color: lightgray;
            color: black;
            cursor: pointer;
        }
    </style>
    <script type="text/javascript">
    function update(targetUri) {
		form.action=targetUri;
		form.submit();
	}
    </script>
</head>

<body>
    <jsp:include page="./../navigation.jsp"/>


    <div class="container">
        <h2 class="mypage">My page</h2>

        <hr>

        <div class="content">
            <h3>My Information</h3>

            <div class="info">
                <form class="info1" method="get" action="">
                    <div>
                        <div class="basic">Name</div>
                        <div>${user.name }</div>
                    </div>

                    <div>
                        <div class="basic">ID</div>
                        <div>${user.userId }</div>
                    </div>

                    <div>
                        <div class="basic">Birth</div>
                        <div>${user.birthday }</div>
                    </div>

                    <div>
                        <div class="basic">E-mail</div>
                        <div>${user.email }</div>
                    </div>

                    <div>
                        <div class="basic">Address</div>
                        <div>${user.address }</div>
                    </div>

                    <div>
                        <div class="basic">mobile phone</div>
                        <div>${user.phoneNum }</div>
                    </div>

                    <div>
                        <div class="basic">reward</div>
                        <div>${user.rewardAmount }</div>
                    </div>
                </form>

                <div class="btn">
                    <input type="button" value="Update" onClick="update('<c:url value='/user/update' />')"> <!-- aTag로 바꿔야 할 거 같음: 파라미터로 유저아이디 값을 넘겨야 함 -->
                </div>
            </div>
        </div>
    </div>
</body>

</html>