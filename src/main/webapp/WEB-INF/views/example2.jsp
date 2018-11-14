<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h3>example2.jsp</h3>
	
	<p>${value1 }</p>
	<p>${value2 }</p>
	
	<form action="example3" method="post">
		<input type="text" name="id" value="tom"><br/>
		<input type="password" name="pw" value="1234"><br/>
		<button type="submit">Send</button>
	</form>
</body>
</html>