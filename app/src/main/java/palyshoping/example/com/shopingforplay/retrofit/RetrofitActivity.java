package palyshoping.example.com.shopingforplay.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.schedulers.RxThreadFactory;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import palyshoping.example.com.shopingforplay.R;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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


        String baseUrl = "http://guolin.tech/api/china/";
        Retrofit retrofitProvince=new Retrofit.Builder().
                baseUrl(baseUrl).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        GitHubService gitHubService = retrofitProvince.create(GitHubService.class);
        Call<List<Province>> province = gitHubService.getProvince("");
        province.enqueue(new Callback<List<Province>>() {
            @Override
            public void onResponse(Call<List<Province>> call, retrofit2.Response<List<Province>> response) {
                List<Province> s = response.body();
                Log.e(TAG, "onResponse:===>>> "+ s.get(s.size()-1));



            }

            @Override
            public void onFailure(Call<List<Province>> call, Throwable t) {

            }
        });


        final Retrofit retrofit=new Retrofit.Builder().baseUrl(baidu).build();
        AppUrl appUrl = retrofit.create(AppUrl.class);
        Call<ResponseBody> index = appUrl.getIndex();
        index.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                Log.e(TAG, "onResponse:=== "+response.body().toString() );
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        Retrofit retrofit1=new Retrofit.Builder().
                baseUrl("http://guolin.tech/api/").
                addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                build();
        CityInterface cityInterface = retrofit1.create(CityInterface.class);
        Observable<City> cityDta = cityInterface.
                getCityDta("CN101190401", "dc918c185cf6471ca80a571edd569224");
        cityDta.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<City>() {
                    @Override
                    public void accept(@NonNull City city) throws Exception {
                        Log.e(TAG, "accept: "+ city.toString() );
                    }
                });


        Retrofit retrofit2=new Retrofit.Builder().baseUrl("http://guolin.tech/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CityInterface cityInterface1 = retrofit2.create(CityInterface.class);
        Call<City> cityDta2 = cityInterface1.getCityDta2("CN101190401", "dc918c185cf6471ca80a571edd569224");
        cityDta2.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, retrofit2.Response<City> response) {
                Log.e(TAG, "onResponse: "+response.body() );
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {

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
