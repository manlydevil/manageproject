/**Copyright(C) 2017
 *UploadProjectValidate.java, Apr 5, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.validate;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import manageproject.entities.formbean.ProjectInforBean;
import manageproject.logic.StudentLogic;
import manageproject.utils.Constants;

/**
 * @author DELL
 *
 */
@Component
@PropertySource(value="classpath:config.properties", encoding = "UTF-8")
public class UploadProjectValidate {
	@Autowired
	private Environment env;
	@Autowired
	private StudentLogic studentLogic;
	public Errors validate(ProjectInforBean inforBean, Errors errors, MultipartFile[] files) {
		if(!errors.hasFieldErrors("studentNumber")) {
			if(!Pattern.matches(env.getProperty("studentNumberPattern"), String.valueOf(inforBean.getStudentNumber()))) {
				errors.rejectValue("studentNumber", "Invalid.StudentNumber");
			} else if(!studentLogic.checkExistedUserName(0,inforBean.getStudentNumber())) {
				errors.rejectValue("studentNumber", "NotExisted.StudentNumber");
			} else if(studentLogic.checkAssignedProject(inforBean.getStudentNumber(), inforBean.getProjectID())) {
				errors.rejectValue("studentNumber", "Assigned.StudentNumber");
			}
		}
		
		String fieldName[] = { "sourceLink", "descriptionLink" };
		for (int i = 0; i < files.length; i++) {
			if (files[i].getSize() > Integer.parseInt(env.getProperty("maxFileSizeUpload"))) {
				errors.rejectValue(fieldName[i], "file.maxsize", new Object[]{files[i].getOriginalFilename()}, "");
			}
		}
		if(files[0].getSize() != 0 && !files[0].getContentType().equals(Constants.RAR_FILE)) {
			errors.rejectValue("sourceLink","Invalid.TypeFile.Rar", new Object[]{files[0].getOriginalFilename()}, "");
		}
		
		if(files[1].getSize() != 0 && !files[1].getContentType().equals(Constants.PDF_FILE)) {
			errors.rejectValue("descriptionLink", "Invalid.TypeFile.Pdf", new Object[]{files[1].getOriginalFilename()}, "");
		}
		return errors;
	}
}
