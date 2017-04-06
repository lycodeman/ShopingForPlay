package palyshoping.example.com.shopingforplay;

/**
 * Created by Administrator on 2017/4/6/006.
 */

public class Course {

    String china;
    String english;
    String activity;

    @Override
    public String toString() {
        return "Course{" +
                "china='" + china + '\'' +
                ", english='" + english + '\'' +
                ", activity='" + activity + '\'' +
                '}';
    }

    public String getChina() {
        return china;
    }

    public void setChina(String china) {
        this.china = china;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
