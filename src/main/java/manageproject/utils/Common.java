package manageproject.utils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.services.drive.Drive;

import manageproject.entities.StudentBean;
import manageproject.entities.formbean.BasicInforFormBean;
import manageproject.entities.formbean.StudentFormBean;

@Component

public class Common {
	
	/**
	 * Phương thức mã hóa mật khẩu
	 * @param input mật khẩu
	 * @return chuỗi đã mã hóa
	 */
	public static String encryptMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
	/**
	 * Phương thức copy thuộc tính
	 * @param studentBean StudentBean
	 * @return StudentFormBean
	 */
	public static BasicInforFormBean copProSBToBIFB(StudentBean studentBean) {
		BasicInforFormBean formBean = new BasicInforFormBean();
		try {
			PropertyUtils.copyProperties(formBean, studentBean);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return formBean;
	}
	
	public static StudentBean copProSFBToSB(StudentFormBean studentFormBean) {
		StudentBean studentBean = new StudentBean();
		try {
			PropertyUtils.copyProperties(studentBean, studentFormBean);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return studentBean;
	}
	
	/**
	 * Phương thức lấy giới hạn bản ghi trên 1 trang
	 * @return int
	 */
	public static int getLimit() {
		return Integer.parseInt(ConfigProperties.getMessage("limit"));
	}
	
	/**
     * Tính tổng số trang
     * @param totalUsers tổng số User
     * @param limit số lượng cần hiển thị trên 1 trang
     * @return tổng số trang
     */
    public static int getTotalPage(int totalUsers, int limit) {
    	return (int) Math.ceil((double) totalUsers / limit);
    }

    /**
     * Lấy vị trí data cần lấy
     * @param currentPage Trang hiện tại
     * @param limit số lượng cần hiển thị trên 1 trang
     * @return vị trí cần lấy
     */
    public static int getOffset(int currentPage,int limit) {
    	return limit * (currentPage - 1);
    }
    
    /**
     * Tạo chuỗi paging
     * @param totalUsers tổng sô user
     * @param limit số lượng cần hiển thị trên 1 trang
     * @param currentPage trang hiện tại
     * @return
     */
    public static List<Integer> getListPaging (int totalRecords, int limit, int currentPage) {
    	List<Integer> lstPaging = new ArrayList<Integer>();
    	int totalPages = getTotalPage(totalRecords, limit);
    	int range = Integer.parseInt(ConfigProperties.getMessage("range"));
    	int startPage = 1;
    	int endPage = totalPages;
    	int space = (int) Math.floor(range/2);
    	if(currentPage > space && currentPage <= totalPages - space){
    		startPage = currentPage - range/2;
    		endPage = currentPage + range/2;
    	} else if(currentPage <= space) {
	    	endPage = startPage + range - 1;
    	} else if(totalPages > range) {
    		startPage = totalPages - (range - 1);
    	}
    	for(int i = startPage; i <= endPage; i++) {
    		if(i <= totalPages) {
    			lstPaging.add(i);
    		} else {
    			break;
    		}
    	}
    	return lstPaging;
    }
    
    /**
	 * Phương thức lấy key bất kỳ để đặt tên cho đối tượng truyền lên session
	 * @return String key
	 */
	public static String getKey() {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder key = new StringBuilder();
		Random random = new Random();
		while(key.length() < 30) {
			int index = (int) (random.nextFloat() * chars.length());
			key.append(chars.charAt(index));
		}
		return key.toString();
	}
	
	/**
     * Phương thức kiểm tra chuỗi ngày tháng nhập vào
     * @param date chuỗi ngày tháng
     * @return return 1 nếu ngày có tồn tại, 0 nếu ngày không tồn tại và return 2 nếu sai format
     */
	public static int checkExistDate(String date) {
		if(date.length() != 0) { 
			try {
				String[] partOfDate = date.split("/");
				int year = Integer.parseInt(partOfDate[2]);
				int month = Integer.parseInt(partOfDate[1]);
				int day = Integer.parseInt(partOfDate[0]);
		
				switch (month) {
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12: {
					if (day <= 31)
						return 1;
				}
				case 4:
				case 6:
				case 9:
				case 11: {
					if (day <= 30)
						return 1;
				}
				case 2: {
					if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
						if (day <= 29) {
							return 1;
						}
					} else {
						if (day <= 28) {
							return 1;
						}
					}
				}
				}
			} catch(ArrayIndexOutOfBoundsException ex) {
				System.out.println("Lỗi nhập ngày không đúng định dạng");
				return 2;
			}
			return 0;
			}
		return 1;
	}
	
	/**
     * Convert lại ngày để đưa vào DB
     * @param date ngày
     * @return ngày đã convert
     */
    public static String convertDateHQL(String date) {
    	if(date.length() > 0) {
    		String[] rs = date.split("/");
    		return rs[2]+"-"+rs[1]+"-"+rs[0];
    	}
    	return null;
    }
    
    /**
     * Phương thức chuyển định dạng chuỗi ngày tháng 
     * @param date ngày tháng 
     * @return chuỗi theo format
     */
    public static String formatDate(String date) {
    	if(date != null) {
	    	String[] arrDate = date.split("-");
	    	return arrDate[2]+"/"+arrDate[1]+"/"+arrDate[0];
    	} 
    	return date;
    }
    
    /**
     * Phương thức so sánh 2 ngày 
     * @param date1 ngày thứ 1 
     * @param date2 ngày thứ 2
     * @return true nếu ngày thứ 1 sớm hơn ngày thứ 2 và ngược lại
     */
    public static Boolean compareDate(String date1, String date2) {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		boolean resultCompare;
		try {
			Date dt1 = sdf.parse(date1);
			Date dt2 = sdf.parse(date2);
			resultCompare = dt1.before(dt2);
			if(resultCompare) {
				return true;
			}
		} catch (ParseException e) {
			System.out.println("error: " + e.getMessage());
		}
		return false;
    }
    
    public static boolean compareWithCurDate(String date1) {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	LocalDate localDate = LocalDate.now();
    	String date2 = dtf.format(localDate);
		boolean resultCompare;
		try {
			Date dt1 = sdf.parse(date1);
			Date dt2 = sdf.parse(date2);
			resultCompare = dt1.before(dt2);
			if(resultCompare) {
				return true;
			}
		} catch (ParseException e) {
			System.out.println("error: " + e.getMessage());
		}
		return false;
    }
    
    /**
     * Phương thức lấy ra ngày hiện tại
     * @return ngày hiện tại
     */
    public static String getCurrentDate() {
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    	LocalDate localDate = LocalDate.now();
    	return dtf.format(localDate);
    }
    
    /**
     * Kiểm tra xem có file cần upload không
     * @param files danh sách file
     * @return false nếu không có file đính kèm, true nếu có lớn hơn 1 file đính kèm
     */
    public static boolean checkUploadFile(MultipartFile[] files) {
    	boolean rsCheck = false;
    	for(MultipartFile file : files) {
    		if(file.getSize() != 0) rsCheck = true;
    	}
    	return rsCheck;
    }
    
    /**
     * Phương thức tách link drive lấy id của file
     * @param link link file
     * @return id file
     */
    public static String getFileId(String link) {
    	String rs[] = link.split("=");
    	return rs[1];
    }
    
    /**
     * Phương thức xóa file trên drive
     * @param service Drive
     * @param id String
     * @return boolean
     */
    public static boolean deleteFileOnDrive(Drive service, String id) {
    	try {
			service.files().delete(id);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
    }

    /**
     * Phương thức chuẩn hóa xâu
     * @param str String
     * @return String
     */
    public static String standardizedString(String str) {
    	str = str.trim();
        str = str.replaceAll("\\s+"," ");
        str = str.toUpperCase();
        return str;
    }

}
