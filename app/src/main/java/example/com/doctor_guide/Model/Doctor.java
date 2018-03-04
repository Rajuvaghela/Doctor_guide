package example.com.doctor_guide.Model;

/**
 * Created by Khodal on 04/08/2017.
 */

public class Doctor {
    private String title, content,id,imageurl,sub_id;

    public Doctor() {
    }

    public Doctor(String title, String content,String imageurl,String sub_id) {
        this.title = title;
        this.content = content;
        this.imageurl = imageurl;
        this.id = id;
        this.sub_id = sub_id;


    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

}
