package palyshoping.example.com.shopingforplay.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2017/4/6/006.
 */

public interface GitHubService {
    @GET("{name}")
    Call<List<Province>> getProvince(@Path("name")String name);
//    @POST()
//    Call<>
}
