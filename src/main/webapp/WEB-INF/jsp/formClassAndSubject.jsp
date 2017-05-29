<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
    <%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="header.jsp"></jsp:include>
	<script type="text/javascript" src="<c:url value="/resources/js/formClass.js" />"></script>
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
                                <a href="" class="dropdown-toggle"  id="ddlmenuItem" data-toggle="dropdown"><spring:message code="MN_MANA_PROJECT" /> <i class="fa fa-angle-down"></i></a>
                                <ul class="dropdown-menu" role="menu" aria-labelledby="ddlmenuItem">
                                    <li role="presentation"><a role="menuitem" tabindex="-1" href="${pageContext.servletContext.contextPath}/ListProject.do"><spring:message code="LIST_PROJECT" /></a></li>
                                    <li role="presentation"><a role="menuitem" tabindex="-1" href="${pageContext.servletContext.contextPath}/UploadProject.do"><spring:message code="NEW_PROJECT" /></a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="" class="dropdown-toggle menu-top-active"  id="ddlmenuItem" data-toggle="dropdown"><spring:message code="MN_COMMON" /> <i class="fa fa-angle-down"></i></a>
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
             <div class="row">
            <div class="col-md-6 col-sm-6 col-xs-12">
               <div class="panel panel-info">
                        <div class="panel-heading">
                           Lớp
                        </div>
                        <div class="panel-body">
                            <form:form modelAttribute="classInfor" action="${pageContext.servletContext.contextPath}/AddClass.do" id="mainClass" method="POST" >
                             <form:hidden path="classID"/>
                             <table style="width: 100%">
                             <tr class="form-group">
				                <td width="35%" align="left">
				                	<label><spring:message code="ID"/></label>
				                	</td>
					                <td width="65%" align="left">
					                	<form:errors cssClass="err_msg" path="classID"/>
										<c:if test="${classInfor.classID == 0}"><div id="id"><spring:message code="ADD"/></div></c:if>
										<c:if test="${classInfor.classID != 0}"><c:out value="${classInfor.classID}"/></c:if>
									</td>
								</tr>
				                <tr class="form-group">
				                <td width="35%" align="left">
				                	<label><spring:message code="CLASS_NAME"/></label>
				                	</td>
				                	
					                <td width="65%" align="left">
					                	<form:errors cssClass="err_msg" path="className"/>
										<form:input cssClass="form-control form-search" path="className"/>
									</td>
								</tr>
								<tr>
									<td align="center"><input type="submit" class="btn btn-info" value="<spring:message code="BTN_CONFIRM" />" /> </td>
									<td align="right"><input type="button" class="btn btn-info" onclick="javascript:refreshClassTB();" value="<spring:message code="REFRESH" />" /> </td>
								</tr>
							</table>
                                    </form:form>
                                     <!-- Advanced Tables -->
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="table-responsive">
                            <div class="err_msg"><c:out value="${class_errmsg}"></c:out></div>
                            <display:table list="${listClass}" pagesize="2" requestURI="${pageContext.servletContext.contextPath}/ClassAndSubject.do" class="table table-striped table-bordered table-hover"  >
                            	<display:column property="classID" titleKey="CLASS_ID" />
                            	<display:column property="className" titleKey="CLASS_NAME" />            
                            	<display:column titleKey="UPDATE" href="javascript:setTBClass('#')" paramId="id" paramProperty="classID" value="Sửa" />
                            	<display:column titleKey="DELETE" href="javascript:deleteClass('#')" paramId="classID" paramProperty="classID"  value="Xóa"/>
                            </display:table>
                            </div>                            
                        </div>
                    </div>
                    <!--End Advanced Tables -->
                            </div>
                        </div>
                            </div>
<div class="col-md-6 col-sm-6 col-xs-12">
               <div class="panel panel-danger">
                        <div class="panel-heading">
                           Môn học
                        </div>
                        <div class="panel-body">
                           <form:form modelAttribute="subjectInfor" action="${pageContext.servletContext.contextPath}/AddSubject.do" id="mainSubject" method="POST" >
                             <form:hidden path="subjectID"/>
                             <table style="width: 100%">
                             <tr class="form-group">
				                <td align="left" width="10%">
				                	<label><spring:message code="ID"/></label>
				                	</td>
				                	<td align="left">	
				                		<form:errors cssClass="err_msg" path="subjectID"/>									
										<c:if test="${subjectInfor.subjectID == 0}"><div id="sid"><spring:message code="ADD"/></div></c:if>
										<c:if test="${subjectInfor.subjectID != 0}"><c:out value="${subjectInfor.subjectID}"/></c:if>
									</td>
								</tr>
				                <tr class="form-group">
				                <td align="left" width="10%">
				                	<label><spring:message code="SUBJECT_CODE"/></label>
				                	</td>
				                	<td align="left">			
				                		<form:errors cssClass="err_msg" path="subjectCode"/>							
										<form:input cssClass="form-control form-search" path="subjectCode"/>
									</td>
								</tr>
								<tr>
									<td  align="left">
				                	<label><spring:message code="SUBJECT_NAME"/></label>
				                	</td>
					                <td align="left">	
					                	<form:errors cssClass="err_msg" path="subjectName"/>									
										<form:input cssClass="form-control form-search" path="subjectName"/>
									</td>
									<td/>													
								</tr>
								<tr>
									<td  align="center"><input type="submit" class="btn btn-info" value="<spring:message code="BTN_CONFIRM"/>" /> </td>
									<td align="right"><input type="button" class="btn btn-info" onclick="javascript:refreshSubjectTB();" value="<spring:message code="REFRESH" />" /> </td>
								</tr>
							</table>
                            </form:form>                                                                
                                    <!-- Advanced Tables -->
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="table-responsive">
                            <div class="err_msg"><c:out value="${sub_errmsg}"></c:out></div>
                            <display:table list="${listSubject}" pagesize="2" requestURI="${pageContext.servletContext.contextPath}/ClassAndSubject.do" class="table table-striped table-bordered table-hover"  >
                            	<display:column property="subjectID" titleKey="ID" />
                            	<display:column property="subjectCode" titleKey="SUBJECT_CODE" />
                            	<display:column property="subjectName" titleKey="SUBJECT_NAME" />            
                            	<display:column titleKey="UPDATE" href="javascript:setTBSubject('#')" paramId="id" paramProperty="subjectID" value="Sửa" />
                            	<display:column titleKey="DELETE" href="javascript:deleteSubject('#')" paramId="studentID" paramProperty="subjectID"  value="Xóa"/>
                            </display:table>
                            </div>                            
                        </div>
                    </div>
                    <!--End Advanced Tables -->
                            </div>
                        </div>
                            </div>
        </div>
      </div>
      </div>
      <input type="hidden" id="msg_delete" value="<spring:message code="CONFIRM_DELETE" />"/>
      <input type="hidden" id="add" value="<spring:message code="ADD" />"/>
</body>
</html>