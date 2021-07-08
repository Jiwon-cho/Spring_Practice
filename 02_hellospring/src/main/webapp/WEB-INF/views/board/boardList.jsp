<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<c:set var="path" value="${pageContext.request.contextPath }"/>  
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value=""/>
</jsp:include>

<section id="content">
 <p>총 ${totalContents }건의 게시물이 있습니다.</p>
        
        <table id="tbl-board" class="table table-striped table-hover">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>첨부파일</th>
                <th>조회수</th>
            </tr>
            <c:forEach var="b" items="${list }">
            	<tr>
            		<td class="cols">${b.boardNo }</td>
            		<td class="cols">${b.boardTitle }</td>
            		<td class="cols">${b.boardWriter }</td>
            		<td class="cols">${b.boardDate }</td>
            		<td class="cols">${b.attachments.size() }</td>
            		<td class="cols">${b.boardCount }</td>
            		
            	</tr>
            </c:forEach>
            </table>
        <div id="pagebar-container">
        	${pageBar }
        </div>
</section>
		
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>








