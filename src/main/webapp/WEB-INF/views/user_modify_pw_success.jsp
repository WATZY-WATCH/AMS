<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% session.invalidate(); %>
	<script type="text/javascript">
		alert("성공적으로 변경되었습니다.\n다시 로그인해주세요.");
		document.location.href = "/";
	</script>
</body>
</html>