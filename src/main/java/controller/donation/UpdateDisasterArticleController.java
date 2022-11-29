package controller.donation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.AnimalArticle;
import model.DisasterArticle;
import model.service.AnimalManager;
import model.service.DisasterManager;
import model.service.UserNotFoundException;

public class UpdateDisasterArticleController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {
			String articleIdStr = request.getParameter("articleId");
			int articleId = Integer.parseInt(articleIdStr);
			
			DisasterManager manager = DisasterManager.getInstance();
			DisasterArticle article = manager.findDisasterArticleByArticleId(articleId);
			request.setAttribute("article", article);
			
			return "/donationForm/disasterUpdateForm.jsp";  
	    }
		
		try {
			DisasterManager manager = DisasterManager.getInstance();
			String articleId = request.getParameter("articleId");
			
			DisasterArticle disaster = new DisasterArticle(
					 Integer.parseInt(articleId),
					request.getParameter("title"),
					"disaster",
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
					request.getParameter("userId"),
					0,
					request.getParameter("type"),
					request.getParameter("name"),
					request.getParameter("area"),
					Integer.parseInt(request.getParameter("damage_amount")),
					request.getParameter("situation")
					);
			System.out.println(disaster);
			manager.update(disaster);
			return "redirect:/donationList/disaster?articleId="+articleId; 
		}catch (ArithmeticException e) {
			// TODO: handle exception
			throw new UserNotFoundException("존재하지 않는 후원글입니다.");
		}
	}

}
