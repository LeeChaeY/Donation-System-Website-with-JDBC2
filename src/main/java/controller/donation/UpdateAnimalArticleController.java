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
			String articleIdStr = request.getParameter("articleId");
			int articleId = Integer.parseInt(articleIdStr);
			
			AnimalManager manager = AnimalManager.getInstance();
			AnimalArticle article = manager.findAnimalArticleByArticleId(articleId);
			request.setAttribute("article", article);
			log.debug("UpdateForm article : {}", article);
			return "/donationForm/animalUpdateForm.jsp";  
	    }
		
		String updateId = request.getParameter("userId");
		String articleIdStr = request.getParameter("articleId");
		int articleId = Integer.parseInt(articleIdStr);
		
		AnimalArticle animal = new AnimalArticle(
								articleId,
								request.getParameter("title"),
								"animal",
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
								updateId,
								0,
								request.getParameter("name"),
								request.getParameter("area"),
								request.getParameter("type"),
								request.getParameter("age"),
								request.getParameter("weight"),
								request.getParameter("gender"),
								request.getParameter("neutering"),
								request.getParameter("current_status"),
								request.getParameter("health_status"),
								request.getParameter("personality")
								);
		log.debug("UpdateAnimalArticle Controller : {}", animal);
		AnimalManager manager = AnimalManager.getInstance();
		manager.update(animal);
		log.debug("UpdateAnimalArticle Controller After update : {}", animal);
		AnimalArticle article = manager.findAnimalArticleByArticleId(articleId);
		request.setAttribute("article", article);
		return "/donationList/animalArticle.jsp";
	}

}
