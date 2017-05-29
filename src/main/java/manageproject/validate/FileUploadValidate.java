/**Copyright(C) 2017
 *FileUploadValidate.java, Apr 16, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author DELL
 *
 */
@Component
public class FileUploadValidate {
	public Errors validate(MultipartFile[] files, Errors errors) {
		int index = 0;
		String fieldName[] = {"sourceLink","descriptionLink"};
			for(MultipartFile file : files) {
				if(errors.hasFieldErrors(fieldName[index])) {
					if(file!=null){
		                if (file.getSize() == 0) {
		                    errors.rejectValue(fieldName[index], "missing.file");
		                }
		            }
				}
	            index++;
				}
			return errors;
		}
	}

