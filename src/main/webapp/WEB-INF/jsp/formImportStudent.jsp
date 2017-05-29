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
            <h4 class="header-line"><spring:message code="SEARCH"/> </h4>
            <form:form action="${pageContext.servletContext.contextPath}/ImportST.do" method="POST" id="formListStudent" enctype="multipart/form-data">
	              <div><label><spring:message code="IMPORT_LABEL" /></label></div>
	              <div><input type="radio" name="fileType" value="0" checked="checked"><spring:message code="LIST_STUDENT" /></div>
	              <div><input type="radio" name="fileType" value="1"/><spring:message code="LIST_INSTRUCTOR" /></div>
	              <div><input type="radio" name="fileType" value="2"/><spring:message code="LIST_REVIEW" /></div>
	              <div><input type="file" name="file"></div>
	              <input type="submit" value="<spring:message code="BTN_CONFIRM" />">
	              <c:forEach items="${message}" var="mess">
	              <div class="err_msg"><c:out value="${mess}" /></div>
	              </c:forEach>
			</form:form>   
				</div>   
        </div>                           
                </div>
            </div>
</body>
</html>