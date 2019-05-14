package kylec.me.user.ui.module

import kylec.me.user.repo.UserRepository
import kylec.me.user.repo.UserRepositoryImpl
import kylec.me.user.service.UserService
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

/**
 * user module for kdi.
 *
 * Created by KYLE on 2019/5/8 - 16:02
 */

private const val NAME_USER_MODULE = "USER_MODULE"

val userKodeinModule = Kodein.Module(NAME_USER_MODULE) {

    bind<UserService>() with singleton {
        instance<Retrofit>().create(UserService::class.java)
    }

    bind<UserRepository>() with singleton { UserRepositoryImpl(instance()) }

    bind() from singleton { UserViewModelFactory(instance()) }

    // bind<UserViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
    //     ViewModelProviders.of(context.activity!!, UserViewModelFactory.getInstance(instance()))
    //         .get(UserViewModel::class.java)
    // }

}
