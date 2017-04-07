package palyshoping.example.com.shopingforplay.loginmvp.modle;

import palyshoping.example.com.shopingforplay.loginmvp.CallBack;

/**
 * Created by Administrator on 2017/4/7/007.
 */

public interface LoginModle {

    void login(String name, String password, CallBack callBack);
}
