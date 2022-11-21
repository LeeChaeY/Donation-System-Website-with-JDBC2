package model;

public class DonationImage
{
    String imgLink;
    int imgOrder;
    int articleId;
    
    public DonationImage() {

    }

    public DonationImage(String imgLink, int imgOrder, int articleId) {
        this.imgLink = imgLink;
        this.imgOrder = imgOrder;
        this.articleId = articleId;
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