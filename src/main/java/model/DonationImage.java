package model;

public class DonationImage
{
    private String imgLink;
    private int imgOrder;
    private int articleId;
    DonationArticle article; // article 객체 먼저 생성..?
    
    public DonationImage() { }

    public DonationImage(String imgLink, int imgOrder, int articleId, DonationArticle article) {
        this.imgLink = imgLink;
        this.imgOrder = imgOrder;
        this.articleId = articleId;
        this.article = article;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public int getImgOrder() {
        return imgOrder;
    }

    public void setImgOrder(int imgOrder) {
        this.imgOrder = imgOrder;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        return "DonationImage [imgLink=" + imgLink + ", imgOrder=" + imgOrder + ", articleId=" + articleId + "]";
    }
    
}