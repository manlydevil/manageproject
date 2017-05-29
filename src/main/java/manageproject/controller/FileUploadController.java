/**Copyright(C) 2017
 *FileUploadController.java, Apr 15, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.ParentReference;

import manageproject.dao.StudentDao;
import manageproject.entities.formbean.ProjectInforBean;
import manageproject.entities.formbean.StudentInforBean;
import manageproject.logic.StudentLogic;
import manageproject.utils.Common;


/**
 * @author DELL
 *
 */
@Controller
@PropertySource(value="classpath:config.properties")
public class FileUploadController {
	@Autowired
	private StudentLogic studentLogic;
	@Autowired
	private Environment env;
	
	/** Application name. */
    private static final String APPLICATION_NAME =
        "Drive API Java Quickstart";

    /** Directory to store user credentials for this application. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
        System.getProperty("user.home"), ".credentials/drive-java-quickstart");

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =
        JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes required by this quickstart.
     *
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/drive-java-quickstart
     */
    private static final List<String> SCOPES =
        Arrays.asList(DriveScopes.DRIVE);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in =
            FileUploadController.class.getResourceAsStream("/client_secret.json");
        GoogleClientSecrets clientSecrets =
            GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(
            flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

    /**
     * Build and return an authorized Drive client service.
     * @return an authorized Drive client service
     * @throws IOException
     */
    public static Drive getDriveService() throws IOException {
        Credential credential = authorize();
        return new Drive.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
    
    public boolean uploadAndSetLink(MultipartFile[] files, ProjectInforBean inforBean) throws Exception {
    		String links[] = new String[2];
    		Drive service = getDriveService();
    		// tạo thư mục
    		String id =  createFolder(inforBean, service); 
    		File fileMetadata = new File();    		
    		// Build a new authorized API client service.
    		for(int i = 0; i < files.length; i++) {
    			if(files[i].getSize() != 0){
    				fileMetadata = new File();
			        fileMetadata.setTitle(files[i].getOriginalFilename());
			        fileMetadata.setParents(Collections.singletonList(
			        		  new ParentReference().setId(id)));
			        java.io.File filePath = new java.io.File(files[i].getOriginalFilename());
			        files[i].transferTo(filePath);
			        FileContent mediaContent = new FileContent(files[i].getContentType(), filePath);
			        File f = service.files().insert(fileMetadata, mediaContent)
			                .setFields("id")
			                .execute();
			        links[i] = "http://drive.google.com/open?id=" + f.getId();
    			}
		     }
    		if(links[0] != null) {
    			if(!"".equals(inforBean.getSourceLink().trim())) {
    				deleteFile(service, Common.getFileId(inforBean.getSourceLink()));
    			} 
    			inforBean.setSourceLink(links[0]);
    		}
    		if(links[1] != null) {
    			if(!"".equals(inforBean.getDescriptionLink().trim())) {
    				deleteFile(service, Common.getFileId(inforBean.getDescriptionLink()));
    			}
    			inforBean.setDescriptionLink(links[1]);
    		}
    		return true;
    }
    
    private String createFolder(ProjectInforBean inforBean, Drive service) throws Exception {
    	StudentInforBean bean = studentLogic.getTermAndEduProgram(inforBean.getStudentNumber());
    	String folderName = inforBean.getStudentNumber()+"-"+bean.getName();
    	String rootPath[] = {"datn",bean.getTerm(), bean.getEduProgram(), folderName}; 
    	String folderId = checkExistedFolder(folderName, service);
		File fileMetadata = new File();
		// tạo mới folder
		if(folderId.equals("")) {
			for(int x = 0; x < rootPath.length; x++) {
				folderId = checkExistedFolder(rootPath[x], service);
				if(folderId.equals("")) {
					fileMetadata.setTitle(rootPath[x]);
					fileMetadata.setMimeType("application/vnd.google-apps.folder");
					if(x > 0){
					fileMetadata.setParents(Collections.singletonList(
							  new ParentReference().setId(checkExistedFolder(rootPath[x-1], service))));
					}
					File ff = service.files().insert(fileMetadata)
					        .setFields("id, parents")			        
					        .execute();
					folderId = ff.getId();
				}
			}
		}
		return folderId;
    }
    
    private String checkExistedFolder(String folderName, Drive service) throws IOException {
    	String folderId = "";
		// kiểm tra tồn tại folder
		Files.List request = service.files().list()
				.setQ("mimeType='application/vnd.google-apps.folder' and trashed=false and title='" + folderName + "'");
		FileList filess = request.execute();
		if(filess.getItems().size() != 0) {
			folderId = filess.getItems().get(0).getId();
		}
		return folderId;
    }
    
    public boolean deleteFolderOnDrive(String id) {
    	try {
    		String parentID = "";
			Drive service = getDriveService();
			File file = service.files().get(id).setFields("parents").execute();
			for(ParentReference parent : file.getParents() ) {
				parentID = parent.getId();
			}
			service.files().delete(parentID).execute();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}    	
    }
    
    public boolean deleteFile(Drive service, String id) {
    	try {
    		service.files().delete(id).execute();
    		return true;
    	}catch(Exception ex) {
    		ex.printStackTrace();
    		return false;
    	}
    }
}
