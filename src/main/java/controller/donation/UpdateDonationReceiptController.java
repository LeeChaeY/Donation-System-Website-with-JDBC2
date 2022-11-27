package controller.donation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.DonationReceipt;
import model.dao.DonationReceiptDAO;
import model.service.DonationReceiptManager;
import model.service.UserNotFoundException;

public class UpdateDonationReceiptController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(UpdateDonationReceiptController.class);
	 
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if (request.getMethod().equals("GET")) {
			int articleId = Integer.parseInt(request.getParameter("articleId"));
			String category = request.getParameter("category");
			
			request.setAttribute("articleId", articleId);
			request.setAttribute("category", category);
			
			DonationReceiptManager receiptMan = DonationReceiptManager.getInstance();
			DonationReceipt receipt = receiptMan.findByArticleId(articleId);
			
			request.setAttribute("donationReceipt", receipt);
			
			return "/donationForm/receiptUpdateForm.jsp";
		}
		
		try {
			DonationReceipt receipt = new DonationReceipt(
					Integer.parseInt(request.getParameter("receiptId")), 
					request.getParameter("receipt_text"), 
					null, 
					null, 
					Integer.parseInt(request.getParameter("articleId"))
					);
			log.debug("Receipt Update Controller : {}", receipt);
			
			DonationReceiptManager receipt_man = DonationReceiptManager.getInstance();
			
			int result = receipt_man.update(receipt);
			request.setAttribute("result", result);
			log.debug("Receipt Update result: {}", result);
		
			return "/donationForm/block.jsp"; 
		}catch (ArithmeticException e) {
			// TODO: handle exception
			log.debug("Exception: ", e);
			return "/donationForm/receiptUpdateForm.jsp";
		}
	}
}
