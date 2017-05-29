/**Copyright(C) 2017
 *GlobalExceptionHandler.java, Apr 16, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.controller;

import org.apache.commons.fileupload.FileUploadBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import manageproject.utils.Constants;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author DELL
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	@Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    public String handleMultipartException(Exception ex, HttpServletRequest request){
    	String ssKey = request.getParameter("ssKey");
    	FlashMap flash = RequestContextUtils.getOutputFlashMap(request);
        if (ex instanceof MultipartException) {
            MultipartException mEx = (MultipartException)ex;
            if (ex.getCause() instanceof FileUploadBase.FileSizeLimitExceededException){
                FileUploadBase.FileSizeLimitExceededException flEx = (FileUploadBase.FileSizeLimitExceededException)mEx.getCause();
                float permittedSize = flEx.getPermittedSize() / 1024;
                String message = messageSource.getMessage(
                        "file.maxsize",
                        new Object[]{flEx, permittedSize},
                        LocaleContextHolder.getLocale());
                flash.put("errors", message);
            } else {
                flash.put("errors", "Please contact your administrator: " + ex.getMessage());
            }
        } else {
            flash.put("errors", "Please contact your administrator: " + ex.getMessage());
        }
        return "redirect:/error.do?error="+Constants.ERROR_FILE_SIZE;
    	
    }
    
    @ExceptionHandler(value = IOException.class)
    public RedirectView handleIOException(Exception ex, HttpServletRequest request){
        RedirectView model = new RedirectView("error");
        FlashMap flash = RequestContextUtils.getOutputFlashMap(request);
        flash.put("errors", "Please contact your administrator: " + ex.getMessage());
        return model;
    }
}
