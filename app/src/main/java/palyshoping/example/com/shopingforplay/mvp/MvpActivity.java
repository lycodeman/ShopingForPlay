package palyshoping.example.com.shopingforplay.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import palyshoping.example.com.shopingforplay.R;
import palyshoping.example.com.shopingforplay.mvp.modle.BaseModle;
import palyshoping.example.com.shopingforplay.mvp.modle.BaseModleBean;
import palyshoping.example.com.shopingforplay.mvp.presenter.Presenter;
import palyshoping.example.com.shopingforplay.mvp.view.BaseView;

public class MvpActivity extends AppCompatActivity implements BaseView {

    @InjectView(R.id.mvp_button)
    Button mvpButton;
    @InjectView(R.id.mvp_tv)
    TextView mvpTv;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        ButterKnife.inject(this);
        initView();
        presenter.attachView(this);
    }

    private void initView() {
        presenter=new Presenter(this);
        presenter.loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void showData(BaseModleBean modleBean) {
//        presenter.loadData();
        mvpTv.setText(modleBean.getHeWeather().get(0).getBasic().toString());
    }

    @OnClick(R.id.mvp_button)
    public void onClick() {
    }
}
