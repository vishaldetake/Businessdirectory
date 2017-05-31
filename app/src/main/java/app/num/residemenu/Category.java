package app.num.residemenu;

/**
 * Created by vishal on 5/30/2017.
 */

public class Category {
    String id;
    String cname;
    String img;

    public Category(String id, String cname, String img) {
        this.id = id;
        this.cname = cname;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
