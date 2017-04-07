package palyshoping.example.com.shopingforplay.mvp;

import io.reactivex.Observable;
import palyshoping.example.com.shopingforplay.mvp.modle.BaseModleBean;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/4/7/007.
 */

public interface WeatherInterface {
    @GET("weather")
    Observable<BaseModleBean> getWearher(@Query("cityid")String cityId,@Query("key")String key);
}
