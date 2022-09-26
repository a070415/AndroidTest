package com.example.shopping;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface RetrofitAPI {

    @GET("api/sample")
    Call<List<Data>> getPost();

    @POST("api/communitytest")
    Call<String> createPost(@Body Data data);

    @PUT("api/communitytest/{textid}")
    Call<String> putPost(@Path("textid") String id, @Body Data data);

    @DELETE("api/communitytest/{textid}")
    Call<String> deletePost(@Path("textid") String id);

}
