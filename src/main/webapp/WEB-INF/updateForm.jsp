<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ page import = "com.javaex.vo.PersonVO" %>
<%
	PersonVO personVO = (PersonVO)request.getAttribute("person");
	System.out.println("여기는 jsp");
	System.out.println(personVO);
%>
<!DOCTYPE html>

<html>

	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
		<h1>주소록</h1>
		
		<h2>회원정보 수정폼</h2>
		<p>회원정보를 수정하는 폼입니다.</p>
		
		<form action = "http://localhost:8080/phonebook2/pbc" method = "get">
			<label>이름(name)</label>
			<input type = "text" name = "name" value = "<%= personVO.getName() %>">
			<br>
			
			<label>핸드폰(hp)</label>
			<input type = "text" name = "hp" value = "<%= personVO.getHp() %>">
			<br>
			
			<label>회사(company)</label>
			<input type = "text" name = "company" value = "<%= personVO.getCompany() %>">
			<br>
			
			<label></label>
			<input type = "text" name = "action" value = "update">
			<br>
			
			<label></label>
			<input type = "text" name = "no" value = "<%= personVO.getPersonId() %>">
			
			<button>등록</button>
			
			
		</form>
		
		<a href = "http://localhost:8080/phonebook2/pbc?action=list">
		리스트 바로가기
		</a>
		
	</body>

</html>