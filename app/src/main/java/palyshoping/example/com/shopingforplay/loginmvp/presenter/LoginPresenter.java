package palyshoping.example.com.shopingforplay.loginmvp.presenter;


import palyshoping.example.com.shopingforplay.loginmvp.CallBack;
import palyshoping.example.com.shopingforplay.loginmvp.modle.ILoginModle;
import palyshoping.example.com.shopingforplay.loginmvp.view.BaseView;
import palyshoping.example.com.shopingforplay.loginmvp.view.LoginView;

/**
 * Created by Administrator on 2017/4/7/007.
 */

public class LoginPresenter implements  Presenter{

    private LoginView loginView;
    private ILoginModle loginModle;


    public LoginPresenter(LoginView loginView, ILoginModle loginModle) {
        this.loginView = loginView;
        this.loginModle = loginModle;
    }

    @Override
    public void login() {

        loginModle.login(loginView.getUserName(), loginView.getPassWord(), new CallBack() {
            @Override
            public void onSuccess() {
                loginView.hideDialog();
                loginView.showResult("登陆成功");
            }

            @Override
            public void onFailed(String error) {
                loginView.hideDialog();
                loginView.showResult(error);
            }
        });
    }

    @Override
    public void detachView(BaseView view) {
        loginView= (LoginView) view;
    }

    @Override
    public void detachView() {
        loginView=null;
    }
}
