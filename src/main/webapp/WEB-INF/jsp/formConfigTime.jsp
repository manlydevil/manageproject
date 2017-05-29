<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  	<jsp:include page="header.jsp"/>	
  	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />"></script>
  	<script type="text/javascript" src="<c:url value="/resources/js/datePicker.js" />"></script>
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
                                <a href="" class="dropdown-toggle menu-top-active"  id="ddlmenuItem" data-toggle="dropdown"><spring:message code="MN_MANA_CONFIG" /> <i class="fa fa-angle-down"></i></a>
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
		<div class="col-md-6 col-sm-6 col-xs-12" style="width: 40%"	align="center">
			<div class="panel panel-info">
				<div class="panel-heading">
					<spring:message code="UPDATE_CONFIG_TIME" />
				</div>
				<div class="panel-body">
				<form:form modelAttribute="configTime" method="POST" action="${pageContext.servletContext.contextPath}/UpdateConfig.do">
				<form:hidden path="configTimeID"/>
				<table>
					<tr class="form-group">
							<td width="40%"><label ><spring:message code="EDU_PROGRAM" /></label></td>
							<td><form:errors path="termID" cssClass="err_msg"/>
							<form:select cssClass="btn btn-default dropdown-toggle" items="${listTerm}" itemLabel="termNumber" itemValue="termID" path="termID"/></td>
						</tr>
						<tr class="form-group">
							<td><label ><spring:message code="INSTRUCTOR_TEACHER" /></label></td>
							<td><form:errors path="eduProgramID" cssClass="err_msg"/>
							<form:select cssClass="btn btn-default dropdown-toggle" items="${listEduProgram}" itemLabel="eduProgramName" itemValue="eduProgramID" path="eduProgramID"/></td>
						</tr>
						<tr class="form-group">
							<td><label ><spring:message code="PASSWORD" /></label></td>
							<td><form:errors path="password" cssClass="err_msg"/>
							<form:password cssClass="form-control" path="password"/></td>
						</tr>
						<tr>
							<td></td>
							<td><form:errors path="startDate" cssClass="err_msg"/>
							<form:input cssClass="form-control" path="startDate"/></td>
						</tr>
						<tr>
							<td><label ><spring:message code="TIME" /></label></td>
							<td align="left"><spring:message code="TO" /></td>
						</tr>
						<tr class="form-group">
							<td></td>
							<td><form:errors path="endDate" cssClass="err_msg"/>
							<form:input cssClass="form-control" path="endDate"/></td>
						</tr>
						<tr class="form-group">
							<td><label ><spring:message code="REVIEW_DATE" /></label></td>
							<td><form:errors path="reviewDate" cssClass="err_msg"/>
							<form:input cssClass="form-control" path="reviewDate"/></td>
						</tr>
						<tr class="form-group">
							<td align="right"><input type="submit" class="btn btn-info" name="btn_confirm" value='<spring:message code="BTN_CONFIRM" />'></td>
							<td align="center"><input type="button" class="btn btn-info" name="btn_cancel" onclick="location.href='${pageContext.servletContext.contextPath}/ListConfigTime.do'" value='<spring:message code="BTN_CANCEL" />'></td>
						</tr>
				</table>
				</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>