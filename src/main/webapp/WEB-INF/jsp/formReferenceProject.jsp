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
    <div class="content-wrapper">
         <div class="container">
        <div class="row pad-botm">
            <div class="col-md-12">      
            <form:form modelAttribute="projectInfor" action="${pageContext.servletContext.contextPath}/ListReferenceProject/Search" method="POST">
	                <input type="hidden" name="ssKey" value="${ssKey}">
            	<h4 class="header-line"><spring:message code="SEARCH"/> </h4>
	                <table style="width: 100%">
	                <tr class="form-group">
		                <td width="25%">
							<label><spring:message code="PROJECT_NAME"/></label>
							<form:input cssClass="form-control form-search" path="projectName"/>
						</td>
						<td width="25%">
							<label><spring:message code="KEY_WORD"/></label>
							<form:input cssClass="form-control form-search" path="keyWord"/> 
						</td>
						<td width="25%">
							<label><spring:message code="INSTRUCTOR_TEACHER"/></label>
							<form:input cssClass="form-control form-search" path="instructorTeacherName"/> 
						</td>
						<td width="25%">
					</tr>
					<tr class="form-group"><td>
						<label><spring:message code="TERM"/></label>
						<form:select cssClass="form-control" cssStyle="display : block" items="${lstTerm}" path="termID" itemLabel="termNumber" itemValue="termID" />
					</td>
					<td>
						<label><spring:message code="EDU_PROGRAM"/></label>
						<form:select cssClass="form-control" path="eduProgramID" items="${lstEduProgram}" itemLabel="eduProgramName" itemValue="eduProgramID" />
					</td>
					
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
                            <display:table list="${listProject}" id="project"  class="table table-striped table-bordered table-hover"  >
                            	<display:column property="projectID" titleKey="ID" />
                            	<display:column property="projectName" titleKey="PROJECT_NAME" />
                            	<display:column property="studentName" titleKey="STUDENT_NAME"/>
                            	<display:column property="eduProgram" titleKey="EDU_PROGRAM" />
                            	<display:column property="instructorTeacher" titleKey="INSTRUCTOR_TEACHER" />
                            	<display:column property="reviewTeacher" titleKey="REVIEW_TEACHER" />
                            	<display:column titleKey="DETAILS" href="${pageContext.servletContext.contextPath}/ProjectDetails" paramId="projectID" paramProperty="projectID" value="Xem" />       	
                            	<display:column titleKey="SOURCE" > <c:if test="${not empty project.sourceLink}" > <a href="${project.sourceLink }" >Link </a> </c:if> </display:column>      	            		
                            	<display:column titleKey="DESCRIPTION" > <c:if test="${not empty project.descriptionLink}" > <a href="${project.descriptionLink }">Link</a> </c:if> </display:column>
                            </display:table>
                            <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                            <c:forEach items="${listPaging}" var="num" varStatus="loop">
	                            <ul class="pagination">
									<c:if test="${loop.first && num != 1}">
									<li class="paginate_button previous " aria-controls="dataTables-example" tabindex="0" id="dataTable-example_previous">
		                            	<a href="javascript:clickPagingReferPro(${currentPage - 1})"> Previous </a>
		                            </li>
									</c:if>
									<c:if test="${currentPage != num}">
										<li class="paginate_button" aria-controls="dataTables-example" tabindex="0">
		                            	<a href="javascript:clickPagingReferPro(${num})"> ${num} </a>
		                            </li>
									</c:if>
									<c:if test="${currentPage == num}">
										<li class="paginate_button disabled" aria-controls="dataTables-example" tabindex="0">
			                            	<a href="javascript:clickPagingReferPro(${num})"> ${num} </a>
									</c:if>
									<c:if test="${loop.last && num < totalPages}">
										<li class="paginate_button next" aria-controls="dataTables-example" tabindex="0">
			                            	<a href="javascript:clickPagingReferPro(${currentPage + 1})"> Next </a>
			                            </li>
									</c:if>
								</ul>
							</c:forEach>
                            </div>
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
