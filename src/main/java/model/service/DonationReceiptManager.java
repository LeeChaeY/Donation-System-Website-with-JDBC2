package model.service;

import java.sql.SQLException;

import model.DonationReceipt;
import model.ReceiptImage;
import model.dao.DonationReceiptDAO;

public class DonationReceiptManager {
	private static DonationReceiptManager receiptMan = new DonationReceiptManager();
	private DonationReceiptDAO receiptDAO;
	
	private DonationReceiptManager() {
		try {
			receiptDAO = new DonationReceiptDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static DonationReceiptManager getInstance() {
		return receiptMan;
	}
	
	public int create(DonationReceipt receipt) throws SQLException {
		return receiptDAO.create(receipt);
	}
	
	public int create_image(ReceiptImage receiptImage) throws SQLException {
		return receiptDAO.create_image(receiptImage);
	}
	
	public DonationReceipt findByReceiptId(int receiptId) {
		return receiptDAO.findByReceiptId(receiptId);
	}
	
	public DonationReceipt findByArticleId(int articleId) {
		return receiptDAO.findByArticleId(articleId);
	}
	
	public int update(DonationReceipt receipt) throws SQLException {
		return receiptDAO.update(receipt);
	}
	
	public int remove(int articleId) throws SQLException {
		return receiptDAO.remove(articleId);
	}
}
