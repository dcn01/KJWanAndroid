package kylec.me.base

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import io.realm.Realm
import io.realm.RealmConfiguration
import kylec.me.base.di.netKodeinModule
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
            // 这两行必须写在init之前，否则这些配置在init过程中将无效
            // 打印日志
            ARouter.openLog()
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug()
        }
        ARouter.init(this)

        // init Realm
        Realm.init(this)
        val defaultConfiguration = RealmConfiguration.Builder()
            .name("KJAndroid.realm")
            .schemaVersion(1)
            .build()
        Realm.setDefaultConfiguration(defaultConfiguration)
    }

    companion object {
        lateinit var INSTANCE: WanAndroidApp
    }
}
