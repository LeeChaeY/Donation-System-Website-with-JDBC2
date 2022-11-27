package controller.donation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.AnimalArticle;
import model.DonationComment;

import model.DonationReceipt;

import model.Donator;
import model.service.AnimalManager;
import model.service.CommentManager;
import model.service.DonationManager;

import model.service.DonationReceiptManager;

import model.service.UserNotFoundException;

public class ViewAnimalArticleController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		 final Logger log = LoggerFactory.getLogger(ViewAnimalArticleController.class);
		
		try {
			HttpSession session = request.getSession();	
			int articleId = Integer.parseInt(request.getParameter("articleId"));
			
			AnimalManager animal_man = AnimalManager.getInstance();
			AnimalArticle article = animal_man.findAnimalArticleByArticleId(articleId);
			System.out.println("articleInfo: "+article);
			request.setAttribute("article", article);
			
			CommentManager comm_man = CommentManager.getInstance();
			List<DonationComment> comment_l = comm_man.findAllComment(articleId);
			request.setAttribute("comment", comment_l);
			
			DonationManager donationMan = DonationManager.getInstance();
			List<Donator> donatorList = donationMan.latest10Donors(articleId);
			request.setAttribute("donatorList", donatorList);
			

			if (article.getReceiptCheck().equals("Y")) {
				DonationReceiptManager receipt_man = DonationReceiptManager.getInstance();
				DonationReceipt receipt = receipt_man.findByArticleId(articleId);
				request.setAttribute("donationReceipt", receipt);
				log.debug("receipt: {}", receipt);
			}

			return "/donationList/animalArticle.jsp";
		}catch (ArithmeticException e) {
			// TODO: handle exception
			throw new UserNotFoundException("존재하지 않는 후원글입니다.");
		}
	}

}
