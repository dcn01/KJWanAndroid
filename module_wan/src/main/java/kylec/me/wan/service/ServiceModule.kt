package kylec.me.wan.service

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

/**
 * Created by KYLE on 2019/5/13 - 23:16
 */

private const val NAME_SERVICE_MODULE = "SERVICE_MODULE"

val serviceKodeinModule = Kodein.Module(NAME_SERVICE_MODULE) {

    bind() from singleton {
        instance<Retrofit>().create(HomePageService::class.java)
    }

}
