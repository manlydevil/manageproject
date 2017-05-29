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
                           <li>
                                <a href="" class="dropdown-toggle menu-top-active"  id="ddlmenuItem" data-toggle="dropdown"><spring:message code="LIST_ASSIGNED" /> <i class="fa fa-angle-down"></i></a>
                                <ul class="dropdown-menu" role="menu" aria-labelledby="ddlmenuItem">
                                    <li role="presentation"><a role="menuitem" tabindex="-1" href="${pageContext.servletContext.contextPath}/ListStudentInstruction.tc"><spring:message code="INSTRUCTION" /></a></li>
                                    <li role="presentation"><a role="menuitem" tabindex="-1" href="${pageContext.servletContext.contextPath}/ListStudentReview.tc"><spring:message code="REVIEW" /></a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>

            </div>
        </div>
    </section>
     <!-- MENU SECTION END-->
    <div class="content-wrapper">
         <div class="container">
        <div class="row pad-botm">
            <div class="col-md-12">      
            <form:form modelAttribute="inforSearch" action="${pageContext.servletContext.contextPath}/ListStudentAssigned/Search.do" method="POST" id="formListStudent">
	                <input type="hidden" name="ssKey" value="${ssKey}">
	                <input type="hidden" name="studentID" id="studentID" value="">
            	<h4 class="header-line"><spring:message code="SEARCH"/> </h4>
	                <table style="width: 100%">
	                <tr class="form-group">
		                <td width="25%">
							<label><spring:message code="STUDENT_ID"/></label>
							<form:input cssClass="form-control form-search" path="studentNumber"/>
						</td>
						<td width="25%"/>
						<td width="25%">
							<label><spring:message code="TERM"/></label>
						<form:select cssClass="form-control" cssStyle="display : block" items="${lstTerm}" path="termID" itemLabel="termNumber" itemValue="termID" />
						</td>
						<td width="25%">
					</tr>
					<tr class="form-group"><td>
						<label><spring:message code="STUDENT_NAME"/></label>
						<form:input cssClass="form-control form-search" path="name" />
					</td>
					<td/>
					<td>
						<label><spring:message code="EDU_PROGRAM"/></label>
						<form:select cssClass="form-control" path="eduProgramID" items="${lstEduProgram}" itemLabel="eduProgramName" itemValue="eduProgramID" />
					</td>
					<td/>
					</tr>
					<tr>
					<td align="center" >
						<input type="submit" class="btn btn-info" name="btn_search" value='<spring:message code="SEARCH" />'>
					</td>
					<td/>
					<td colspan="2" ></td>
					</tr>
					</table>
					</form:form>   
				</div>   
        </div>
            <div class="row">
                <div class="col-md-12">
                    <!-- Advanced Tables -->
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="table-responsive">
                            <div class="err_msg"><c:out value="${message}"/></div>
                            <display:table list="${listStudent}" pagesize="20"  sort="list" requestURI="${pageContext.servletContext.contextPath}/ListStudentInstruction.do" id="student"  class="table table-striped table-bordered table-hover"  >
                            	<display:setProperty name="basic.msg.empty_list" value="Không có bản ghi"/>
                            	<display:column property="studentNumber" titleKey="STUDENT_ID" sortable="true" />
                            	<display:column property="studentName" titleKey="FULLNAME" sortable="true" />
                            	<display:column property="term" titleKey="TERM" sortable="true" />
                            	<display:column property="eduProgram" titleKey="EDU_PROGRAM" sortable="true" />
                            	<display:column property="projectName" titleKey="PROJECT_NAME"  />
                            	<display:column property="submitDate" titleKey="SUBMIT_DATE" />
                            	<display:column titleKey="PUBLIC" > 
                            		<c:if test='${student.projectID ne "0"}'><input onclick="javascript:changePublic(${student.projectID}, ${student.teacherPublic});" type="checkbox" <c:if test='${student.teacherPublic eq "1" }'> checked="checked" </c:if> > </c:if>
                            	</display:column>
                            </display:table>
                            </div>
                            
                        </div>
                    </div>
                    <!--End Advanced Tables -->
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
