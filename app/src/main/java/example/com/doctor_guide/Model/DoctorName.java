package example.com.doctor_guide.Model;

/**
 * Created by Khodal on 10/08/2017.
 */

public class DoctorName {
    private String name, address,l_id,sub_id,imageurl,mname,lname;

    public String getDoctor_l_id() {
        return doctor_l_id;
    }

    public void setDoctor_l_id(String doctor_l_id) {
        this.doctor_l_id = doctor_l_id;
    }

    public  String doctor_l_id;
    public DoctorName() {
    }

    public DoctorName(String name, String address,String l_id,String sub_id,String imageurl,String mname,String lname) {
        this.name= name;
        this.address = address;
        this.l_id = l_id;
        this.sub_id = sub_id;
        this.imageurl = imageurl;
        this.mname = mname;
        this.lname = lname;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }


    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }



    public String getL_id() {
        return l_id;
    }

    public void setL_id(String sub_id) {
        this.l_id = l_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

}
