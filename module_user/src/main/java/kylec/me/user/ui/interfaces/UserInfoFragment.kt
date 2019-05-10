package kylec.me.user.ui.interfaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import kylec.me.user.ui.module.UserViewModel
import kylec.me.user.ui.module.UserViewModelFactory
import kylec.me.user.ui.module.userKodeinModule
import kylec.me.base.ui.BaseFragment
import kylec.me.user.R
import org.kodein.di.Copy
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

/**
 * Created by KYLE on 2019/5/10 - 20:18
 */
class UserInfoFragment : BaseFragment() {

    override val kodein: Kodein = Kodein.lazy {
        extend(parentKodein, copy = Copy.All)
        import(userKodeinModule)
    }

    private val factory: UserViewModelFactory by instance()

    private val userViewModel
        by lazy { ViewModelProviders.of(this, factory).get(UserViewModel::class.java) }

    override fun getViewId(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = R.layout.fragment_user_info

    companion object {
        fun newInstance() = UserInfoFragment()
    }
}
