package palyshoping.example.com.shopingforplay.loginmvp.presenter;

import palyshoping.example.com.shopingforplay.loginmvp.view.BaseView;

/**
 * Created by Administrator on 2017/4/7/007.
 */

    public interface BasePresenter < V extends BaseView> {

    void detachView(V view);
    void detachView();
}
