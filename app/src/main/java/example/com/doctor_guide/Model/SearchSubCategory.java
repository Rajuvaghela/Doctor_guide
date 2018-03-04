package example.com.doctor_guide.Model;

/**
 * Created by Khodal on 30/08/2017.
 */

public class SearchSubCategory {
    private String subname,sub_id;

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }


    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }



    @Override
    //override toString() method for returning the fund names. This will show the fund names in the suggestion list.
    //If you want to show the fund ids replace fundName with fundId.
    public String toString() {
        return subname;
    }
}
