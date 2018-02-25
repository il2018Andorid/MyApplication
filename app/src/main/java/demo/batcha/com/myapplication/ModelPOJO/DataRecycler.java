package demo.batcha.com.myapplication.ModelPOJO;

/**
 * Created by vimoxpc on 25-Feb-18.
 */

public class DataRecycler {

    String Name,title;
    Integer imageID;

    public DataRecycler(String name, String title, Integer imageID) {
        Name = name;
        this.title = title;
        this.imageID = imageID;
    }

    public DataRecycler(String name, Integer imageID) {
        Name = name;
        this.imageID = imageID;
    }

    public DataRecycler(String name) {
        Name = name;
    }

    public String getName() {
        return Name;

    }

    public void setName(String name) {
        Name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getImageID() {
        return imageID;
    }

    public void setImageID(Integer imageID) {
        this.imageID = imageID;
    }
}
