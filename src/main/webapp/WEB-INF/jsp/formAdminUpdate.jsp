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
                                <a href="" class="dropdown-toggle menu-top-active"  id="ddlmenuItem" data-toggle="dropdown"><spring:message code="MN_MANA_STUDENT" /> <i class="fa fa-angle-down"></i></a>
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
                                <a href="" class="dropdown-toggle"  id="ddlmenuItem" data-toggle="dropdown"><spring:message code="MN_MANA_PROJECT" /> <i class="fa fa-angle-down"></i></a>
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
	<div class="col-md-6 col-sm-6 col-xs-12" style="width: 40%" align="center">
		<div class="panel panel-info">
			<div class="panel-heading"><spring:message code="UPDATE_INFOR" /></div>
			<div class="panel-body">
				<form:form modelAttribute="studentInfor" method="POST" action="${pageContext.servletContext.contextPath}/UpdateInfor.do">
				<input type="hidden" name="ssKey" value="${ssKey}">
				<form:hidden path="studentID"/>
				<table style="width: 100%">
						<tr class="form-group" >
							<td width="35%"><label ><spring:message code="STUDENT_ID" /></label></td>
							<td width="50%"><form:errors path="studentNumber" cssClass="err_msg" /><form:input cssClass="form-control" path="studentNumber"/></td>
						</tr>
						<tr class="form-group">
							<td><label ><spring:message code="STUDENT_NAME" /></label></td>
							<td><form:errors path="name" cssClass="err_msg" /><form:input cssClass="form-control" path="name"/></td>
						</tr>
						<tr class="form-group">
							<td><label ><spring:message code="EMAIL" /></label></td>
							<td><form:errors path="email" cssClass="err_msg" /><form:input cssClass="form-control" path="email"/></td>
						</tr>
						<tr class="form-group">
							<td><label ><spring:message code="PHONENUMBER" /></label></td>
							<td><form:input cssClass="form-control" path="phone"/></td>
						</tr>
						<tr class="form-group">
							<td><label ><spring:message code="WORKPLACE" /></label></td>
							<td><form:input cssClass="form-control" path="workPlace"/></td>
						</tr>
						<tr class="form-group">
							<td><label ><spring:message code="OFFICE" /></label></td>
							<td><form:input cssClass="form-control" path="office"/></td>
						</tr>
						<tr class="form-group">
							<td><label ><spring:message code="CLASS" /></label></td>
						 <td><form:errors path="classID" cssClass="err_msg" /><form:select items="${lstClass}" itemLabel="className" itemValue="classID" cssClass="btn btn-default dropdown-toggle" path="classID"/>	</td>
						</tr>
						<tr class="form-group">
							<td><label ><spring:message code="TERM" /></label></td>
							<td><div style="display: block;"><form:errors path="termID" cssClass="err_msg" /></div><form:select items="${lstTerm}" itemLabel="termNumber" itemValue="termID" cssClass="btn btn-default dropdown-toggle" path="termID"/></td>
						</tr>
						<tr class="form-group">
							<td><label ><spring:message code="SUBJECT" /></label></td>
							<td><form:errors path="subjectID" cssClass="err_msg" /><form:select items="${lstSubject}" itemLabel="subjectName" itemValue="subjectID" cssClass="btn btn-default dropdown-toggle" path="subjectID"/></td>
						</tr>
						<tr class="form-group">
							<td><label ><spring:message code="EDU_PROGRAM" /></label></td>
							<td><form:errors path="eduProgramID" cssClass="err_msg" /><form:select items="${lstEduProgram}" itemLabel="eduProgramName" itemValue="eduProgramID" cssClass="btn btn-default dropdown-toggle" path="eduProgramID"/></td>
						</tr>
						<tr class="form-group">
							<td><label ><spring:message code="INSTRUCTOR_TEACHER" /></label></td>
							<td><div style="display: block;"><form:errors path="instructorTeacherID" cssClass="err_msg" /></div><form:select items="${lstTeacher}" itemLabel="teacherName" itemValue="teacherID" cssClass="btn btn-default dropdown-toggle" path="instructorTeacherID"/></td>
						</tr>
						<tr class="form-group">
							<td><label ><spring:message code="REVIEW_TEACHER" /></label></td>
							<td><div style="display: block;"><form:errors path="reviewTeacherID" cssClass="err_msg" /></div>
							<form:select items="${lstTeacher}" itemLabel="teacherName" itemValue="teacherID" cssClass="btn btn-default dropdown-toggle" path="reviewTeacherID"/></td>
						</tr>
						<tr align="center" class="form-group">
							<td><input class="btn btn-info" type="submit" value="<spring:message code="BTN_CONFIRM" />" /> </td>
							<td><input class="btn btn-info" type="button" onclick="location.href = '${pageContext.servletContext.contextPath}/ListStudent/Back.do?ssKey=${ssKey}'" value="<spring:message code="BTN_BACK" />" /></td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
	</div>
</div>
</body>
</html>