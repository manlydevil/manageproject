/**Copyright(C) 2017
 *ErrorController.java, Apr 19, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import manageproject.utils.Constants;

/**
 * @author DELL
 *
 */
/**
 * Class chứa các method xử lý liên quan tới MH thông báo lỗi
 * @author DELL
 *
 */
@Controller
@PropertySource(value="classpath:msg_error.properties", encoding="UTF-8")
public class ErrorController {
	@Autowired
	private Environment env;
	/**
	 * Phương thức xử lý khi vào MH thông báo lỗi
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping(value="error.do", method=RequestMethod.GET)
	public String setMess(ModelMap modelMap,HttpServletRequest request) {
		String error = request.getParameter("error");
		String errorMsg = "";
		if(Constants.NOTFOUND.equals(error)) {
			errorMsg = env.getProperty("NOTFOUND");
		} else if(Constants.UPDATE_FAILED.equals(error)) {
			errorMsg = env.getProperty("UpdateFailed");
		} else if(Constants.SYSTEM_ERROR.equals(error)) {
			errorMsg = env.getProperty("SystemError");
		} else if(Constants.UPLOAD_FAILED.equals(error)) {
			errorMsg = env.getProperty("UploadFailed");
		} else if(Constants.ERROR_FILE_SIZE.equals(error)) {
			errorMsg = env.getProperty("ErrFileSize");
		}
		modelMap.addAttribute("errorMsg", errorMsg);
		return "error";
	}
}
