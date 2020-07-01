package or.techtown.doitmission_14;

public class ShopList {
    int resId;
    String itemName;
    String price;
    String itemInfo;

    public ShopList(String itemName, String price, String itemInfo) {
        this.itemName = itemName;
        this.price = price;
        this.itemInfo = itemInfo;
    }

    public ShopList(int resId, String itemName, String price, String itemInfo) {
        this.resId = resId;
        this.itemName = itemName;
        this.price = price;
        this.itemInfo = itemInfo;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(String itemInfo) {
        this.itemInfo = itemInfo;
    }
}
