package palyshoping.example.com.shopingforplay.loginmvp.view;

/**
 * Created by Administrator on 2017/4/7/007.
 */

public interface LoginView extends BaseView{
    String getUserName();
    String getPassWord();
    void showResult(String result);
}
