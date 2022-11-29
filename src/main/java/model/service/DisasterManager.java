package model.service;

import java.sql.SQLException;

import model.DisasterArticle;
import model.DonationImage;
import model.dao.DisasterDAO;

public class DisasterManager {
	private static DisasterManager disasterMan = new DisasterManager();
	private DisasterDAO disasterDAO;
	
	private DisasterManager() {
		try {
			disasterDAO = new DisasterDAO();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static DisasterManager  getInstance() {
		return disasterMan;
	}
	
	public int create(DisasterArticle disaster)throws SQLException{
		return disasterDAO.create(disaster);
	}
	
	public int create_image(DonationImage image) throws SQLException, ExistingUserException {
	    return disasterDAO.create_image(image);
	}
	
	public DisasterArticle findDisasterArticleByArticleId(int article_id) throws SQLException{
		return disasterDAO.findDisasterArticleByArticleId(article_id);
	}
	
	public int update(DisasterArticle disaster) throws SQLException{
		return disasterDAO.update(disaster);
	}
	
	public int remove(int articleId) throws SQLException{
		return disasterDAO.remove(articleId);
	}
}
