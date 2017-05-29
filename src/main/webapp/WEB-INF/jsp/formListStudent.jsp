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
    <div class="content-wrapper">
         <div class="container">
        <div class="row pad-botm">
            <div class="col-md-12">      
            <form:form modelAttribute="inforSearch" action="${pageContext.servletContext.contextPath}/ListStudent/Search.do" method="POST" id="formListStudent">
	                <input type="hidden" name="ssKey" value="${ssKey}">
	                
            <a href="${pageContext.servletContext.contextPath}/InsertStudent.do?ssKey=${ssKey}" class="btn btn-default btn-sm" > <spring:message code="BTN_ADD" /> </a>
		    <a href="${pageContext.servletContext.contextPath}/ImportStudent.do" class="btn btn-default btn-sm" > <spring:message code="IMPORT" /></a>
		    <c:if test="${not empty lstStudent}">
		    <a href="javascript:downloadListStudent(); " class="btn btn-default btn-sm" > <spring:message code="EXPORT" /></a>
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
                            <div class="err_msg"><c:out value="${message}"/></div>
                            <display:table list="${lstStudent}" requestURI="${pageContext.servletContext.contextPath}/ListStudent.do" id="student"  class="table table-striped table-bordered table-hover"  >
                            	<display:column property="studentNumber" titleKey="STUDENT_ID" sortable="true" />
                            	<display:column property="name" titleKey="FULLNAME" sortable="true" />
                            	<display:column property="email" titleKey="EMAIL" />
                            	<display:column property="phone" titleKey="PHONENUMBER" />
                            	<display:column property="term" titleKey="TERM" sortable="true" />
                            	<display:column property="eduProgram" titleKey="EDU_PROGRAM" sortable="true" />
                            	<display:column property="workPlace" titleKey="WORKPLACE"  />
                            	<display:column property="office" titleKey="OFFICE" />
                            	<display:column titleKey="SUBMITTED" property="submit" sortable="true" class="err_msg" style="text-align:center;"/>
                            	<display:column titleKey="UPDATE" href="${pageContext.servletContext.contextPath}/Update/${ssKey}" paramId="id" paramProperty="studentID" value="Sửa" />
                            	<display:column titleKey="DELETE" href="javascript:deleteStudent('#')" paramId="id" paramProperty="studentID"  value="Xóa"/>
                            </display:table>
                            <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                            <c:forEach items="${listPaging}" var="num" varStatus="loop">
	                            <ul class="pagination">
									<c:if test="${loop.first && num != 1}">
									<li class="paginate_button previous " aria-controls="dataTables-example" tabindex="0" id="dataTable-example_previous">
		                            	<a href="javascript:clickPaging(${inforSearch.currentPage - 1})"> Previous </a>
		                            </li>
									</c:if>
									<c:if test="${inforSearch.currentPage != num}">
										<li class="paginate_button" aria-controls="dataTables-example" tabindex="0">
		                            	<a href="javascript:clickPaging(${num})"> ${num} </a>
		                            </li>
									</c:if>
									<c:if test="${inforSearch.currentPage == num}">
										<li class="paginate_button disabled" aria-controls="dataTables-example" tabindex="0">
			                            	<a href=""> ${num} </a></li>
									</c:if>
									<c:if test="${loop.last && num < totalPages}">
										<li class="paginate_button next" aria-controls="dataTables-example" tabindex="0">
			                            	<a href="javascript:clickPaging(${inforSearch.currentPage + 1})"> Next </a>
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
