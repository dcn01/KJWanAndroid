package kylec.me.user.ui.module

import kylec.me.user.repo.UserRepository
import kylec.me.user.repo.UserRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

/**
 * user module for kodein.
 *
 * Created by KYLE on 2019/5/8 - 16:02
 */

private const val NAME_USER_MODULE = "USER_MODULE"

val userKodeinModule = Kodein.Module(NAME_USER_MODULE) {

    bind<UserRepository>() with singleton { UserRepositoryImpl() }

    bind() from singleton { UserViewModelFactory.getInstance(instance()) }

    // bind<UserViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
    //     ViewModelProviders.of(context.activity!!, UserViewModelFactory.getInstance(instance()))
    //         .get(UserViewModel::class.java)
    // }

}
