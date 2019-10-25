package com.example.timetableapp.Retrofit;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.Callback;

public interface INodeJS {
        @POST("login")
        @FormUrlEncoded
        Observable <String> userLogin(
                @Field("add_student") String student,
                @Field("add_password") String password
        );

        @POST("signup")
        @FormUrlEncoded
        Observable <String> userRegister(
                @Field("add_student") String student,
                @Field("add_password") String password,
                @Field("add_email") String email
        );

        @GET("class/{student}")
        Call <List<ClassModel>> getClassInfo(@Path("student")String student);

        @POST("modules")
        @FormUrlEncoded
        Observable <String> addModule(
                @Field("add_student") String student,
                @Field("add_module") String module
                );

        @POST("deleteModule")
        @FormUrlEncoded
        Observable <String> deleteModule(
                @Field("add_student") String student,
                @Field("add_module") String module
        );

}
