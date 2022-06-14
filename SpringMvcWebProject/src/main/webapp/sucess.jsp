<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>welcome</title>
</head>
<body>
Success.${userName}
<button type="button" onclick="back()">back</button><br/>
<script>
function back(){
	window.location.href="./form.jsp"
}
</script>
</body>
</html>