package palyshoping.example.com.shopingforplay.mvp.presenter;

import palyshoping.example.com.shopingforplay.mvp.modle.BaseModleBean;

/**
 * Created by Administrator on 2017/4/7/007.
 */

public interface IBasePresenter {
    void loadSuccess(BaseModleBean modleBean);
    void loadFailed();
}
