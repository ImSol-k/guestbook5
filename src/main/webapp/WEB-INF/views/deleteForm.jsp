<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/guestbook5/guest/delete" method="get">
		<table>
			<tr>
				<td>비밀번호</td>
				<td>
				<input type="hidden" name="no" value="${requestScope.no }">
				<input type="password" name="pass"></td>
				<td><button type="submit">삭제</button></td>
			</tr>
		</table>
	</form>
	
	<br><br>
	<a href="">메인으로 돌아가기</a>
</body>
</html>