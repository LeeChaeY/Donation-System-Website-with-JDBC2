package model.dao.mybatis.mapper;

import java.util.List;

import model.AnimalArticle;
import model.DonationImage;

public interface AnimalArticleMapper {
	public int insertArticle(AnimalArticle animal);
	
//	public int insertImage(DonationImage image);
	
	public AnimalArticle selectAnimalArticleByPk(int articleId);
	
//	public List<DonationImage> selectImages(int articleId);
	
//	public int updateArticle(AnimalArticle article);
	
	public int updateAnimalArticle(AnimalArticle article);
	
//	public int deleteArticle(int articleId);
}
