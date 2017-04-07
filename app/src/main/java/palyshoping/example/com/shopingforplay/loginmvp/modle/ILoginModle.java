package palyshoping.example.com.shopingforplay.loginmvp.modle;

import android.os.Handler;

import palyshoping.example.com.shopingforplay.loginmvp.CallBack;

/**
 * Created by Administrator on 2017/4/7/007.
 */

public class ILoginModle implements LoginModle {

    @Override
    public void login(final String name, final String password, final CallBack callBack) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (name.equals("admin")&&password.equals("111111")){
                    callBack.onSuccess();
                }else callBack.onFailed("账户密码错误");
            }
        },2000);
    }
}
