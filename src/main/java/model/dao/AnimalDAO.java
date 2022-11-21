package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;
import model.AnimalArticle;

public class AnimalDAO {
	private JDBCUtil jdbcUtil = null;
	
	public AnimalDAO() {
		jdbcUtil = new JDBCUtil();
	}

	public int create(AnimalArticle animal) throws SQLException {			
		try {		
			String insert1 = "INSERT INTO DONATION_ARTICLE VALUES (seq_donation_article_id.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, ?, ?, ?)";
			Object[] param1 = new Object[] {animal.getTitle(), animal.getCategory(), animal.getDeadline(), animal.getBankName(),
					animal.getAccHolder(), animal.getAccNum(), animal.getIdCheck(), animal.getDueDate(), animal.getUsePlan(), animal.getOtherText(),
					animal.getUpdateDate(), animal.getReceiptCheck(), animal.getTotalAmount(), animal.getUserId()};
			System.out.println("AdnimalDAO: "+animal);
			jdbcUtil.setSqlAndParameters(insert1, param1);
			String key[]= {"article_id"}; // DONATION_ARTICLE PK
			jdbcUtil.executeUpdate(key);
			ResultSet rs = jdbcUtil.getGeneratedKeys(); // 생성된 PK 값을 포함한 result set 객체 반환
			int generatedKey = 0;
			 if(rs.next()) {
				 generatedKey = rs.getInt(1);     //  PK 값을 읽음
				 String insert2 = "INSERT INTO ANIMAL_ARTICLE VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				 Object[] param2 = new Object[] {generatedKey, animal.getName(), animal.getArea(), animal.getType(), 
                         							animal.getAge(), animal.getWeight(), animal.getGender(), 
                         							animal.getNeutering(), animal.getCurrentStatus(),animal.getHealthStatus(),
                         							animal.getPersonality()};
				 jdbcUtil.setSqlAndParameters(insert2, param2);
				 jdbcUtil.executeUpdate();
			}
			return generatedKey;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return -1;			
	}
	
	public AnimalArticle findAnimalArticleByArticleId(int article_id) throws SQLException{
		
		String sql = "SELECT title, category, deadline, bank_name, acc_holder, acc_num, due_date, use_plan, other_text, TO_CHAR(CREATE_DATE, 'YYYY-MM-DD') \"create_date\", TO_CHAR(UPDATE_DATE, 'YYYY-MM-DD') \"update_date\", receipt_check, user_id, total_amount, name, area, type, age, weight, gender, neutering, current_status, health_status, personality "
				+"FROM animal_article a JOIN donation_article d ON a.article_id = d.article_id "
				+"WHERE a.article_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {article_id});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if(rs.next()) {
				AnimalArticle article = new AnimalArticle(
												article_id,
												rs.getString("title"), 
												rs.getString("category"), 
												rs.getString("deadline"), 
												rs.getString("bank_name"), 
												rs.getString("acc_holder"), 
												rs.getString("acc_num"),
												rs.getString("due_date"),
												rs.getString("use_plan"), 
												rs.getString("other_text"),
												rs.getString("create_date"), 
												rs.getString("update_date"), 
												rs.getString("receipt_check"),
												rs.getString("user_id"), 
												rs.getInt("total_amount"),
												rs.getString("name"), 
												rs.getString("area"),
												rs.getString("type"), 
												rs.getString("age"),
												rs.getString("weight"), 
												rs.getString("gender"), 
												rs.getString("neutering"),
												rs.getString("current_status"), 
												rs.getString("health_status"),
												rs.getString("personality")
												);
				return article;
			}
		}catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}	
		return null;
	}
	
	public int update(AnimalArticle article) throws SQLException{
		try {
			String update1 = "UPDATE DONATION_ARTICLE "
							+ "SET title=?, due_date=?, use_plan=?, other_text=?, update_date = SYSDATE, receipt_check=? "
							+ "WHERE article_id=? ";
			Object[] param1 = new Object[] {article.getTitle(), article.getDueDate(), article.getUsePlan(),
					article.getOtherText(), article.getReceiptCheck(), article.getArticleId()};
			jdbcUtil.setSqlAndParameters(update1, param1);
			jdbcUtil.executeUpdate();
			
			String update2 = "UPDATE ANIMAL_ARTICLE "
							+ "SET name=?, area=?, type=?, age=?, weight=?, gender=?, neutering=?, current_status=?, health_status=?, personality=? "
							+ "WHERE article_id=? ";
			Object[] param2 = new Object[] {article.getName(), article.getArea(), article.getType(), 
					article.getAge(), article.getWeight(), article.getGender(), article.getNeutering(),
					article.getCurrentStatus(), article.getHealthStatus(), article.getPersonality(), article.getArticleId()};
			jdbcUtil.setSqlAndParameters(update2, param2);
			jdbcUtil.executeUpdate();
//			jdbcUtil.commit();
			
			return 1;
		}catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}
		return 0;
	}
}
