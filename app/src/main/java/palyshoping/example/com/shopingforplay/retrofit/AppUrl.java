package palyshoping.example.com.shopingforplay.retrofit;

import android.telecom.CallScreeningService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017/4/6/006.
 */

public interface AppUrl {

    @GET("index")
    Call<ResponseBody> getIndex();
}
