package example.com.doctor_guide.Model;

/**
 * Created by raju on 23-09-2017.
 */

public class RatingModel {
    String dr_id;
    String d_id;

    public String getBrocher_pdf_name() {
        return brocher_pdf_name;
    }

    public void setBrocher_pdf_name(String brocher_pdf_name) {
        this.brocher_pdf_name = brocher_pdf_name;
    }

    String brocher_pdf_name;

    public String getBrocher_path() {
        return brocher_path;
    }

    public void setBrocher_path(String brocher_path) {
        this.brocher_path = brocher_path;
    }

    String brocher_path;

    public String getDr_id() {
        return dr_id;
    }

    public void setDr_id(String dr_id) {
        this.dr_id = dr_id;
    }

    public String getD_id() {
        return d_id;
    }

    public void setD_id(String d_id) {
        this.d_id = d_id;
    }

    public String getDr_name() {
        return dr_name;
    }

    public void setDr_name(String dr_name) {
        this.dr_name = dr_name;
    }

    public String getDr_contact() {
        return dr_contact;
    }

    public void setDr_contact(String dr_contact) {
        this.dr_contact = dr_contact;
    }

    public String getDr_desc() {
        return dr_desc;
    }

    public void setDr_desc(String dr_desc) {
        this.dr_desc = dr_desc;
    }

    public String getDr_rating() {
        return dr_rating;
    }

    public void setDr_rating(String dr_rating) {
        this.dr_rating = dr_rating;
    }

    String dr_name;
    String dr_contact;
    String dr_desc;
    String dr_rating;
}
