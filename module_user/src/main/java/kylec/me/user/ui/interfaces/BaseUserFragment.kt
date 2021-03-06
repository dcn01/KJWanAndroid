package kylec.me.user.ui.interfaces

import androidx.databinding.ViewDataBinding
import kylec.me.base.ui.BaseBindingFragment
import kylec.me.base.viewmodule.BaseViewModel
import kylec.me.user.ui.module.UserViewModel
import kylec.me.user.ui.module.UserViewModelFactory
import kylec.me.user.ui.module.userKodeinModule
import org.kodein.di.Copy
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

/**
 * Created by KYLE on 2019/5/9 - 11:54
 */
abstract class BaseUserFragment<DB : ViewDataBinding, VM : BaseViewModel> :
    BaseBindingFragment<DB, VM>() {

    override val kodein: Kodein = Kodein.lazy {
        extend(parentKodein, copy = Copy.All)
        import(userKodeinModule)
    }

    private val factory by instance<UserViewModelFactory>()

    protected val userViewModel by lazy { UserViewModel(this, factory) }
}
