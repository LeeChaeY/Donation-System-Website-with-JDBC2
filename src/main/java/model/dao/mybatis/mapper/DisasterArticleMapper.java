package model.dao.mybatis.mapper;

import java.util.List;

import model.DisasterArticle;
import model.DonationImage;

public interface DisasterArticleMapper {

//	public int insertImage(DonationImage image);
	
	public DisasterArticle selectDisasterArticleByPk(int articleId);
	
//	public List<DonationImage> selectImages(int articleId);
	
//	public int updateArticle(DisasterArticle article);
	
	public int updateDisasterArticle(DisasterArticle article);
	
//	public int deleteArticle(int articleId);
}
