<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반게시판 > 글수정</title>
</head>
<body>
	<h2>일반게시판 > 글수정</h2>
	<form action="update.do" method="post" enctype="multipart/form-data">
		<div>
			<label>번호</label> <input name="no" readonly="readonly"
				value="${vo.no }" />
		</div>
		<div>
			<label>제목</label> <input name="title" value="${vo.title }" />
		</div>
		<div>
			<label>이미지</label>
			<c:forEach items="${fileNameList }" var="fileVO">
			
				<img alt="" src="${fileVO.fileName }" width="100px">
				<p>${fileVO.orgFileName }[${fileVO.fileSize}]</p>

				<c:if test="${!empty fileVO.fileName }">
				<input name="del" value="${fileVO.fileName }" type="hidden">
				</c:if>
			
			</c:forEach>

		</div>
		<div>
			<label>내용</label>
			<textarea rows="5" style="width: 600px;" name="content">${vo.content }</textarea>
		</div>
		<div>
			<label>이미지수정</label> 
			<input type="file" name="uploadFile" multiple="multiple">
		</div>

		<div>
			<label>작성자</label> <input name="writer" value="${vo.writer }"
				readonly="readonly" />
		</div>
		<div>
			<button>수정</button>
			<button type="reset">새로입력</button>
			<button type="button" onclick="history.back()">취소</button>
		</div>

	</form>
</body>
</html>