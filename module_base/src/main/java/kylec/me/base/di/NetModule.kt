package kylec.me.base.di

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.google.gson.GsonBuilder
import kylec.me.base.WanAndroidApp
import kylec.me.base.extend.i
import kylec.me.base.helper.CoroutineCallAdapterFactory
import okhttp3.Cookie
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.util.concurrent.TimeUnit

/**
 * Network related. instance by kdi
 *
 * Used in place of `ServiceCreator`
 *
 * Created by KYLE on 2019/5/12 - 21:00
 */

private const val BASE_URL = "https://www.wanandroid.com/"

private const val TAG = "LoggingInterceptor"

private const val NET_MODULE = "NET_MODULE"

val netKodeinModule = Kodein.Module(NET_MODULE) {

    bind<HttpLoggingInterceptor>() with singleton {
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
    }

    bind<SharedPrefsCookiePersistor>() with singleton { SharedPrefsCookiePersistor(WanAndroidApp.INSTANCE) }

    bind<PersistentCookieJar>() with singleton {
        PersistentCookieJar(
            SetCookieCache(),
            instance()
        )
    }

    bind<OkHttpClient.Builder>() with provider { OkHttpClient.Builder() }

    bind<OkHttpClient>() with singleton {
        instance<OkHttpClient.Builder>()
            .readTimeout(6, TimeUnit.SECONDS)
            .writeTimeout(6, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(instance())
            // .persistentCookieJar(KJWanAndroidCookieJar.create())
            .cookieJar(instance())
            .retryOnConnectionFailure(true)
            .build()
    }

    bind<Retrofit.Builder>() with provider { Retrofit.Builder() }

    bind<Retrofit>() with singleton {
        instance<Retrofit.Builder>()
            .client(instance())
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()))
            .build()
    }

    bind<List<Cookie>>() with provider { instance<SharedPrefsCookiePersistor>().loadAll() }

}
