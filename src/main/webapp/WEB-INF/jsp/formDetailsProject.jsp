<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
  	<jsp:include page="header.jsp"/>
</head>
<body>
    <!-- LOGO HEADER END-->
    <section class="menu-section">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="navbar-collapse collapse ">
                        <ul id="menu-top" class="nav navbar-nav navbar-right">                                       
                           <li><a href="#" > <spring:message code="LIST_REFER_PROJECT" /></a></li>
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
			<div class="panel-heading"><spring:message code="DETAILS_PROJECT" /></div>
			<div class="panel-body">
			<form:form modelAttribute="projectInforBean" >
					<table style="width: 100%">
						<tr class="form-group" >
							<td width="15%"><label ><spring:message code="PROJECT_NAME" /></label></td>
							<td width="80%"><form:errors path="projectName" cssClass="err_msg" />
							<c:out value="${projectInforBean.projectName}"/>									
							</td>
						</tr>
						<tr class="form-group">
							<td><label ><spring:message code="KEY_WORD" /></label></td>
							<td><form:errors path="keyWord" cssClass="err_msg" />
							<c:out value="${projectInforBean.keyWord}"/>
							</td>
						</tr>
						<tr class="form-group">
							<td><label ><spring:message code="STUDENT_ID" /></label></td>
							<td><form:errors path="studentNumber" cssClass="err_msg" />
							<c:out value="${projectInforBean.studentNumber}"/>
							</td>
						</tr>
						<tr class="form-group">
							<td><label ><spring:message code="DESCRIPTION" /></label></td>
							<td style="display: block; width: 500px;"><c:out value="${projectInforBean.description}" /></td>
						</tr>
						<tr class="form-group">
							<td><label ><spring:message code="NOTE" /></label></td>
							<td style="display: block; width: 500px;"><c:out value="${projectInforBean.note}" /></td>
						</tr>
						<tr align="center" class="form-group">
							<td colspan="2" align="center"><input class="btn btn-info" type="button" onclick="location.href = '${pageContext.servletContext.contextPath}/ListReferenceProject'" value="<spring:message code="BTN_BACK" />" /></td>
						</tr>
					</table>
					</form:form>
			</div>
		</div>
	</div>
</div>
     <!-- CONTENT-WRAPPER SECTION END-->
    <section class="footer-section">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                   &copy; 2014 Yourdomain.com |<a href="http://www.binarytheme.com/" target="_blank"  > Designed by : binarytheme.com</a> 
                </div>

            </div>
        </div>
    </section>
      <!-- FOOTER SECTION END-->
    <!-- JAVASCRIPT FILES PLACED AT THE BOTTOM TO REDUCE THE LOADING TIME  -->
    <!-- CORE JQUERY  -->
    <script src="assets/js/jquery-1.10.2.js"></script>
    <!-- BOOTSTRAP SCRIPTS  -->
    <script src="assets/js/bootstrap.js"></script>
    <!-- DATATABLE SCRIPTS  -->
    <script src="assets/js/dataTables/jquery.dataTables.js"></script>
    <script src="assets/js/dataTables/dataTables.bootstrap.js"></script>
      <!-- CUSTOM SCRIPTS  -->
    <script src="assets/js/custom.js"></script>
    <input type="hidden" id="msg_delete" value="<spring:message code="CONFIRM_DELETE" />"/>
</body>
</html>
