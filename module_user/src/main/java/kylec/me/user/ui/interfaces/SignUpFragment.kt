package kylec.me.user.ui.interfaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.jakewharton.rxbinding3.widget.textChanges
import kylec.me.user.ui.module.UserViewModel
import io.reactivex.Observable
import io.reactivex.functions.Function3
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kylec.me.base.extend.content
import kylec.me.user.R
import kylec.me.user.databinding.FragmentSignUpBinding

/**
 * Created by KYLE on 2019/5/8 - 14:47
 */
class SignUpFragment : BaseUserFragment<FragmentSignUpBinding, UserViewModel>() {

    override fun getViewId(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = R.layout.fragment_sign_up

    override fun initViewModel() = userViewModel

    override fun initDataAfterViewCreated(view: View) {
        binding.viewModel = userViewModel

        val o1 = etUserName.textChanges()
        val o2 = etPassword.textChanges()
        val o3 = confirmPassword.textChanges()
        Observable.combineLatest(
            o1,
            o2,
            o3,
            Function3<CharSequence, CharSequence, CharSequence, Boolean> { s1, s2, s3 ->
                s1.isNotBlank() and s2.isNotBlank() and s3.isNotBlank()
            })
            .subscribe { signUpButton.isEnabled = it }.add()

        signUpButton.setOnClickListener {
            userViewModel.signUp(etUserName.content, etPassword.content, confirmPassword.content)
        }

        userViewModel.signUpErrMsg.observe(this, Observer {
            if (it.isBlank()) {
                toastShort("注册成功，开始登录吧！")
                toLogin()
            } else {
                toastShort("注册失败：$it")
            }
        })
    }

    private fun toLogin() {
        fragmentManager?.popBackStack()
    }
}
