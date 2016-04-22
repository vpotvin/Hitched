package edu.uco.weddingcrashers.hitched;

/**
 * Created by KALIDY12 on 2/18/2016.
 */
public class HoneymoonItem {
    private String Name;
    private Integer img;
    private String Description;

    public HoneymoonItem(String name, Integer img, String description) {
        Name = name;
        this.img = img;
        Description = description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}