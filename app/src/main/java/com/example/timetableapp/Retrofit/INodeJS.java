package com.example.timetableapp.Retrofit;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface INodeJS {
        @POST("login")
        @FormUrlEncoded
        Observable <String> userLogin(
                @Field("add_student") String student,
                @Field("add_password") String password
        );

        @POST("signup")
        @FormUrlEncoded
        Observable <String> userLogin(
                @Field("add_student") String student,
                @Field("add_password") String password,
                @Field("add_email") String email
        );

}
