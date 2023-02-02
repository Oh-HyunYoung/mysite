<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath }/board" method="post">
					<input type="text" id="kwd" name="kwd" value="${searchText}"> 
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<tr>
						<c:set var="count" value="${fn:length(index) }" />
						<c:forEach items="${index }" var="vo" varStatus="status">
									<tr>
										<td>${count-status.index }</td>
										
										<td style="text-align:left; padding-left:${vo.depth*15 }px">
										<c:if test="${vo.depth>0 }">
										<img src="${pageContext.request.contextPath }/assets/images/reply.png">
										</c:if>
										<a href="${pageContext.request.contextPath }/board/view/${vo.no }">${vo.title }</a></td>
										
										<td>${vo.name }</td>
										<td>${vo.hit }</td>
										<td>${vo.reg_date }</td>
										<td>
										<c:if test="${vo.user_no == authUser.no }">
										<a href="${pageContext.request.contextPath }/board/delete/${vo.no }" class="del">삭제</a></td>
										</c:if>
									</tr>
						</c:forEach>
						</tr>
					</table>
			
	
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
					<li>
					<c:if test="${page>1 }">
					<a href="${pageContext.request.contextPath }/board?page=${page-1}&kwd=${searchText}">◀</a></c:if>
					</li>
						<c:forEach var="i" begin="1" end="${count/10 +1}" step="1">
							<li><a href="${pageContext.request.contextPath }/board/page=${i}&kwd=${searchText}">${i }</a></li>
						</c:forEach>
						<li>
						<c:if test="${page<count/10}">
						<a href="${pageContext.request.contextPath }/board?page=${page+1}&kwd=${searchText}">▶</a></c:if>
						</li>
	
					</ul>
				</div>
				<!-- pager 추가 -->

				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board/write/-1" id="new-book">글쓰기</a>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>