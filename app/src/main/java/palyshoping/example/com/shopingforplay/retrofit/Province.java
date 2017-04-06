package palyshoping.example.com.shopingforplay.retrofit;

/**
 * Created by Administrator on 2017/4/6/006.
 */

public class Province {


    /**
     * id : 1
     * name : 北京
     */

    private int id;
    private String name;

    @Override
    public String toString() {
        return "Province{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
