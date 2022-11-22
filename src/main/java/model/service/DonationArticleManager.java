package model.service;

import java.sql.SQLException;
import java.util.List;

import model.DonationArticle;
import model.dao.DonationArticleDAO;

public class DonationArticleManager {
	private static DonationArticleManager articleMan = new DonationArticleManager();
	private DonationArticleDAO articleDAO;
	
	private DonationArticleManager() {
		try {
			articleDAO = new DonationArticleDAO();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static DonationArticleManager getInstance() {
		return articleMan;
	}
	
	public List<DonationArticle> find() throws SQLException {
		return articleDAO.find();
	}

}
