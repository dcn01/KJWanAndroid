package kylec.me.user.ui.interfaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import kotlinx.android.synthetic.main.fragment_login.*
import kylec.me.user.Router
import kylec.me.user.ui.module.UserViewModel
import kylec.me.base.extend.content
import kylec.me.user.R
import kylec.me.user.databinding.FragmentLoginBinding

/**
 * Created by KYLE on 2019/5/8 - 14:47
 */
class LoginFragment : BaseUserFragment<FragmentLoginBinding, UserViewModel>() {

    override fun getViewId(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = R.layout.fragment_login

    override fun initViewModel() = userViewModel

    override fun initDataAfterViewCreated(view: View) {
        binding.viewModel = userViewModel

        val o1 = etUserName.textChanges()
        val o2 = etPassword.textChanges()
        Observable.combineLatest(o1, o2, BiFunction<CharSequence, CharSequence, Boolean> { s1, s2 ->
            s1.isNotBlank() and s2.isNotBlank()
        }).subscribe { loginButton.isEnabled = it }.add()

        signUp.setOnClickListener { findNavController().navigate(R.id.action_login_to_signUp) }
        loginButton.setOnClickListener {
            userViewModel.login(etUserName.content, etPassword.content)
        }

        userViewModel.loginErrMsg.observe(this, Observer {
            if (it.isBlank()) {
                toastShort("登录成功")
                Router.startMainActivity()
            } else {
                toastShort("登录失败：$it")
            }
        })
    }
}
