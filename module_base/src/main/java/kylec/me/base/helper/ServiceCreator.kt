package kylec.me.base.helper

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.google.gson.GsonBuilder
import kylec.me.base.WanAndroidApp
import kylec.me.base.extend.i
import okhttp3.Cookie
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.util.concurrent.TimeUnit

/**
 * Created by KYLE on 2019/5/8 - 13:05
 */
object ServiceCreator {

    private const val BASE_URL = "https://www.wanandroid.com/"

    private const val TAG = "LoggingInterceptor"

    private val loggingInterceptor =
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            if (it.isBlank()) return@Logger

            // Print server returns a json.
            try {
                i(tag = TAG, msg = URLDecoder.decode(it, "utf-8"))
            } catch (e: UnsupportedEncodingException) {
                i(tag = TAG, msg = it)
            }
            // need set the print level to ` BODY ` because not print by default.
        }).apply { level = HttpLoggingInterceptor.Level.BODY }

    private val sharedPrefsCookiePersistor = SharedPrefsCookiePersistor(WanAndroidApp.INSTANCE)

    val persistentCookieJar =
        PersistentCookieJar(
            SetCookieCache(),
            sharedPrefsCookiePersistor
        )

    val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .readTimeout(6, TimeUnit.SECONDS)
        .writeTimeout(6, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        // .persistentCookieJar(KJWanAndroidCookieJar.create())
        .cookieJar(persistentCookieJar)
        .retryOnConnectionFailure(true)
        .build()

    private val builder = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()))

    private val retrofit = builder.build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    fun provideCookies(): List<Cookie> = sharedPrefsCookiePersistor.loadAll()
}

/*
    Use `CoroutineCallAdapterFactory` as a Call adapter when building `Retrofit instance`
    service methods can now use `Deferred` as their return type.

    NOTE:
    if not use `CoroutineCallAdapterFactory`


    do it like this:

    `create a extend function`
    ```
    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()

                    if ((null != body) && response.isSuccessful) {
                        continuation.resume(body)
                    } else {
                        continuation.resumeWithException(RuntimeException("Response failed or response body is null."))
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
    ```

 */
