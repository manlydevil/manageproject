/**Copyright(C) 2017
 *LoginInterceptor.java, May 12, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import manageproject.entities.formbean.AccountInfor;

/**
 * @author DELL
 *
 */
public class LoginInterceptor implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if( !request.getRequestURI().equals("/manageproject/") &&
			!request.getRequestURI().equals("/manageproject/login/admin") &&
			!request.getRequestURI().equals("/manageproject/login/student") &&
			!request.getRequestURI().equals("/manageproject/ListReferenceProject") &&
			!request.getRequestURI().equals("/manageproject/ProjectDetails")) {
			if(request.getRequestURI().contains(".st")) {
				AccountInfor accountInfor = (AccountInfor) request.getSession().getAttribute("studentSession");
				if(accountInfor == null) {
					response.sendRedirect("/manageproject/login/student");
					return false;
				}
			} else if (request.getRequestURI().contains(".tc")) {
				AccountInfor accountInfor = (AccountInfor) request.getSession().getAttribute("teacherSession");
				if(accountInfor == null) {
					response.sendRedirect("/manageproject/login");
					return false;
				}
			} else	{
					AccountInfor accountInfor = (AccountInfor) request.getSession().getAttribute("adminSession");
					if(accountInfor == null) {
						response.sendRedirect("/manageproject/login/admin");
						return false;
					}
	       }
		}
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
