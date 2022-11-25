package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.DisasterArticle;

public class DisasterDAO {
	// DisasterDao

	private JDBCUtil jdbcUtil = null;

	public DisasterDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
	}

	public int create(DisasterArticle disaster) throws SQLException {
		try {
			String insert1 = "INSERT INTO DONATION_ARTICLE VALUES (seq_donation_disaster_id.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, ?, ?, ?)";

			Object[] param1 = new Object[] { disaster.getTitle(), disaster.getCategory(), disaster.getDeadline(),
					disaster.getBankName(), disaster.getAccHolder(), disaster.getAccNum(), disaster.getIdCheck(),
					disaster.getDueDate(), disaster.getUsePlan(), disaster.getOtherText(), disaster.getUpdateDate(),
					disaster.getReceiptCheck(), disaster.getTotalAmount(), disaster.getUserId() };

//			System.out.println("DisasterDao" + disaster);

			jdbcUtil.setSqlAndParameters(insert1, param1);

			String key[] = { "disaster_id" };

			jdbcUtil.executeUpdate(key);

			ResultSet rs = jdbcUtil.getGeneratedKeys();

			int generatedKey = 0;

			if (rs.next()) {
				generatedKey = rs.getInt(1);

				String insert2 = "INSERT INTO Disaster_ARTICLE VALUES (?, ?, ?, ?, ?, ?)";
				Object[] param2 = new Object[] { disaster.getType(), disaster.getName(), disaster.getArea(),
						disaster.getDamageAmount(), disaster.getSituation(), generatedKey };

				jdbcUtil.setSqlAndParameters(insert2, param2);
				jdbcUtil.executeUpdate();
			}

			return generatedKey;

		} catch (Exception e) {
			// TODO: handle exception
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}

		return -1;
	}

	public DisasterArticle findDisasterArticle(int article_id) throws SQLException {
		String sql = "SELECT title, cateory, deadline, bank_name, acc_holder, acc_num, due_date, use_plan, other_text, create_date, update_date, receipt_check, user_id, total_amount, type, name, area, damage_amount, situation, disaster_id "
				+ "FROM disaster_article a JOIN donation_article d ON a.disaster_id = d.disaster_id "
				+ "WHERE a.disaster_id=? ";

		jdbcUtil.setSqlAndParameters(sql, new Object[] { article_id });

		try {
			ResultSet rs = jdbcUtil.executeQuery();

			if (rs.next()) {
				DisasterArticle article = new DisasterArticle(article_id, rs.getString("title"),
						rs.getString("category"), rs.getString("deadline"), rs.getString("bank_name"),
						rs.getString("acc_holder"), rs.getString("acc_num"), rs.getString("due_date"),
						rs.getString("use_plan"), rs.getString("other_text"), rs.getString("create_date"),
						rs.getString("update_date"), rs.getString("receipt_check"), rs.getString("user_id"),
						rs.getInt("total_amount"), rs.getString("type"), rs.getString("name"), rs.getString("area"),
						rs.getInt("damage_amount"), rs.getString("situation"));

				return article;
			}
		} catch (Exception e) {
			// TODO: handle exception
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}

		return null;
	}

	public int update(DisasterArticle article) throws SQLException {
		try {
			String update1 = "UPDATE DONATION_ARTICLE "
					+ "SET title=?, due_date=?, use_plan=?, other_text=?, update_date=?, receipt_check=? "
					+ "WHERE article_id=? ";

			Object[] param1 = new Object[] { article.getTitle(), article.getDueDate(), article.getUsePlan(),
					article.getOtherText(), article.getUpdateDate(), article.getReceiptCheck(),
					article.getArticleId() };

			jdbcUtil.setSqlAndParameters(update1, param1);

			int result1 = jdbcUtil.executeUpdate();

			String update2 = "UPDATE ANIMAL_ARTICLE "
					+ "SET type = ?, name = ?, area = ?, damage_amount = ?, situation = ? " + "where article_id=1? ";

			Object[] param2 = new Object[] { article.getType(), article.getName(), article.getArea(),
					article.getDamageAmount(), article.getSituation() };

			jdbcUtil.setSqlAndParameters(update2, param2);

			int result2 = jdbcUtil.executeUpdate();

			return result1 + result2;
		} catch (Exception e) {
			// TODO: handle exception
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}

		return 0;
	}
}