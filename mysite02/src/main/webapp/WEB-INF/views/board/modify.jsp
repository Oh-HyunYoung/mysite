<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<% pageContext.setAttribute("newline", "\n"); %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form class="board-form" method="post" action="${pageContext.request.contextPath }/board?page=1">
					<input type="hidden" value="modify" name="a">
					<input type="hidden" value=${vo.no } name="no">
					<table class="tbl-ex">
						<tr>
							<th colspan="2">글수정</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" name="title" value="${vo.title }"></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<textarea id="content" name="content">${vo.contents }</textarea>
							</td>
						</tr>
					</table>
					<c:choose>
							<c:when test='${not empty sessionScope.authUser.no }'>
					<div class="bottom">
						<a href="${pageContext.servletContext.contextPath }/board?page=1">취소</a>
						<input type="submit" value="수정">
					</div>
					</c:when>
					<c:otherwise>
					<p>
					<div class="bottom">
						<a href="${pageContext.servletContext.contextPath }/board?page=1">취소</a>
						<p> 회원만 수정 가능합니다. 로그인 해주세요. </p>
					</div>
					</c:otherwise>
					</c:choose>
				</form>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>