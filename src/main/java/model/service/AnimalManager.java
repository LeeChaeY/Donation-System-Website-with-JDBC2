package model.service;

import java.sql.SQLException;

import model.AnimalArticle;
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
}
