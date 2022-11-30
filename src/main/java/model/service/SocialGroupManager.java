package model.service;

import java.sql.SQLException;

import model.DonationImage;
import model.SocialGroupArticle;
import model.dao.SocialGroupDAO;

public class SocialGroupManager
{
    private static SocialGroupManager socialGroupMan = new SocialGroupManager();
    private SocialGroupDAO socialGroupDAO;
    
    private SocialGroupManager() {
        try {
            socialGroupDAO = new SocialGroupDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }           
    }
    
    public static SocialGroupManager getInstance() {
        return socialGroupMan;
    }
    
    public int create(SocialGroupArticle article) throws SQLException, ExistingUserException {
        return socialGroupDAO.create(article);
    }
    
    public int create_image(DonationImage image) throws SQLException, ExistingUserException {
        return socialGroupDAO.create_image(image);
    }
    
    public SocialGroupArticle findSocialGroupArticleByArticleId(int articleId) 
        throws SQLException, ArticleNotFoundException {
        SocialGroupArticle socialGroupArticle = socialGroupDAO.findSocialGroupArticleByArticleId(articleId);
        if (socialGroupArticle == null) {
            throw new ArticleNotFoundException(articleId + "는 존재하지 않는 socialGroup article ID입니다.");
        }
        return socialGroupArticle;
    }
    
    public int update(SocialGroupArticle article) throws SQLException{
        return socialGroupDAO.update(article);
    }

    public int remove(int articleid) throws SQLException, UserNotFoundException {
        return socialGroupDAO.remove(articleid);
    }
    
    public SocialGroupDAO getArticleDAO() {
        return this.socialGroupDAO;
    }
    
    public int update_image(DonationImage image) throws SQLException{
		return socialGroupDAO.update_image(image);
	}
    
    public int getMaxOrder(int articleId)throws SQLException{
		return socialGroupDAO.getMaxOrder(articleId);
	}
}