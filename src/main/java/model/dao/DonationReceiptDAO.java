package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.DonationReceipt;

public class DonationReceiptDAO {
	private JDBCUtil jdbcUtil = null;
	
	public DonationReceiptDAO() {
		jdbcUtil = new JDBCUtil();
	}

	//	후원 인증 작성 시, DonationReceipt table에 튜플 삽입, 후원 인증에 대한 DonationReceipt 객체 입력
	public int create(DonationReceipt receipt) throws SQLException {
		try {
			String insert = "INSERT INTO DONATION_RECEIPT VALUES (seq_receipt_id.nextval, ?, SYSDATE, NULL, ?)";		
			Object[] param = new Object[] {receipt.getContent(), receipt.getArticleId()};
			
			jdbcUtil.setSqlAndParameters(insert, param);	// JDBCUtil 에 insert문과 매개 변수 설정
			jdbcUtil.executeUpdate();
		
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 1;			
	}
	
	//	receiptId로 후원 인증글 찾기, receiptId로 질의(입력)
	public DonationReceipt findByReceiptId(int receiptId) {
		try {
			String query = "SELECT receipt_id, content, create_date, update_date, article_id "
					+ "FROM DONATION_RECEIPT "
					+ "WHERE receipt_id = ?";
	    	
	    	Object[] param = new Object[] {receiptId};
	    	jdbcUtil.setSqlAndParameters(query, param);
	    	
			ResultSet rs = jdbcUtil.executeQuery();
			DonationReceipt receipt = new DonationReceipt();
			
			if (rs.next()) {
				receipt.setReceiptId(rs.getInt("receipt_id"));
				receipt.setContent(rs.getString("content"));
				receipt.setCreateDate(rs.getDate("create_date"));
				receipt.setUpdateDate(rs.getDate("update_date"));
				receipt.setArticleId(rs.getInt("article_id"));
			}
			return receipt;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		} return null;
	}
	
	//	articleId로 후원 인증글 찾기, articleId로 질의(입력)
	public DonationReceipt findByArticleId(int articleId) {
		try {
			String query = "SELECT receipt_id, content, create_date, update_date, article_id "
					+ "FROM DONATION_RECEIPT "
					+ "WHERE article_id = ?";
	    	
	    	Object[] param = new Object[] {articleId};
	    	jdbcUtil.setSqlAndParameters(query, param);
	    	
			ResultSet rs = jdbcUtil.executeQuery();
			DonationReceipt receipt = new DonationReceipt();
			
			if (rs.next()) {
				receipt.setReceiptId(rs.getInt("receipt_id"));
				receipt.setContent(rs.getString("content"));
				receipt.setCreateDate(rs.getDate("create_date"));
				receipt.setUpdateDate(rs.getDate("update_date"));
				receipt.setArticleId(rs.getInt("article_id"));
			}
			return receipt;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		} return null;
	}
	
	//	후원 인증글 수정, id나 createDate는 수정되지 않고 content, updateDate만 수정, 수정한 DonationReceipt 객체 입력
	public int update(DonationReceipt receipt) throws SQLException {
		try {
			String update = "UPDATE DONATION_RECEIPT SET (content, update_date) = (?, ?) WHERE receipt_id = ?";		
			Object[] param = new Object[] {receipt.getContent(), receipt.getUpdateDate(), receipt.getReceiptId()};
			
			jdbcUtil.setSqlAndParameters(update, param);	// JDBCUtil 에 insert문과 매개 변수 설정
			jdbcUtil.executeUpdate();
		
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 1;			
	}
	
	//	후원 인증글 삭제, donation_article이 삭제되면 같이 삭제되므로 article_id로 삭제(입력)
	public int remove(int articleId) throws SQLException {
		try {
			String remove = "DELETE FROM DONATION_RECEIPT WHERE receipt_id = ?";		
			Object[] param = new Object[] {articleId};
			
			jdbcUtil.setSqlAndParameters(remove, param);	// JDBCUtil 에 insert문과 매개 변수 설정
			jdbcUtil.executeUpdate();
		
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 1;			
	}

}
