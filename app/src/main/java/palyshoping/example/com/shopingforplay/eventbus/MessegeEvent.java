package palyshoping.example.com.shopingforplay.eventbus;

/**
 * Created by Administrator on 2017/4/5/005.
 */

public class MessegeEvent {

    int messege;

    public int getMessege() {
        return messege;
    }

    public void setMessege(int messege) {
        this.messege = messege;
    }

    public MessegeEvent(int messege) {
        this.messege = messege;
    }
}
