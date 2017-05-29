<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
    <%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
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
    <div class="content-wrapper">
		    <div class="container">
            <div class="row">
                <div class="col-md-12">
                <a href="${pageContext.servletContext.contextPath}/NewConfig.do" class="btn btn-default btn-sm" > <spring:message code="BTN_ADDCONFIG" /> </a>
                    <form:form action="${pageContext.servletContext.contextPath}/DeleteConfig.do" id="formListConfig">
                    <input type="hidden" id="configID" name="configID" />
                    </form:form>
                    <!-- Advanced Tables -->
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="table-responsive">
                            <display:table list="${listConfigTime}" pagesize="5" requestURI="${pageContext.servletContext.contextPath}/ListConfigTime.do" class="table table-striped table-bordered table-hover"  >
                            	<display:setProperty name="basic.msg.empty_list" value="Không có bản ghi"/>
                            	<display:column property="configTimeID" titleKey="ID" />
                            	<display:column property="termNumber" titleKey="TERM" sortable="true" />
                            	<display:column property="eduProgramName" titleKey="EDU_PROGRAM" sortable="true" />
                            	<display:column property="submitDate" titleKey="SUBMIT_DATE" />
                            	<display:column property="reviewDate" titleKey="REVIEW_DATE" />
                            	<display:column titleKey="UPDATE" href="${pageContext.servletContext.contextPath}/UpdateConfig.do" paramId="id" paramProperty="configTimeID" value="Sửa" />
                            	<display:column titleKey="DELETE" href="javascript:deleteConfig('#')" paramId="ID" paramProperty="configTimeID"  value="Xóa"/>
                            </display:table>
                            <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                            <c:forEach items="${listPaging}" var="num" varStatus="loop">
	                            <ul class="pagination">
									<c:if test="${loop.first && num != 1}">
									<li class="paginate_button previous " aria-controls="dataTables-example" tabindex="0" id="dataTable-example_previous">
		                            	<a href="${pageContext.servletContext.contextPath}/ListConfigTime.do?page=${currentPage - 1}"> Previous </a>
		                            </li>
									</c:if>
									<c:if test="${currentPage != num}">
										<li class="paginate_button" aria-controls="dataTables-example" tabindex="0">
		                            	<a href="${pageContext.servletContext.contextPath}/ListConfigTime.do?page=${num}"> ${num} </a>
		                            </li>
									</c:if>
									<c:if test="${currentPage == num}">
										<li class="paginate_button disabled" aria-controls="dataTables-example" tabindex="0">
			                            	<a href=""> ${num} </a>
									</c:if>
									<c:if test="${loop.last && num < totalPages}">
										<li class="paginate_button next" aria-controls="dataTables-example" tabindex="0">
			                            	<a href="${pageContext.servletContext.contextPath}/ListConfigTime.do?page=${currentPage + 1}"> Next </a>
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
            <input type="hidden" id="msg_delete" value="<spring:message code="CONFIRM_DELETE" />"/>
    </div>
    </div>
    