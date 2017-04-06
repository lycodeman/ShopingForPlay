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
import okhttp3.ResponseBody;
import palyshoping.example.com.shopingforplay.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class RetrofitActivity extends AppCompatActivity {

    private static final String TAG = "RetrofitActivity";
    @InjectView(R.id.rxbus)
    Button rxbus;
    private String url;
    private String baidu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.inject(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                url = "http://www.jianshu.com/search?q=Retrofit&page=1&type=note";
                Request request = new Request.Builder().url(url).build();
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
        baidu = "http://www.baidu.com/";
       new Retrofit.Builder().baseUrl(baidu).build().create(AppUrl.class).getIndex().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        final Retrofit retrofit=new Retrofit.Builder().baseUrl(baidu).build();
        AppUrl appUrl = retrofit.create(AppUrl.class);
        Call<ResponseBody> index = appUrl.getIndex();
        index.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                Log.e(TAG, "onResponse: "+response.toString() );
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.rxbus)
    public void onClick() {
    }
}
