package model.service;

import java.sql.SQLException;

import model.AnimalArticle;
import model.DonationImage;
import model.dao.AnimalDAO;

public class AnimalManager {
	private static AnimalManager animalMan = new AnimalManager();
	private AnimalDAO animalDAO;
	
	private AnimalManager() {
		try {
			animalDAO = new AnimalDAO();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static AnimalManager getInstance() {
		return animalMan;
	}
	
	public int create(AnimalArticle animal) throws SQLException{
		return animalDAO.create(animal);
	}
	
	public AnimalArticle findAnimalArticleByArticleId(int article_id) throws SQLException{
		return animalDAO.findAnimalArticleByArticleId(article_id);
	}
	
	public int update(AnimalArticle animal) throws SQLException{
		return animalDAO.update(animal);
	}
	
	public int remove(int articleId) throws SQLException{
		return animalDAO.remove(articleId);
	}
	
	public int create_image(DonationImage image) throws SQLException, ExistingUserException {
	    return animalDAO.create_image(image);
	}
	
	public int update_image(DonationImage image) throws SQLException{
		return animalDAO.update_image(image);
	}
}
