<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- pom 에 이미 추가되어있어서 다운받지 않아도 된다 -->
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
.dataRow:hover {
	background: #eee;
	cursor: pointer;
}

</style>

<script src="https://code.jquery.com/jquery-3.5.0.js"></script>

<script type="text/javascript">

// 	$(function(){

// 		$(".dataRow").click(function(){
// 			location="view.do?no=10";

// 			});
// 		});
</script>

<title>일반게시판 > 리스트</title>
</head>
<body>
<h2>일반게시판 > 리스트</h2>
<table>
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
	<c:forEach items="${list }" var="vo">
	<tr class="dataRow" onclick="location='view.do?no=${vo.no}&inc=1'" >
		<td>${vo.no }</td>
		<td>${vo.title }</td>
		<td>${vo.writer }</td>
		<td><fmt:formatDate value="${vo.writeDate }" pattern="yyyy-MM-dd"/></td>
		<td>${vo.hit }</td>
	</tr>
	
	</c:forEach>
</table>
<br>
<a href="write.do">글쓰기</a>
</body>
</html>