<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
   <section class="menu-section" >
        <div class="container" align="center">
            <div class="row">
                <div class="col-md-12">
                    <div class="navbar-collapse collapse ">
                        <ul id="menu-top" class="nav navbar-nav navbar-right">
                            <li><a href="${pageContext.servletContext.contextPath}/UpdateInformation.st" class="menu-top-active"><spring:message code="UPDATE_INFOR"/> </a></li>                        
                            <li><a href="${pageContext.servletContext.contextPath}/AddProject.st"><spring:message code="UPDATE_PRO"/></a></li>
                            <li><a href="${pageContext.servletContext.contextPath}/ChangePass.st"><spring:message code="CHANGE_PASS"/></a></li>
                            <li><a href="${pageContext.servletContext.contextPath}/logout" ><spring:message code="LOGOUT"/></a></li>
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
                <h4 class="header-line"><spring:message code="UPDATE_INFOR"/></h4>                
            </div>
        </div>
        <div class="err_msg">
          	<form:errors path="*"/>
        </div>
             <div class="row">
            <div class="col-md-6 col-sm-6 col-xs-12">
            <form:form modelAttribute="basicInfor" action="${pageContext.servletContext.contextPath}/Update.st" method="POST">
                            <form:errors path="*" cssClass="err_msg" />
               <div class="panel panel-info">             
                        <div class="panel-body">
                           <table>
                                        <tr class="form-group">
                                            <td width="15%"><label><spring:message code="FULLNAME"/></label></td>
                                            <td width="100%"><form:input cssClass="form-control" path="name" /></td>
                                            </tr>
                                 		<tr class="form-group">
                                            <td><label><spring:message code="EMAIL"/></label></td>
                                            <td><form:input cssClass="form-control" path="email" /></td>
                                        </tr>
                                            <tr class="form-group">
                                            <td><label><spring:message code="PHONENUMBER"/></label></td>
                                            <td><form:input cssClass="form-control" path="phone" /></td>
                                        </tr>
                                        <tr class="form-group">
                                            <td><label><spring:message code="WORKPLACE"/></label></td>
                                            <td><form:input cssClass="form-control" path="workPlace" /></td>
                                        </tr>
                                        <tr class="form-group">
                                            <td><label><spring:message code="OFFICE"/></label></td>
                                            <td><form:input cssClass="form-control" path="office" /></td>
                                        </tr>
                                        <tr>
	                                        <td></td>
	                                        <td>
	                                     		<button type="submit" class="btn btn-info"><spring:message code="BTN_UPDATE"/> </button>
	                                     	</td>
                                     	</tr>
						 </table>   
						  
                            </div>
                        </div></form:form>
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
      <!-- CUSTOM SCRIPTS  -->
    <script src="assets/js/custom.js"></script>
</body>
</html>
