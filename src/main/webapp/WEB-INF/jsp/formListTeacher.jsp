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
                                <a href="" class="dropdown-toggle"  id="ddlmenuItem" data-toggle="dropdown"><spring:message code="MN_MANA_STUDENT" /> <i class="fa fa-angle-down"></i></a>
                                <ul class="dropdown-menu" role="menu" aria-labelledby="ddlmenuItem">
                                    <li role="presentation"><a role="menuitem" tabindex="-1" href="${pageContext.servletContext.contextPath}/ListStudent.do"><spring:message code="LIST_STUDENT" /></a></li>
                                    <li role="presentation"><a role="menuitem" tabindex="-1" href="${pageContext.servletContext.contextPath}/InsertStudent.do"><spring:message code="BTN_ADD" /></a></li>
                                </ul>
                            </li>   
                            <li>
                                <a href="" class="dropdown-toggle menu-top-active"  id="ddlmenuItem" data-toggle="dropdown"><spring:message code="MN_MANA_TEACHER" /> <i class="fa fa-angle-down"></i></a>
                                <ul class="dropdown-menu" role="menu" aria-labelledby="ddlmenuItem">
                                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#"><spring:message code="LIST_TEACHER" /></a></li>
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
            <form:form modelAttribute="inforSearch" sort="list" action="${pageContext.servletContext.contextPath}/ListTeacher/Search.do" method="POST" id="formListTeacher">
	                <input type="hidden" name="ssKey" value="${ssKey}">
	                <input type="hidden" id="teacherID" name="teacherID"/>
	                 <h4 class="header-line"><spring:message code="SEARCH_TEACHER"/> </h4>
	                <table style="width: 50%">
	                <tr class="form-group">
		                <td>
							<label><spring:message code="TEACHER_NAME"/></label>
						</td><td>
							<form:input cssClass="form-control form-search" path="teacherName"/>
						</td>
					</tr>
					<tr class="form-group">
		                <td/>
		                <td>
							<input type="submit" class="btn btn-info" name="btn_search" value='<spring:message code="SEARCH" />'>
						</td>
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
                            <div class="err_msg"><c:out value="${error}"/></div>
                            <display:table list="${listTeacher}" pagesize="5"  requestURI="${pageContext.servletContext.contextPath}/ListTeacher.do" id="teacher"  class="table table-striped table-bordered table-hover"  >
                            	<display:setProperty name="basic.msg.empty_list" value="Không có bản ghi"/>
                            	<display:column property="teacherID" titleKey="TEACHER_ID" sortable="true" />
                            	<display:column property="teacherName" titleKey="TEACHER_NAME" sortable="true" />
                            	<display:column property="degreeName" titleKey="DEGREE" />
                            	<display:column property="address" titleKey="ADDRESS" />
                            	<display:column property="birthday" titleKey="BIRTHDAY" sortable="true" />                          
                            	<display:column titleKey="UPDATE" href="${pageContext.servletContext.contextPath}/UpdateTeacher/${ssKey}" paramId="id" paramProperty="teacherID" value="Sửa" />
                            	<display:column titleKey="DELETE" href="javascript:deleteTeacher('#')" paramId="id" paramProperty="teacherID"  value="Xóa"/>
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
