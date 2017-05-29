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
	<script type="text/javascript" src="<c:url value="/resources/js/formTerm.js" />"></script>
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
                           <spring:message code="TERM"/>
                        </div>
                        <div class="panel-body">
                            <form:form modelAttribute="termInfor" action="${pageContext.servletContext.contextPath}/Term.do" id="mainTerm" method="POST" >
                             <form:hidden path="termID"/>
                             <table style="width: 100%">
                             <tr class="form-group">
				                <td width="35%" align="left">
				                	<label><spring:message code="ID"/></label>
				                	</td>
					                <td width="65%" align="left">
					                	<form:errors cssClass="err_msg" path="termID"/>
										<c:if test="${termInfor.termID == 0}"><div id="id"><spring:message code="ADD"/></div></c:if>
										<c:if test="${termInfor.termID != 0}"><c:out value="${termInfor.termID}"/></c:if>
									</td>
								</tr>
				                <tr class="form-group">
				                <td width="35%" align="left">
				                	<label><spring:message code="TERM"/></label>
				                	</td>
				                	
					                <td width="65%" align="left">
					                	<form:errors cssClass="err_msg" path="termNumber"/>
										<form:input cssClass="form-control form-search" path="termNumber"/>
									</td>
								</tr>
								<tr>
									<td align="center"><input type="submit" class="btn btn-info" value="<spring:message code="BTN_CONFIRM" />" /> </td>
									<td align="right"><input type="button" class="btn btn-info" onclick="javascript:refreshTermTB();" value="<spring:message code="REFRESH" />" /> </td>
								</tr>
							</table>
                                    </form:form>
                                     <!-- Advanced Tables -->
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="table-responsive">
                            <div class="err_msg"><c:out value="${class_errmsg}"></c:out></div>
                            <display:table list="${listTerm}" pagesize="2" requestURI="${pageContext.servletContext.contextPath}/TermAndEduProgram.do" class="table table-striped table-bordered table-hover"  >
                            	<display:column property="termID" titleKey="ID" />
                            	<display:column property="termNumber" titleKey="TERM" />            
                            	<display:column titleKey="UPDATE" href="javascript:setTBTerm('#')" paramId="id" paramProperty="termID" value="Sửa" />
                            	<display:column titleKey="DELETE" href="javascript:deleteTerm('#')" paramId="id" paramProperty="termID"  value="Xóa"/>
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
                           <spring:message code="EDU_PROGRAM"/>
                        </div>
                        <div class="panel-body">
                           <form:form modelAttribute="eduProgramInfor" action="${pageContext.servletContext.contextPath}/EduProgram.do" id="mainEduProgram" method="POST" >
                             <form:hidden path="eduProgramID"/>
                             <table style="width: 100%">
                             <tr class="form-group">
				                <td align="left" width="10%">
				                	<label><spring:message code="ID"/></label>
				                	</td>
				                	<td align="left">	
				                		<form:errors cssClass="err_msg" path="eduProgramID"/>									
										<c:if test="${eduProgramInfor.eduProgramID == 0}"><div id="sid"><spring:message code="ADD"/></div></c:if>
										<c:if test="${eduProgramInfor.eduProgramID != 0}"><c:out value="${eduProgramInfor.eduProgramID}"/></c:if>
									</td>
								</tr>				              
								<tr>
									<td  align="left">
				                	<label><spring:message code="EDU_PROGRAM"/></label>
				                	</td>
					                <td align="left">	
					                	<form:errors cssClass="err_msg" path="eduProgramName"/>									
										<form:input cssClass="form-control form-search" path="eduProgramName"/>
									</td>
									<td/>													
								</tr>
								<tr>
									<td  align="center"><input type="submit" class="btn btn-info" value="<spring:message code="BTN_CONFIRM"/>" /> </td>
									<td align="right"><input type="button" class="btn btn-info" onclick="javascript:refreshEduProgramTB();" value="<spring:message code="REFRESH" />" /> </td>
								</tr>
							</table>
                            </form:form>                                                                
                                    <!-- Advanced Tables -->
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="table-responsive">
                            <div class="err_msg"><c:out value="${sub_errmsg}"></c:out></div>
                            <display:table list="${listEduPr}" pagesize="2" requestURI="${pageContext.servletContext.contextPath}/TermAndEduProgram.do"  class="table table-striped table-bordered table-hover"  >
                            	<display:column property="eduProgramID" titleKey="ID" />
                            	<display:column property="eduProgramName" titleKey="EDU_PROGRAM" />            
                            	<display:column titleKey="UPDATE" href="javascript:setTBEduProgram('#')" paramId="id" paramProperty="eduProgramID" value="Sửa" />
                            	<display:column titleKey="DELETE" href="javascript:deleteEduProgram('#')" paramId="id" paramProperty="eduProgramID"  value="Xóa"/>
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