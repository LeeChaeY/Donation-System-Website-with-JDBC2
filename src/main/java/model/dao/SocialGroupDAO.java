package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.DonationImage;
import model.SocialGroupArticle;

public class SocialGroupDAO {
    private JDBCUtil jdbcUtil = null; // JDBCUtil 참조 변수 선언

    public SocialGroupDAO() { // 생성자
        jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성 및 활용
    }

    public int create(SocialGroupArticle article, DonationImage image) throws SQLException {
        try {
            //DONATION_ARTICLE
            String sql1 = "INSERT INTO DONATION_ARTICLE VALUES (seq_donation_article_id.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, ?, ?, ?)";     
            Object[] param1 = new Object[] {article.getTitle(),
                    article.getCategory(), article.getDeadline(),
                    article.getBankName(), article.getAccHolder(),
                    article.getAccNum(), article.getIdCheck(),
                    article.getDueDate(), article.getUsePlan(),
                    article.getOtherText(), article.getUpdateDate(),
                    article.getReceiptCheck(), article.getTotalAmount(), article.getUserId()};             
            jdbcUtil.setSqlAndParameters(sql1, param1);   // JDBCUtil 에 insert문과 매개 변수 설정
            String key[]= {"article_id"}; // DONATION_ARTICLE PK
            int result = jdbcUtil.executeUpdate(key);
            //System.out.println("Doantion_Article table " + result + "개 등록 성공");
            
            //SOCIALGROUP_ARTICLE
            ResultSet rs = jdbcUtil.getGeneratedKeys(); // 생성된 PK 값을 포함한 result set 객체 반환
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1); // PK 값을 읽음
                String sql2 = "INSERT INTO SOCIALGROUP_ARTICLE VALUES (?, ?, ?, ?, ?, ?)";
                Object[] param2 = new Object[] {article.getAge(), article.getGender(),
                        article.getType(),article.getArea(), article.getSituation(),
                        generatedKey};
                jdbcUtil.setSqlAndParameters(sql2, param2); // JDBCUtil 에 insert문과 매개 변수 설정
                result = jdbcUtil.executeUpdate(); // insert 문 실행
                //System.out.println("SocialGroup_Article table " + result + "개 등록 성공");
            
            //DONATION_IMAGE
            String sql3 = "INSERT INTO DONATION_IMAGE VALUES (?, ?, ?)";
            Object[] param3 = new Object[] {image.getImgLink(),
                    generatedKey, image.getImgOrder()};
            jdbcUtil.setSqlAndParameters(sql3, param3); // JDBCUtil 에 insert문과 매개 변수 설정
            result = jdbcUtil.executeUpdate(); // insert 문 실행
            System.out.println("DONATION_IMAGE table " + result + "개 등록 성공");
        }
            return generatedKey;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {     
            jdbcUtil.commit();
            jdbcUtil.close();   // resource 반환
        }       
        return 0;
    }
    
    public int update(SocialGroupArticle article) throws SQLException{
        try {
            String sql1 = "UPDATE DONATION_ARTICLE "
                            + "SET title=?, due_date=?, use_plan=?, other_text=?, update_date=?, receipt_check=? "
                            + "WHERE article_id=? ";
            Object[] param1 = new Object[] {article.getTitle(), 
                    article.getDueDate(), article.getUsePlan(),
                    article.getOtherText(), article.getUpdateDate(),
                    article.getReceiptCheck(), article.getArticleId()};
            jdbcUtil.setSqlAndParameters(sql1, param1);
            int result = jdbcUtil.executeUpdate();
            //System.out.println("DONATION_ARTICLE table " + result + "개 수정 성공");

            String sql2 = "UPDATE SOCIALGROUP_ARTICLE "
                            + "SET area=?, gender=?, type=?, area=?, situation=? "
                            + "where article_id=? ";
            Object[] param2 = new Object[] {article.getArea(), article.getGender(),
                    article.getArea(), article.getSituation(), article.getArticleId()};
            jdbcUtil.setSqlAndParameters(sql2, param2);
            result = jdbcUtil.executeUpdate();
            //System.out.println("SOCIALGROUP_ARTICLE table " + result + "개 수정 성공");

            return 1;
        }catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        }
        finally {
            jdbcUtil.commit();
            jdbcUtil.close();   // resource 반환
        }
        return 0;
    }
    
    public SocialGroupArticle findSocialGroupArticle(int article_id) throws SQLException{
        String sql = "SELECT title, cateory, deadline, bank_name, acc_holder, acc_num, due_date, use_plan, other_text, create_date, update_date, receipt_check, total_amount, user_id, "
              + "age, gender, type, area, situation "
              + "FROM animal_article a JOIN donation_article d ON a.article_id = d.article_id "
              + "WHERE a.article_id=? ";
        jdbcUtil.setSqlAndParameters(sql, new Object[] {article_id});
        try {
           ResultSet rs = jdbcUtil.executeQuery();
           if(rs.next()) {
              SocialGroupArticle article = new SocialGroupArticle(
                      rs.getInt("article_id"),
                      rs.getString("title"), 
                      rs.getString("cateory"), 
                      rs.getString("deadline"), 
                      rs.getString("bank_name"), 
                      rs.getString("acc_holder"), 
                      rs.getString("acc_num"),
                      rs.getString("id_check"),
                      rs.getString("due_date"),
                      rs.getString("use_plan"), 
                      rs.getString("other_text"),
                      rs.getString("create_date"), 
                      rs.getString("update_date"), 
                      rs.getString("receipt_check"),
                      rs.getInt("total_amount"),
                      rs.getString("user_id"), 
                      rs.getInt("age"), 
                      rs.getString("gender"),
                      rs.getString("type"), 
                      rs.getString("area"),
                      rs.getString("situation")
                      );

              return article;
           }
        }catch (Exception ex) {
           jdbcUtil.rollback();
           ex.printStackTrace();
        } finally {      
           jdbcUtil.commit();
           jdbcUtil.close();   // resource 반환
        }   
        return null;
     }

    
    public int create1(SocialGroupArticle article) throws SQLException {
        String sql = "INSERT INTO DONATION_ARTICLE VALUES (seq_donation_article_id.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, ?, ?, ?)";     
        Object[] param = new Object[] {article.getTitle(), 
                article.getCategory(), article.getDeadline(),
                article.getBankName(), article.getAccHolder(),
                article.getAccNum(), article.getIdCheck(),
                article.getDueDate(), article.getUsePlan(),
                article.getOtherText(), article.getUpdateDate(),
                article.getReceiptCheck(), article.getTotalAmount(),
                article.getUserId()};             
        jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil 에 insert문과 매개 변수 설정
        try {               
            int result = jdbcUtil.executeUpdate();  // insert 문 실행
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {     
            jdbcUtil.commit();
            jdbcUtil.close();   // resource 반환
        }       
        return 0;
    }
    
    public int create2(SocialGroupArticle article) throws SQLException {
        // socialGroup_article(자식) table에 insert
        System.out.println("article_id출력:" + article.getArticleId());
        
        try {    
            String key[]= {"article_id"}; // DONATION_ARTICLE PK
            jdbcUtil.executeUpdate(key);
            ResultSet rs = jdbcUtil.getGeneratedKeys(); // 생성된 PK 값을 포함한 result set 객체 반환
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1); // PK 값을 읽음
                String sql = "INSERT INTO SOCIALGROUP_ARTICLE VALUES (?, ?, ?, ?, ?, ?)";
                Object[] param = new Object[] {article.getAge(), article.getGender(),
                        article.getType(),article.getArea(), article.getSituation(),
                        generatedKey};
                jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil 에 insert문과 매개 변수 설정
                int result = jdbcUtil.executeUpdate(); // insert 문 실행
                return result;
            }
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {     
            jdbcUtil.commit();
            jdbcUtil.close();   // resource 반환
        }       
        return 0;
    }
    
}