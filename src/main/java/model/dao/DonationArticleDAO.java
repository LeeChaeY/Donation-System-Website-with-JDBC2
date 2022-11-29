package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.cloud.vision.v1.Image;

import model.AnimalArticle;
import model.DonationArticle;
import model.DonationComment;
import model.DonationImage;

public class DonationArticleDAO {
private JDBCUtil jdbcUtil = null;
	
	public DonationArticleDAO() {
		jdbcUtil = new JDBCUtil();
	}
	
	public List<DonationArticle> find() throws SQLException{
		
		String sql = "SELECT article_id, title, category, deadline, create_date, update_date, user_id, total_amount "
				+"FROM donation_article ORDER BY create_date desc";
		jdbcUtil.setSqlAndParameters(sql, null);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<DonationArticle> list = new ArrayList<DonationArticle>();
			int articleId;
			while (rs.next()) {
				articleId = rs.getInt("article_id");
				DonationArticle article = new DonationArticle(
						articleId,
						rs.getString("title"), 
						rs.getString("category"), 
						rs.getString("deadline"), 
						rs.getDate("create_date"), 
						rs.getDate("update_date"), 
						rs.getInt("total_amount"),
						rs.getString("user_id"));
				list.add(article);
			}
			return list;
		}catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}	
		return null;
	}

}
