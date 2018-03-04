package example.com.doctor_guide.Model;

/**
 * Created by Khodal on 08/09/2017.
 */


public class FooterBtn {
    private String name,imageurl,description,sub_id;

    public FooterBtn() {
    }

    public FooterBtn(String name,String imageurl,String description,String sub_id) {
        this.name = name;
        this.imageurl = imageurl;
        this.description = description;
        this.sub_id = sub_id;



    }

    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id =sub_id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }



}
