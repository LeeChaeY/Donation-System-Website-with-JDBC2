package controller.donation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.DonationImage;
import model.SocialGroupArticle;
import model.service.SocialGroupManager;

public class SocialGroupArticleController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(SocialGroupArticleController.class);
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("GET")) {
            // GET request: 등록 form 요청
            log.debug("SocialGroupForm Request");
            return "/donationForm/socialGroupForm.jsp";
        }
        HttpSession session = request.getSession();
        String userId = UserSessionUtils.getLoginUserId(session);
        
        // POST request (회원정보가 parameter로 전송됨)
        //부모클래스: DonationArticle  자식클래스: SocialGroupArticle
        SocialGroupArticle article = new SocialGroupArticle();
            article.setArticleId(0); //
            article.setTitle(request.getParameter("title"));
            article.setCategory("socialGroup");
            article.setDeadline(request.getParameter("deadline"));
            article.setBankName(request.getParameter("bank_name"));
            article.setAccHolder(request.getParameter("acc_holder"));
            article.setAccNum(request.getParameter("acc_num"));
            article.setIdCheck("Y");
            article.setDueDate(request.getParameter("due_date"));
            article.setUsePlan(request.getParameter("use_plan"));
            article.setOtherText(request.getParameter("other_text"));
            article.setCreateDate(null); //
            article.setUpdateDate(null); //
            article.setReceiptCheck("N");
            article.setTotalAmount(0);
            article.setUserId(userId);
        
            article.setAge(Integer.parseInt(request.getParameter("age"))); //
            article.setGender(request.getParameter("gender"));
            article.setType(request.getParameter("type"));
            article.setArea(request.getParameter("area"));
            article.setSituation(request.getParameter("situation"));
        
        log.debug("Create article : {}", article); 
        
        //이미지
        DonationImage image = new DonationImage();
            image.setArticleId(0); //
            image.setImgLink(request.getParameter("img"));
            int imageIdx = 1;
            image.setImgOrder(imageIdx++); //
            List<DonationImage> imageList;
            //imageList.add(image);
            //article.setImageList(imageList);
        
        /*
         * for(DonationImage donationImage : imageList) {
         * System.out.println(donationImage); }
         * System.out.println(imageList.toString());
         */
        
        try {
          SocialGroupManager manager = SocialGroupManager.getInstance();
          int articleId = manager.create(article, image); 
          article.setArticleId(articleId);
          
          request.setAttribute("socialGroupArticle", article);
          
          /*
           * String rootPath = System.getProperty(image.getImgLink());
           * System.out.println("이미지 절대경로 " + rootPath);
           * request.setAttribute("socialGroupArticleImage", rootPath);
           */
          
          return "/donationList/socialGroupArticle.jsp"; // 성공 시 리스트 화면으로 redirect
        } catch (Exception e) { // 예외 발생 시 form으로 forwarding
            request.setAttribute("createFailed", true);
            request.setAttribute("exception", e);
            request.setAttribute("socialGroupArticle", article);
            
            return "/donationForm/socialGroupForm.jsp";
        }
    }
}
