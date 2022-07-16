<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반게시판 > 글쓰기</title>
</head>
<body>
	<h2>일반게시판 > 글쓰기</h2>
	<form action="write.do" method="post" enctype="multipart/form-data" >
		<table>
			<tr>
				<th>제목</th>
				<td><input name="title" /></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="5" cols="80" style="width:600px;" name="content"></textarea></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input name="writer" /></td>
			</tr>
			<tr>
				<th>파일 업로드</th>
				<td><input type="file" name="uploadFile" multiple="multiple" >
			</tr>
			<tr>
				<td colspan="2">
				<button>쓰기</button> 
				<button type="reset">새로입력</button> 
				<button type="button" onclick="history.back()">취소</button> 
				
				</td>
			</tr>

		</table>
	</form>
</body>
</html>