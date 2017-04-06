package palyshoping.example.com.shopingforplay;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/4/6/006.
 */

public class Person implements Serializable {

    String name;
    int age;
    Course course;
    List<Course> courses;

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", course=" + course +
                ", courses=" + courses +
                '}';
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
