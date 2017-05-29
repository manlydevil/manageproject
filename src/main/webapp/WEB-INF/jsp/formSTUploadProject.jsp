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
<!-- LOGO HEADER END-->
    <section class="menu-section" >
        <div class="container" align="center">
            <div class="row">
                <div class="col-md-12">
                    <div class="navbar-collapse collapse ">
                        <ul id="menu-top" class="nav navbar-nav navbar-right">
                            <li><a href="${pageContext.servletContext.contextPath}/UpdateInformation.st"><spring:message code="UPDATE_INFOR"/> </a></li>                        
                            <li><a href="${pageContext.servletContext.contextPath}/AddProject.st" class="menu-top-active"><spring:message code="UPDATE_PRO"/></a></li>
                            <li><a href="${pageContext.servletContext.contextPath}/ChangePass.st"><spring:message code="CHANGE_PASS"/></a></li>
                            <li><a href="${pageContext.servletContext.contextPath}/Logout" ><spring:message code="LOGOUT"/></a></li>
                        </ul>
                    </div>
                </div>

            </div>
        </div>
    </section>
     <!-- MENU SECTION END-->
     <c:if test="${rsCompare}">
     	<div class="err_msg"><c:out value="Đã hết hạn nộp bài!!! ((thời gian nộp bài từ ${configBean.startDate} đến ${configBean.endDate}))"></c:out></div>
     </c:if>
     <c:if test="${rsCompare == false}" >
     <div class="row">
	<div class="col-md-6 col-sm-6 col-xs-12" style="width: 50%" align="center">
		<div class="panel panel-info">
			<div class="panel-heading"><spring:message code="UPDATE_INFOR" /></div>
			<div class="panel-body">
				<div class="err_msg"><c:out value="Thời gian nộp bài từ ${configBean.startDate} đến ${configBean.endDate}"/></div>
				<form:form modelAttribute="projectInfor" enctype="multipart/form-data" method="POST" action="${pageContext.servletContext.contextPath}/UpdatePro.st">
				<input type="hidden" name="ssKey" value="${ssKey}">
				<form:hidden path="projectID"/>
				<c:out value="${errors}" />
					<table style="width: 100%">
						<tr class="form-group" >
							<td width="15%"><label ><spring:message code="PROJECT_NAME" /></label></td>
							<td width="80%"><form:errors path="projectName" cssClass="err_msg" />
							<form:input cssClass="form-control" path="projectName"/>									
							</td>
						</tr>
						<tr class="form-group">
							<td><label ><spring:message code="KEY_WORD" /></label></td>
							<td><form:errors path="keyWord" cssClass="err_msg" />
							<form:input cssClass="form-control" path="keyWord"/>
							</td>
						</tr>
						<tr class="form-group">
							<td><label ><spring:message code="STUDENT_ID" /></label></td>
							<td><form:errors path="studentNumber" cssClass="err_msg" />
							<form:input readonly="true" cssClass="form-control" path="studentNumber"/>
							</td>
						</tr>
						<tr class="form-group">
							<td><label ><spring:message code="ZIP_FILE" /></label></td>
							<td> <input type="file" name="file"> <form:errors path="sourceLink" cssClass="err_msg" /> <form:input readonly="true" cssClass="form-control" path="sourceLink"/> </td>
						</tr>
						<tr class="form-group">
							<td><label ><spring:message code="PDF_FILE" /></label></td>
							<td> <input type="file" name="file"> <form:errors path="descriptionLink" cssClass="err_msg" /> <form:input readonly="true" cssClass="form-control" path="descriptionLink"/> </td>
						</tr>
						<tr class="form-group">
							<td><label ><spring:message code="SHARE" /></label></td>
							<td> <form:checkbox path="studentPublic" value="1" /> </td>
						</tr>
						<tr class="form-group">
							<td><label ><spring:message code="DESCRIPTION" /></label></td>
							<td><form:textarea cols="40" rows="5" cssClass="form-control" path="description" cssStyle="min-height: 150px; display : block;" /></td>
						</tr>
						<tr class="form-group">
							<td><label ><spring:message code="NOTE" /></label></td>
							<td><form:textarea cols="40" rows="5" cssClass="form-control" path="note" cssStyle="min-height: 150px; display : block;"/></td>
						</tr>
						<tr align="center" class="form-group">
							<td colspan="2"><input class="btn btn-info" type="submit" value="<spring:message code="BTN_CONFIRM" />" /> </td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
	</div>
</div>
	</c:if>
</body>
</html>