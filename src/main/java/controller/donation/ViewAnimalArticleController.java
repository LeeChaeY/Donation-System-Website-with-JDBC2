package controller.donation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.AnimalArticle;
import model.DonationComment;
import model.service.AnimalManager;
import model.service.CommentManager;
import model.service.UserNotFoundException;

public class ViewAnimalArticleController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			AnimalManager animal_man = AnimalManager.getInstance();
			HttpSession session = request.getSession();	
			
			int articleId = Integer.parseInt(request.getParameter("articleId"));

			AnimalArticle article = animal_man.findAnimalArticleByArticleId(articleId);
			System.out.println("articleInfo: "+article);
			request.setAttribute("article", article);
			
			CommentManager comm_man = CommentManager.getInstance();
			List<DonationComment> comment_l = comm_man.findAllComment(articleId);
			request.setAttribute("comment", comment_l);
			return "/donationList/animalArticle.jsp";
		}catch (ArithmeticException e) {
			// TODO: handle exception
			throw new UserNotFoundException("존재하지 않는 후원글입니다.");
		}
	}

}
