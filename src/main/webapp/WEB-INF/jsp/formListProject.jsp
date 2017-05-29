<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
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
    <div class="content-wrapper">
         <div class="container">
        <div class="row pad-botm">
            <div class="col-md-12">      
            <form:form modelAttribute="inforSearch" action="${pageContext.servletContext.contextPath}/ListProject/Search.do" method="POST" id="formListProject">
	                <input type="hidden" name="ssKey" value="${ssKey}">
	                <input type="hidden" name="projectID" id="projectID" value=""/>
            <a href="${pageContext.servletContext.contextPath}/UploadProject.do?ssKey=${ssKey}" class="btn btn-default btn-sm" > <spring:message code="NEW_PROJECT" /> </a>
		    <a href="${pageContext.servletContext.contextPath}/ImportProject.do" class="btn btn-default btn-sm" > <spring:message code="IMPORT" /></a>
		    <c:if test="${not empty listProject}">
		    <a href="javascript:downloadListProject(); " class="btn btn-default btn-sm" > <spring:message code="EXPORT" /></a>
		    </c:if>      
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
                            <div class="err_msg"><c:out value="${message}" /></div>
                            <display:table list="${listProject}" requestURI="${pageContext.servletContext.contextPath}/ListProject.do" id="project"  class="table table-striped table-bordered table-hover"  >
                            	<display:setProperty name="basic.msg.empty_list" value="Không có bản ghi"/>
                            	<display:column property="projectID" titleKey="ID" />
                            	<display:column property="projectName" titleKey="PROJECT_NAME" />
                            	<display:column property="submitDate" titleKey="SUBMIT_DATE" sortable="true" />
                            	<display:column property="studentName" titleKey="STUDENT_NAME" sortable="true" />
                            	<display:column property="instructorTeacher" titleKey="INSTRUCTOR_TEACHER" />
                            	<display:column property="reviewTeacher" titleKey="REVIEW_TEACHER" />
                            	<display:column property="eduProgram" titleKey="EDU_PROGRAM" />
                            	<display:column property="term" titleKey="TERM" sortable="true"  />
                            	<display:column property="score" titleKey="SCORE" sortable="true"  />
                            	<display:column property="isPass" titleKey="PASS" sortable="true"  />
                            	<display:column titleKey="TEACHER" style="text-align: center;"> 
                            	<c:if test='${project.teacherPublic eq "1"}'> <img alt="" src="<c:url value="/resources/images/tick.png" />"> </c:if>
                            	<c:if test='${project.teacherPublic ne "1"}'> <img alt="" src="<c:url value="/resources/images/cross.png" />"> </c:if>  
                            	</display:column>
                            	<display:column titleKey="STUDENT" style="text-align: center;"> 
                            	<c:if test='${project.studentPublic eq "1"}'> <img alt="" src="<c:url value="/resources/images/tick.png" />"> </c:if>
                            	<c:if test='${project.studentPublic ne "1"}'> <img alt="" src="<c:url value="/resources/images/cross.png" />"> </c:if>  
                            	</display:column>
                            	<display:column titleKey="SOURCE"><c:if test='${project.sourceLink ne ""}'> <a href='${project.sourceLink }'>Link</a></c:if> </display:column>                       		
                            	<display:column titleKey="DESCRIPTION"><c:if test='${project.descriptionLink ne ""}'> <a  href='${project.descriptionLink }' >Link</a> </c:if> </display:column>
                            	<display:column titleKey="UPDATE" href="${pageContext.servletContext.contextPath}/UpdateProject/${ssKey}" paramId="id" paramProperty="projectID" value="Sửa" />
                            	<display:column titleKey="DELETE" href="javascript:deleteProject('#')" paramId="ID" paramProperty="projectID"  value="Xóa"/>
                            </display:table>
                            <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                            <c:forEach items="${listPaging}" var="num" varStatus="loop">
	                            <ul class="pagination">
									<c:if test="${loop.first && num != 1}">
									<li class="paginate_button previous " aria-controls="dataTables-example" tabindex="0" id="dataTable-example_previous">
		                            	<a href="javascript:clickPagingPro(${inforSearch.currentPage - 1})"> Previous </a>
		                            </li>
									</c:if>
									<c:if test="${inforSearch.currentPage != num}">
										<li class="paginate_button" aria-controls="dataTables-example" tabindex="0">
		                            	<a href="javascript:clickPagingPro(${num})"> ${num} </a>
		                            </li>
									</c:if>
									<c:if test="${inforSearch.currentPage == num}">
										<li class="paginate_button disabled" aria-controls="dataTables-example" tabindex="0">
			                            	<a href=""> ${num} </a>
									</c:if>
									<c:if test="${loop.last && num < totalPages}">
										<li class="paginate_button next" aria-controls="dataTables-example" tabindex="0">
			                            	<a href="javascript:clickPagingPro(${inforSearch.currentPage + 1})"> Next </a>
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
