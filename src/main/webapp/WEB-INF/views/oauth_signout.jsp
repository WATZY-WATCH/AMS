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
		alert("정상적으로 연동해제 되었습니다.");
		document.location.href = "/";
	</script>
</body>
</html>