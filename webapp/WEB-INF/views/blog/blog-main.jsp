<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/blog_header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${vo2.title }</h4>
					<p>${vo2.content  }
					<p>
				</div>


				<ul class="blog-list">
					<c:forEach items="${postvo }" var="vo" varStatus="status">
						<li><a href="${pageContext.request.contextPath}/${authUser.usersId }?pno=${vo.pno}">${vo.title}</a><span>${vo.regDate}</span></li>
					</c:forEach>

				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}/${vo[0].logo }">
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigator.jsp" />
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>