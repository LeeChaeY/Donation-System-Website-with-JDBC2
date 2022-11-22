package controller.donation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.AnimalArticle;
import model.service.AnimalManager;
import model.service.UserNotFoundException;

public class UpdateAnimalArticleController implements Controller{
	 private static final Logger log = LoggerFactory.getLogger(UpdateAnimalArticleController.class);
	 
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {
			String updateId = request.getParameter("userId");
			String articleIdStr = request.getParameter("articleId");
			int articleId = Integer.parseInt(articleIdStr);
			
			AnimalManager manager = AnimalManager.getInstance();
			AnimalArticle article = manager.findAnimalArticleByArticleId(articleId);
			request.setAttribute("article", article);
			
			log.debug("UpdateForm article : {}", article);
		
			return "/donationForm/animalUpdateForm.jsp";  
	    }
		
		try {
			AnimalManager manager = AnimalManager.getInstance();
			String articleId = request.getParameter("articleId");
			int id = Integer.parseInt(articleId);
			AnimalArticle article = manager.findAnimalArticleByArticleId(id);
			System.out.println("articleInfo: "+article);
			request.setAttribute("article", article);
			return "/donationList/animalArticle.jsp";
		}catch (ArithmeticException e) {
			// TODO: handle exception
			throw new UserNotFoundException("존재하지 않는 후원글입니다.");
		}
	}

}
