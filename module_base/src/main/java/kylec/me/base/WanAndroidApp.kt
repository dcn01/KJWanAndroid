package kylec.me.base

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import io.realm.Realm
import io.realm.RealmConfiguration
import kylec.me.base.di.netKodeinModule
import kylec.me.base.user.UserModule
import kylec.me.realm.HomePageModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

/**
 * Created by KYLE on 2019/5/7 - 15:57
 */
class WanAndroidApp : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        bind<Context>() with singleton { this@WanAndroidApp }
        import(androidXModule(this@WanAndroidApp))

        import(netKodeinModule)
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        // init ARouter
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)

        // init Realm
        // See [HomePageModule] for details about the `modules` function
        Realm.init(this)
        Realm.getDefaultModule()?.apply {
            val wanAndroidConfiguration = RealmConfiguration.Builder()
                .modules(this, UserModule(), HomePageModule())
                .name("KJAndroid.realm")
                .schemaVersion(1)
                .build()
            Realm.setDefaultConfiguration(wanAndroidConfiguration)
        }
    }

    companion object {
        lateinit var INSTANCE: WanAndroidApp
    }
}
