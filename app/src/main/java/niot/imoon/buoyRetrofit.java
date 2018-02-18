package niot.imoon;

        import retrofit2.Call;
        import retrofit2.Retrofit;
        import retrofit2.converter.gson.GsonConverterFactory;
        import retrofit2.http.GET;

/**
 * Created by arnab on 1/19/2018.
 */

public interface buoyRetrofit {
    @GET("xml.php")
    Call<BuoyDataAdapter>buoy_data();
    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("http://www.niot.res.in/imoon/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}