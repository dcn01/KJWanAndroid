package kylec.me.user.service

import kotlinx.coroutines.Deferred
import kylec.me.base.httpmodule.HttpReturnType
import kylec.me.base.user.User
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by KYLE on 2019/5/8 - 15:01
 */
interface UserService {

    @FormUrlEncoded
    @POST("user/login")
    fun loginAsync(
        @Field("username") username: String,
        @Field("password") password: String
    ): Deferred<HttpReturnType<User>>

    @FormUrlEncoded
    @POST("user/register")
    fun signUpAsync(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): Deferred<HttpReturnType<User>>

    @GET("user/logout/json")
    fun logoutAsync(): Deferred<HttpReturnType<User>>
}
