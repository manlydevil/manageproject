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
    <section class="menu-section">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="navbar-collapse collapse ">
                        <ul id="menu-top" class="nav navbar-nav navbar-right">
                        	<li>
                                <a href="" class="dropdown-toggle"  id="ddlmenuItem" data-toggle="dropdown"><spring:message code="MN_MANA_STUDENT" /> <i class="fa fa-angle-down"></i></a>
                                <ul class="dropdown-menu" role="menu" aria-labelledby="ddlmenuItem">
                                    <li role="presentation"><a role="menuitem" tabindex="-1" href="${pageContext.servletContext.contextPath}/ListStudent.do"><spring:message code="LIST_STUDENT" /></a></li>
                                    <li role="presentation"><a role="menuitem" tabindex="-1" href="${pageContext.servletContext.contextPath}/InsertStudent.do"><spring:message code="BTN_ADD" /></a></li>
                                </ul>
                            </li>   
                            <li>
                                <a href="" class="dropdown-toggle"  id="ddlmenuItem" data-toggle="dropdown"><spring:message code="MN_MANA_TEACHER" /> <i class="fa fa-angle-down"></i></a>
                                <ul class="dropdown-menu" role="menu" aria-labelledby="ddlmenuItem">
                                    <li role="presentation"><a role="menuitem" tabindex="-1" href="${pageContext.servletContext.contextPath}/ListTeacher.do"><spring:message code="LIST_TEACHER" /></a></li>
                                    <li role="presentation"><a role="menuitem" tabindex="-1" href="${pageContext.servletContext.contextPath}/AddTeacher.do"><spring:message code="ADD_TEACHER" /></a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="" class="dropdown-toggle"  id="ddlmenuItem" data-toggle="dropdown"><spring:message code="MN_MANA_CONFIG" /> <i class="fa fa-angle-down"></i></a>
                                <ul class="dropdown-menu" role="menu" aria-labelledby="ddlmenuItem">
                                    <li role="presentation"><a role="menuitem" tabindex="-1" href="${pageContext.servletContext.contextPath}/ListConfigTime.do"><spring:message code="LIST_CONFIG" /></a></li>
                                    <li role="presentation"><a role="menuitem" tabindex="-1" href="${pageContext.servletContext.contextPath}/NewConfig.do"><spring:message code="BTN_ADDCONFIG" /></a></li>
                                </ul>
                            </li>                        
                           <li>
                                <a href="" class="dropdown-toggle  menu-top-active"  id="ddlmenuItem" data-toggle="dropdown"><spring:message code="MN_MANA_PROJECT" /> <i class="fa fa-angle-down"></i></a>
                                <ul class="dropdown-menu" role="menu" aria-labelledby="ddlmenuItem">
                                    <li role="presentation"><a role="menuitem" tabindex="-1" href="${pageContext.servletContext.contextPath}/ListProject.do"><spring:message code="LIST_PROJECT" /></a></li>
                                    <li role="presentation"><a role="menuitem" tabindex="-1" href="${pageContext.servletContext.contextPath}/UploadProject.do"><spring:message code="NEW_PROJECT" /></a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="" class="dropdown-toggle"  id="ddlmenuItem" data-toggle="dropdown"><spring:message code="MN_COMMON" /> <i class="fa fa-angle-down"></i></a>
                                <ul class="dropdown-menu" role="menu" aria-labelledby="ddlmenuItem">
                                    <li role="presentation"><a role="menuitem" tabindex="-1" href="${pageContext.servletContext.contextPath}/ClassAndSubject.do"><spring:message code="MN_CLASS_SUBJECT" /></a></li>
                                    <li role="presentation"><a role="menuitem" tabindex="-1" href="${pageContext.servletContext.contextPath}/TermAndEduProgram.do"><spring:message code="MN_TERM_EDUPROGRAM" /></a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>

            </div>
        </div>
    </section>
     <!-- MENU SECTION END-->
     <div class="row">
	<div class="col-md-6 col-sm-6 col-xs-12" style="width: 50%" align="center">
		<div class="panel panel-info">
			<div class="panel-heading"><spring:message code="UPDATE_INFOR" /></div>
			<div class="panel-body">
				<form:form modelAttribute="projectInfor" enctype="multipart/form-data" method="POST" action="${pageContext.servletContext.contextPath}/UploadProject.do">
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
							<form:input cssClass="form-control" path="studentNumber"/>
							</td>
						</tr>
						<tr class="form-group">
							<td><label ><spring:message code="SCORE" /></label></td>
							<td><form:errors path="score" cssClass="err_msg" />
							<form:input cssClass="form-control" path="score"/>
							</td>
						</tr>
						<tr class="form-group">
							<td><label ><spring:message code="PASS" /></label></td>
							<td><form:checkbox path="isPass" value="1" /></td>
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
							<td><spring:message code="TEACHER" /> <form:checkbox path="teacherPublic" value="1" /><spring:message code="STUDENT" /> <form:checkbox path="studentPublic" value="1" /> </td>
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
							<td><input class="btn btn-info" type="submit" value="<spring:message code="BTN_CONFIRM" />" /> </td>
							<td><input class="btn btn-info" type="button" onclick="location.href = '${pageContext.servletContext.contextPath}/ListProject/Back.do?ssKey=${ssKey}'" value="<spring:message code="BTN_BACK" />" /></td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
	</div>
</div>
</body>
</html>