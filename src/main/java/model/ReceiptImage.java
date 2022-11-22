package model;

public class ReceiptImage
{
    String imgLink;
    int imgOrder;
    int receiptId;
    
    public ReceiptImage() {

    }

    public ReceiptImage(String imgLink, int imgOrder, int receiptId) {
        this.imgLink = imgLink;
        this.imgOrder = imgOrder;
        this.receiptId = receiptId;
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

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    @Override
    public String toString() {
        return "DonationImage [imgLink=" + imgLink + ", imgOrder=" + imgOrder + ", receiptId=" + receiptId + "]";
    }
    
}