package palyshoping.example.com.shopingforplay.retrofit;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/4/6/006.
 */

public interface CityInterface {
    @GET("weather")
    Observable<City> getCityDta(@Query("cityid")String cityName, @Query("key")String key);
    @GET("weather")
    Call<City> getCityDta2(@Query("cityid")String cityName, @Query("key")String key);
}
