package palyshoping.example.com.shopingforplay.mvp.modle;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import palyshoping.example.com.shopingforplay.mvp.WeatherInterface;
import palyshoping.example.com.shopingforplay.mvp.presenter.IBasePresenter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/4/7/007.
 */

public class BaseModle {
    IBasePresenter basePresenter;

    public BaseModle(IBasePresenter basePresenter) {
        this.basePresenter = basePresenter;
    }

    public void loadData(){
        Retrofit retrofit=new Retrofit.Builder().
                baseUrl("http://guolin.tech/api/").
                addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                build();
        WeatherInterface weatherInterface = retrofit.create(WeatherInterface.class);
        weatherInterface.getWearher("CN101190401","dc918c185cf6471ca80a571edd569224").
                subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Consumer<BaseModleBean>() {
                    @Override
                    public void accept(@NonNull BaseModleBean modleBean) throws Exception {
                        Log.e("======", "accept: "+modleBean.toString() );
                        basePresenter.loadSuccess(modleBean);
                    }
                });
    };
}
