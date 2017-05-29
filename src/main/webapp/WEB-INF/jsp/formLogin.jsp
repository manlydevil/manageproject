<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="header.jsp"></jsp:include>
</head>
<body>
<hr align="center" width="90%" color="gray" size="3px"/>
<spring:message code="USERNAME" var="username"/>
<spring:message code="PASSWORD" var="password"/>
	<!-- main -->
	<div align="center">
		<h1><spring:message code="TITLE_1" /></h1>
		<h1><spring:message code="TITLE_2" /></h1>
		<div align="center">
		<div class="loginform">
               <div class="panel panel-danger">
                        <div class="panel-heading">
                           <spring:message code="LOGIN" />
                        </div>
                        <div class="panel-body">
                            <form:form role="form" modelAttribute="accountInfor" action="${AccountInfor.priority}" method="POST">                                        
                                 <div class="err_msg">
                                 	<form:errors path="*"/>
                                 </div>
                                 <div class="form-group">
                                            <form:input cssClass="form-control" path="userName" placeholder="${username}" />
                                        </div>
                                            <div class="form-group">
                                            <form:password cssClass="form-control" path="password" placeholder="${password}" />
                                        </div>                           
                                        <button type="submit" class="btn btn-danger"><spring:message code="LOGIN" /></button>
                                    </form:form>
                            </div>
                        </div>
                            </div>
                            </div>
        </div>
</body>
</html>