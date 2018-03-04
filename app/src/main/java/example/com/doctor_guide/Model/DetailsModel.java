package example.com.doctor_guide.Model;


public class DetailsModel {
    private String name, award_acchive, award_date, award_from;

    public String getDoctor_gallery_image() {
        return doctor_gallery_image;
    }

    public void setDoctor_gallery_image(String doctor_gallery_image) {
        this.doctor_gallery_image = doctor_gallery_image;
    }

    String doctor_gallery_image;

    public DetailsModel() {
    }

    public DetailsModel(String name, String award_acchive, String award_date, String award_from) {
        this.name = name;
        this.award_acchive = award_acchive;
        this.award_date = award_date;
        this.award_from = award_from;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAward_acchive() {
        return award_acchive;
    }

    public void setAward_acchive(String award_acchive) {
        this.award_acchive = award_acchive;
    }

    public String getAward_date() {
        return award_date;
    }

    public void setAward_date(String award_date) {
        this.award_date = award_date;
    }

    public String getAward_from() {
        return award_from;
    }

    public void setAward_from(String award_from) {
        this.award_from = award_from;
    }

}
