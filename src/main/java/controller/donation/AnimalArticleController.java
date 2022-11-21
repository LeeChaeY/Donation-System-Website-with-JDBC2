package controller.donation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.catalina.manager.util.SessionUtils;

import controller.Controller;
import controller.user.RegisterUserController;
import controller.user.UserSessionUtils;
import model.AnimalArticle;
import model.User;
import model.service.AnimalManager;
import model.service.UserManager;

public class AnimalArticleController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 final Logger log = LoggerFactory.getLogger(RegisterUserController.class);
    	if (request.getMethod().equals("GET")) {
			return "/donationForm/animalForm.jsp";  
	    }
		
    	//LocalDate now  = LocalDate.now();
    	//String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    	HttpSession session = request.getSession();
		String userId = UserSessionUtils.getLoginUserId(session);
    	
    	//POST request
    	AnimalArticle animal = new AnimalArticle(0,
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
    											userId,
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
    											request.getParameter("personality"));
    	 log.debug("AnimalDonation Controller : {}", animal);
    	 
    	 try {
    		 AnimalManager manager = AnimalManager.getInstance();
    		 int articleId = manager.create(animal);
    		 animal.setArticleId(articleId);
    		 request.setAttribute("article", animal);
    		 return "/donationList/animalArticle.jsp";
    	 }catch (Exception e) {
    		 request.setAttribute("exception", e);
    		 request.setAttribute("animal", animal);
    		 return "/donationForm/animalForm.jsp";
		}
	}
}
