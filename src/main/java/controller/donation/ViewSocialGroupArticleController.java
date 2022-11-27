<<<<<<< HEAD
package controller.donation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.DonationComment;
import model.SocialGroupArticle;
import model.service.CommentManager;
import model.service.SocialGroupManager;
import model.service.UserNotFoundException;

public class ViewSocialGroupArticleController implements Controller{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        
        try {
            SocialGroupManager socialGroupMan = SocialGroupManager.getInstance();
            HttpSession session = request.getSession(); 
            
            int articleId = Integer.parseInt(request.getParameter("articleId"));

            SocialGroupArticle article = socialGroupMan.findSocialGroupArticleByArticleId(articleId);
            System.out.println("articleInfo: "+article);
            request.setAttribute("socialGroupArticle", article);
            
            CommentManager comm_man = CommentManager.getInstance();
            List<DonationComment> comment_l = comm_man.findAllComment(articleId);
            request.setAttribute("comment", comment_l);
            return "/donationList/socialGroupArticle.jsp";
        } catch (ArithmeticException e) {
            // TODO: handle exception
            throw new UserNotFoundException("존재하지 않는 후원글입니다.");
        }
    }

}
=======
package controller.donation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.DonationComment;
import model.SocialGroupArticle;
import model.service.CommentManager;
import model.service.SocialGroupManager;
import model.service.UserNotFoundException;

public class ViewSocialGroupArticleController implements Controller{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        
        try {
            SocialGroupManager socialGroupMan = SocialGroupManager.getInstance();
            HttpSession session = request.getSession(); 
            
            int articleId = Integer.parseInt(request.getParameter("articleId"));

            SocialGroupArticle article = socialGroupMan.findSocialGroupArticleByArticleId(articleId);
            System.out.println("articleInfo: "+article);
            request.setAttribute("socialGroupArticle", article);
            
            CommentManager comm_man = CommentManager.getInstance();
            List<DonationComment> comment_l = comm_man.findAllComment(articleId);
            request.setAttribute("comment", comment_l);
            return "/donationList/socialGroupArticle.jsp";
        } catch (ArithmeticException e) {
            // TODO: handle exception
            throw new UserNotFoundException("존재하지 않는 후원글입니다.");
        }
    }

}
>>>>>>> refs/remotes/origin/develop
