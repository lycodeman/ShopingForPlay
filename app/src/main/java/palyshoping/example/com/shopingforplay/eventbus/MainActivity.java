package palyshoping.example.com.shopingforplay.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import palyshoping.example.com.shopingforplay.R;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.button)
    Button button;
    @InjectView(R.id.button2)
    Button button2;
    @InjectView(R.id.button3)
    Button button3;
    @InjectView(R.id.button4)
    Button button4;
    @InjectView(R.id.textView2)
    TextView textView2;
    @InjectView(R.id.activity_main)
    RelativeLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        EventBus.getDefault().register(this);
        OttooBus.getBus().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        OttooBus.getBus().unregister(this);
    }

    @OnClick({R.id.button, R.id.button2, R.id.button3,R.id.button4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                EventBus.getDefault().postSticky(new MessegeEvent(0));
                break;
            case R.id.button2:
                startActivity(new Intent(this,Main2Activity.class));
                break;
            case R.id.button3:
                OttooBus.getBus().post(new OttoEvnet("Send OttoBus"));
                break;
            case R.id.button4:
                EventBus.getDefault().postSticky(new MessegeEvent(1));
                break;
        }
    }

    private static final String TAG = "MainActivity";

    @Subscribe
    public void getOttoBus(OttoEvnet ottoEvnet){
        Log.e(TAG, "getOttoBus: "+ottoEvnet.getStr() );
    }

    /**
     * 主线程中执行
     * @param
     */
   /* @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onMainEventBus(MessegeEvent even) {
        textView2.setText(even.getMessege()+"");
        Log.e("===Main", Thread.currentThread().getName());
    }

    *//**
     * 后台线程
     * @param
     *//*
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onBackgroundEventBus(MessegeEvent even) {
        Log.e("===OnBack", Thread.currentThread().getName());
    }

    *//**
     * 异步线程
     *
     * @param
     *//*
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onAsyncEventBus(MessegeEvent even) {
        Log.e("===onAsy", Thread.currentThread().getName());
    }

    *//**
     * 默认情况，和发送事件在同一个线程
     *
     * @param
     *//*
    @Subscribe(threadMode = ThreadMode.POSTING,priority = 100)
    public void onPostEventBus(MessegeEvent even) {
        Log.e("===onPost", Thread.currentThread().getName());
//        EventBus.getDefault().cancelEventDelivery(even);
    }*/


}
