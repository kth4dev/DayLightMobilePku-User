package daylight.com.daylightmobilepakokku.viewmodel;

public class Categories {
    public String cname,imgurl;
    public Categories(){}

    public Categories(String cname, String imgurl){
        this.cname=cname;
        this.imgurl=imgurl;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
