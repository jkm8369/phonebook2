<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
		<h1>주소록</h1>
		
		<h2>회원정보 등록폼</h2>
		<p>회원정보를 등록하는 폼입니다.</p>
		
		<form action = "http://localhost:8080/phonebook2/pbc" method = "get">
			<label>이름(name)</label>
			<input type = "text" name = "name" value = "">
			<br>
			
			<label>핸드폰(hp)</label>
			<input type = "text" name = "hp" value = "">
			<br>
			
			<label>회사(company)</label>
			<input type = "text" name = "company" value = "">
			<br>
			
			<label></label>
			<input type = "hidden" name = "action" value = "write">
			<br>
			
			<button>등록</button>
		</form>
		
		<a href= "http://localhost:8080/phonebook2/pbc?action=list">리스트 바로가기</a>
		
	</body>

</html>