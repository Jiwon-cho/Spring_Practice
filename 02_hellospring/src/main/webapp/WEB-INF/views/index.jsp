<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<c:set var="path" value="${pageContext.request.contextPath }"/>  
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="안녕 스프링"/>
</jsp:include>

<section id="content">
	<img src="${path }/resources/images/logo-spring.png" id="center-image"
	alt="스프링로고">
</section>
		
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>








