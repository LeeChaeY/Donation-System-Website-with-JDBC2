package model;

public class DonationImage
{
    private int articleId;
    private int fileOrder;
    private String fileName;
    DonationArticle article;
    
    public DonationImage() {
        super();
    }

    public DonationImage(int articleId, int fileOrder, String fileName, DonationArticle article) {
        super();
        this.articleId = articleId;
        this.fileOrder = fileOrder;
        this.fileName = fileName;
        this.article = article;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getFileOrder() {
        return fileOrder;
    }

    public void setFileOrder(int fileOrder) {
        this.fileOrder = fileOrder;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public DonationArticle getArticle() {
        return article;
    }

    public void setArticle(DonationArticle article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "DonationImage [articleId=" + articleId + ", fileOrder=" + fileOrder + ", fileName=" + fileName
                + ", article=" + article + "]";
    }

}
