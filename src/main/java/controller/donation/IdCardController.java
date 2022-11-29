package controller.donation;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.ByteString;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.Feature.Type;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.User;
import model.service.UserManager;

public class IdCardController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(IdCardController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String filename = null;
    	String oriFilename = null;
	    String category = request.getParameter("category");
	    String path = null;
	    
	    if (request.getMethod().equals("GET")) {
	    	request.setAttribute("category", category);
	    	
	    	return "/donationForm/idCard.jsp";
	    }
	    
		try {
			// POST request (정보가 parameter로 전송됨)
		
		    boolean check = ServletFileUpload.isMultipartContent(request);              
		    if(check) {    // 전송된 요청 메시지의 타입이 multipart 인지 여부를 체크한다. (multipart/form-data)
	            // 아래와 같이 하면 Tomcat 내부에 복사된 프로젝트 밑에 upload 폴더가 생성됨 
	            ServletContext context = request.getServletContext();
	            path = context.getRealPath("/upload");
	            File dir = new File(path);
		            
	            // Tomcat 외부의 폴더에 저장하려면 아래와 같이 절대 경로로 폴더 이름을 지정함
	            // File dir = new File("C:/Temp");
                        
            	if (!dir.exists()) dir.mkdir();  // 전송된 파일을 저장할 폴더 생성
            
            
                DiskFileItemFactory factory = new DiskFileItemFactory();
                    // 파일 전송에 대한 기본적인 설정 Factory 클래스를 생성한다.
                factory.setSizeThreshold(10 * 1024);
                    // 메모리에 한번에 저장할 데이터의 크기를 10KB로 설정한다.
                factory.setRepository(dir);
                    // 전송된 데이터의 내용을 저장할 폴더를 지정한다.                
                
                ServletFileUpload upload = new ServletFileUpload(factory);
                    // Factory 클래스를 통해 실제 업로드 되는 내용을 처리할 객체를 선언한다.
                upload.setSizeMax(10 * 1024 * 1024);
                    // 업로드 될 파일의 최대 용량을 10MB로 설정한다.
                upload.setHeaderEncoding("utf-8");
                    // 업로드 되는 내용의 인코딩 방식을 설정한다.
                
                List<FileItem> items = (List<FileItem>)upload.parseRequest(request);                
                    // upload 객체에 전송된 모든 데이터(요청 파라미터)를 Collection 객체에 담는다.
                
                for (int i = 0; i < items.size(); ++i) {
                    FileItem item = (FileItem)items.get(i);            
                    String value = item.getString("utf-8"); // 넘어온 값에 대한 한글 처리를 한다.   
                    
                    // 이미지 처리
                    if (item.getFieldName().equals("image")) {
                        oriFilename = item.getName();    // 파일 이름 획득 (자동 한글 처리 됨)
                        if (oriFilename == null || oriFilename.trim().length() == 0) continue;
                            // 파일이 전송되어 오지 않았다면 건너뜀
                        
                        filename = UUID.randomUUID().toString() + "_" 
                                + oriFilename.substring(oriFilename.lastIndexOf("\\") + 1);
                        log.debug("{}", filename);
                        File file = new File(dir, filename);
                        item.write(file);
            		}
                }
		    }
		} catch(SizeLimitExceededException e) {
            // 업로드 되는 파일의 크기가 지정된 최대 크기를 초과할 때 발생하는 예외처리
            e.printStackTrace();           
        } catch(FileUploadException e) {
            // 파일 업로드와 관련되어 발생할 수 있는 예외 처리
            e.printStackTrace();
        } catch(Exception e) {            
            e.printStackTrace();
        }
		
		try {
            // API 시작, 파일 경로 필요 - oriFilename 사용
            // for문은 oriFilename를 구하기 위함임
            List<AnnotateImageRequest> requestImage = new ArrayList<>();
            
            log.debug("path: {}", path + "\\" + filename);
            String imageFilePath = path + "\\" + filename;
			ByteString imgBytes = ByteString.readFrom(new FileInputStream(imageFilePath));
			
			Image img = Image.newBuilder().setContent(imgBytes).build();
			Feature feat = Feature.newBuilder().setType(Type.TEXT_DETECTION).build();
			AnnotateImageRequest requestImage2 = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
			requestImage.add(requestImage2);
			
			ImageAnnotatorClient client = ImageAnnotatorClient.create();
			
			BatchAnnotateImagesResponse responseImage = client.batchAnnotateImages(requestImage);
		    List<AnnotateImageResponse> responseImage2 = responseImage.getResponsesList();
			    
		    List<String> textList = new ArrayList<String>();
			for (AnnotateImageResponse res : responseImage2) {
			    if (res.hasError()) { // 에러 발생 시 다시 idCard로 forward
		    		System.out.printf("Error: %s\n", res.getError().getMessage());
	                request.setAttribute("createFailed", true);
	                request.setAttribute("exception", res.getError());
	                
	                return "/donationForm/idCard.jsp";
			    }
			    	
			    // 에러가 나지 않았다면 텍스트 추출, for문 - 한줄씩 출력??, 단위마다 출력
		    	//System.out.println(res.getTextAnnotationsList().get(0).getDescription());
			    textList = Arrays.asList(res.getTextAnnotationsList().get(0).getDescription().split("\n"));
		    	
			    // For full list of available annotations, see http://g.co/cloud/vision/docs
		    	/*for (EntityAnnotation annotation : res.getTextAnnotationsList()) {
			    	  
					//System.out.printf("Text: %s\n", annotation.getDescription());
					//System.out.printf("Position : %s\n", annotation.getBoundingPoly());
				}*/
	    	}
			System.out.println(textList);
			//user
        	HttpSession session = request.getSession();
        	String userId = UserSessionUtils.getLoginUserId(session);
        	UserManager userMan = UserManager.getInstance();
        	User user = userMan.findUser(userId);
        	
			if (textList.get(1).split(" ")[0].equals(user.getName()) && textList.get(2).split("-")[0].equals(user.getBirthday().substring(2).replace("-", ""))) {
				return "redirect:/donationForm/"+category;
			}
			else {
				return "/donationForm/idCard.jsp";
			}
			// 비교
			
		} catch(Exception e) {            
            e.printStackTrace();
            request.setAttribute("createFailed", true);
            request.setAttribute("exception", e);
            
            return "/donationForm/idCard.jsp";
        }
        
	}
    
}
