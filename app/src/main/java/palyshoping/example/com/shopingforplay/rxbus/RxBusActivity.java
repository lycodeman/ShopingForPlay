package palyshoping.example.com.shopingforplay.rxbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.thread.EventThread;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import palyshoping.example.com.shopingforplay.R;

public class RxBusActivity extends AppCompatActivity {

    @InjectView(R.id.rxbus)
    Button rxbus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_bus);
        ButterKnife.inject(this);
        RxBus.get().register(this);
        RxBus.get().post(new RxBusEvent("Bus"));
    }

    @Subscribe()
    public void getData(RxBusEvent rxBusEvent) {
        Toast.makeText(this, rxBusEvent.getCode(), Toast.LENGTH_LONG).show();
        Log.e("=====", "getData: " + rxBusEvent.getCode());
        Log.e(TAG, "getData: " + Thread.currentThread());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }

    private static final String TAG = "RxBusActivity";

    @Subscribe(thread = EventThread.MAIN_THREAD)
    public void getDataMain(RxBusEvent event) {
//        Toast.makeText(this,event.getCode(),Toast.LENGTH_LONG).show();
        Log.e(TAG, "getData: " + Thread.currentThread());
    }

    @Subscribe(thread = EventThread.IO)
    public void getDataThread(RxBusEvent event) {
        Log.e(TAG, "getData: " + Thread.currentThread());
    }

    @Subscribe(thread = EventThread.EXECUTOR)
    public void getDataThread1(RxBusEvent event) {
        Log.e(TAG, "getData: " + Thread.currentThread());
    }

    @Subscribe(thread = EventThread.NEW_THREAD)
    public void getDataThread2(RxBusEvent event) {
        Log.e(TAG, "getData: " + Thread.currentThread());
    }

    @OnClick(R.id.rxbus)
    public void onClick() {
        RxBus.get().post(new RxBusEvent("code"));
    }
}
