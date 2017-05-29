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
                            <li><a href="${pageContext.servletContext.contextPath}/UpdateInformation.st"><spring:message code="UPDATE_INFOR"/> </a></li>                        
                            <li><a href="${pageContext.servletContext.contextPath}/AddProject.st"><spring:message code="UPDATE_PRO"/></a></li>
                            <li><a href="${pageContext.servletContext.contextPath}/ChangePass.st" class="menu-top-active"><spring:message code="CHANGE_PASS"/></a></li>
                            <li><a href="${pageContext.servletContext.contextPath}/Logout" ><spring:message code="LOGOUT"/></a></li>
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
                <h4 class="header-line"><spring:message code="CHANGE_PASS"/></h4>                
            </div>
        </div>
        <div class="err_msg">
          	<form:errors path="*"/>
        </div>
             <div class="row">
            <div class="col-md-6 col-sm-6 col-xs-12">
					<form:form modelAttribute="basicInfor" action="ChangePass.st"
						method="POST">
						
						<div class="panel panel-info">
							<div class="panel-body">
							<form:errors path="*" cssClass="err_msg" />
								<table>
									<tr class="form-group">
										<td width="40%"><label><spring:message
													code="NEW_PASS" /></label></td>
										<td><form:password cssClass="form-control" path="newPass" /></td>
									</tr>
									<tr class="form-group">
										<td><label><spring:message code="RE_NEW_PASS" /></label></td>
										<td><form:password cssClass="form-control" path="reNewPass" /></td>
									</tr>
									<tr>
	                                        <td></td>
	                                        <td>	   
	                                        	<button type="submit" class="btn btn-info"><spring:message code="BTN_UPDATE"/> </button>                      
	                                     		<button type="button" onclick="location.href='${pageContext.servletContext.contextPath}/UpdateInfor.st'" class="btn btn-info"><spring:message code="BTN_CANCEL"/> </button>
	                                     	</td>
                                     	</tr>
								</table>
							</div>
						</div>
					</form:form>
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
