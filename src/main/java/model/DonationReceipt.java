package model;

import java.util.Date;

public class DonationReceipt {
	private int receiptId;
	private String content;
	private Date createDate;
	private Date updateDate;
	private int articleId;
	
	public DonationReceipt() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DonationReceipt(int receiptId, String content, Date createDate, Date updateDate, int articleId) {
		super();
		this.receiptId = receiptId;
		this.content = content;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.articleId = articleId;
	}
	
	public int getReceiptId() {
		return receiptId;
	}
	
	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Date getUpdateDate() {
		return updateDate;
	}
	
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public int getArticleId() {
		return articleId;
	}
	
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	
	
}
