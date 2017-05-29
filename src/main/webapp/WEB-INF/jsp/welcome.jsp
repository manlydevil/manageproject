<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<jsp:include page="header.jsp"/>
<hr width="90%" size="15px" align="center" />
<table align="center">
<tr align="center" style="vertical-align: bottom;">
	<td>
		<div ><a href = "${pageContext.servletContext.contextPath}/Login/Student">
	<img alt="logo" src="<c:url value="/resources/images/singin_1.jpg"/>"/>
		</a></div>
		</td>
	<td align="left" >
	<div> <a href = "${pageContext.servletContext.contextPath}/ListReferenceProject">
	<img alt="sologan" src="<c:url value="/resources/images/project.jpg"/>"/></a></div>
	</td>
</tr>
</table>
</body>
</html>