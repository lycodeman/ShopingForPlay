package palyshoping.example.com.shopingforplay.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import palyshoping.example.com.shopingforplay.R;

public class RetrofitActivity extends AppCompatActivity {

    private static final String TAG = "RetrofitActivity";
    @InjectView(R.id.rxbus)
    Button rxbus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.inject(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url("http://www.jianshu.com/search?q=Retrofit&page=1&type=note").build();
                try {
                    Response execute = okHttpClient.newCall(request).execute();
                    final String string = execute.body().string();
                    Log.e(TAG, "onCreate: " + string);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RetrofitActivity.this, string,Toast.LENGTH_LONG).show();
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.rxbus)
    public void onClick() {
    }
}
