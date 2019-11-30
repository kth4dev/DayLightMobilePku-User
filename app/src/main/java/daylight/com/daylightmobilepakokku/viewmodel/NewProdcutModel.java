package daylight.com.daylightmobilepakokku.viewmodel;

import java.io.Serializable;

public class NewProdcutModel implements Serializable {
    private String pnmae,pmodel,detail,imgurl,id;
    private int price;
    public NewProdcutModel(){}
    public NewProdcutModel(String id, String name, String model, int price, String detail, String imageurl){
        this.id=id;
        this.pnmae=name;
        this.pmodel=model;
        this.detail=detail;
        this.price=price;
        this.imgurl=imageurl;
    }

    public String getPnmae() {
        return pnmae;
    }

    public void setPnmae(String pnmae) {
        this.pnmae = pnmae;
    }

    public String getPmodel() {
        return pmodel;
    }

    public void setPmodel(String pmodel) {
        this.pmodel = pmodel;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
