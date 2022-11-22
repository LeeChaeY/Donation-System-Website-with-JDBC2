package controller.donation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.AnimalArticle;
import model.DonationComment;
import model.service.AnimalManager;
import model.service.CommentManager;
import model.service.UserNotFoundException;

public class DeleteCommentController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		final Logger log = LoggerFactory.getLogger(CommentController.class);
		
		try {
			CommentManager comm_man = CommentManager.getInstance();
			int commentId = Integer.parseInt(request.getParameter("commentId"));
			log.debug("commentId : {}", commentId);
			
			int result = comm_man.remove(commentId);
			request.setAttribute("result", result);
			
			String category = request.getParameter("category");
			
			int articleId = Integer.parseInt(request.getParameter("articleId"));
			List<DonationComment> comment_l = comm_man.findAllComment(articleId);
			request.setAttribute("comment", comment_l);
			
			if (category == "animal") {
				AnimalManager animal_man = AnimalManager.getInstance();
				AnimalArticle article = animal_man.findAnimalArticleByArticleId(articleId);
				
				System.out.println("articleInfo: "+article);
				request.setAttribute("article", article);
				
				return "/donationList/animalArticle.jsp";
			}
//			else if (category == "disaster") {
//				DisasterManager disaster_man = DisasterManager.getInstance();
//				DisasterArticle article = disaster_man.findAnimalArticleByArticleId(articleId);
//				
//				System.out.println("articleInfo: "+article);
//				request.setAttribute("article", article);
//				
//				return "/donationList/disaterArticle.jsp";
//			}
//			else {
//				SocialGroupManager socialGroup_man = SocialGroupManager.getInstance();
//				SocialGroupArticle article = socialGroup_man.findAnimalArticleByArticleId(articleId);
//				
//				System.out.println("articleInfo: "+article);
//				request.setAttribute("article", article);
//				
//				return "/donationList/socialGroupArticle.jsp";
//			}
			
//			return "redirect:/donationList/"+category; // viewAnimalController로 리다이렉션
			return "/donationList/animalArticle.jsp";
		
		}catch (ArithmeticException e) {
			// TODO: handle exception
			throw new UserNotFoundException("존재하지 않는 후원글입니다.");
		}
	}

}
