<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="<c:url value="/resources/js/common.js" />"></script>
<title><spring:message code="TITLE_ERROR" /></title>
<jsp:include page="header.jsp" />
</head>
<body>
<form:form action="${pageContext.servletContext.contextPath}/logout" method="GET">
	<hr width="90%" align="center" size="10px"/>
<table width="100%" class="tbl_display">
	<tr >
		<td class="err_msg" align="center">
			<c:out value="${errorMsg}" />
		</td>			
	</tr>
</table>
<div align="center"><input type="submit" class="btn_02" value="<spring:message code="BTN_CONFIRM" />"/></div>
</form:form>
</body>
</html>