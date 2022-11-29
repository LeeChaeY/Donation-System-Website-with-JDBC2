<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
<title>Alert</title>
<script type="text/javascript">
	window.onload = function() { 
		alert("dd");
		// https://gurtn.tistory.com/126
		var urlParams = new URL(location.href).searchParams;
		var urlParams = new URL(location.href).searchParams;
		var category = urlParams.get('category');
		var url = urlParams.get('url');
		//var msg = "<c:out value='${msg}'/>";
	    //var url = "<c:out value='${url}'/>";
	    //alert(msg);
	    location.href = "<c:url value='${url}' > <c:param name='category' value='${category}'/> </c:url>";
  };
</script>
</head>
<body>
</body>
</html>