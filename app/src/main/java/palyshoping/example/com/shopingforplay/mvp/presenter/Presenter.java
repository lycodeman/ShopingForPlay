package palyshoping.example.com.shopingforplay.mvp.presenter;

import palyshoping.example.com.shopingforplay.mvp.modle.BaseModle;
import palyshoping.example.com.shopingforplay.mvp.modle.BaseModleBean;
import palyshoping.example.com.shopingforplay.mvp.view.BaseView;

/**
 * Created by Administrator on 2017/4/7/007.
 */

public class Presenter implements IBasePresenter,BasePresenter<BaseView> {

    private BaseView baseView;
    private BaseModle baseModle;

    public Presenter(BaseView baseView) {
        attachView(baseView);
        baseModle=new BaseModle(this);
    }

    @Override
    public void loadSuccess(BaseModleBean modleBean) {
        baseView.showData(modleBean);
    }

    @Override
    public void loadFailed() {

    }

    @Override
    public void attachView(BaseView view) {
        this.baseView=view;
    }

    @Override
    public void detachView() {
        this.baseView=null;
    }

    public void loadData(){
        baseModle.loadData();

    }
}
