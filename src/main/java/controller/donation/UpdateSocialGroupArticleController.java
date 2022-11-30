package controller.donation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.SocialGroupArticle;
import model.service.SocialGroupManager;
import model.service.UserNotFoundException;

public class UpdateSocialGroupArticleController implements Controller{
     private static final Logger log = LoggerFactory.getLogger(UpdateAnimalArticleController.class);
     
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("GET")) {
            String articleIdStr = request.getParameter("articleId");
            int articleId = Integer.parseInt(articleIdStr);
            
            SocialGroupManager manager = SocialGroupManager.getInstance();
            SocialGroupArticle article = manager.findSocialGroupArticleByArticleId(articleId);
            request.setAttribute("article", article);
            
            log.debug("UpdateForm article : {}", article);
            return "/donationForm/socialGroupUpdateForm.jsp";  
        }
        
        try {
            SocialGroupManager manager = SocialGroupManager.getInstance();
            String articleId = request.getParameter("articleId");
            System.out.println("articleId: " + articleId);
            
            System.out.println("updateSocialGroupController의 socialGroup객체");
            SocialGroupArticle socialGroup = new SocialGroupArticle(
                    Integer.parseInt(articleId),
                    request.getParameter("title"),
                    "socialGroup",
                    request.getParameter("deadline"),
                    request.getParameter("bank_name"),
                    request.getParameter("acc_holder"),
                    request.getParameter("acc_num"),
                    "Y",
                    request.getParameter("due_date"),
                    request.getParameter("use_plan"),
                    request.getParameter("other_text"),
                    null, 
                    null,
                    "N",
                    0,
                    request.getParameter("userId"),
                    Integer.parseInt(request.getParameter("age")),
                    request.getParameter("gender"),
                    request.getParameter("type"),
                    request.getParameter("area"),
                    request.getParameter("situation")
                    );  
            System.out.println("manager.update 전");
            manager.update(socialGroup);
            System.out.println("manager.update 후");
            
            return "redirect:/donationList/socialGroup?articleId="+articleId; 
        }catch (ArithmeticException e) {
            // TODO: handle exception
            throw new UserNotFoundException("존재하지 않는 후원글입니다.");
        }
    }

}
