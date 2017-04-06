package palyshoping.example.com.shopingforplay.eventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.InjectView;
import palyshoping.example.com.shopingforplay.R;

public class Main2Activity extends AppCompatActivity {

    @InjectView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.inject(this);
        EventBus.getDefault().register(this);
    }

    private static final String TAG = "Main2Activity";
    @Subscribe(sticky = true)
    public void setTextViewText(MessegeEvent event){
        tv.setText(event.getMessege()+"");
        Log.e(TAG, "setTextViewText: " );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
