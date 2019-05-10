package kylec.me.user.ui.module

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kylec.me.user.repo.UserRepository
import kotlinx.coroutines.launch
import kylec.me.base.extend.STRING_BLANK
import kylec.me.base.extend.process
import kylec.me.base.helper.SingletonHolderSingleArg
import kylec.me.base.user.UserConfig
import kylec.me.base.viewmodule.BaseViewModule

/**
 * Created by KYLE on 2019/5/8 - 14:47
 */
class UserViewModel(private val repo: UserRepository) : BaseViewModule() {

    private var _loginErrMsg = MutableLiveData<String>()
    private var _signUpErrMsg = MutableLiveData<String>()

    var isLoading = MutableLiveData<Boolean>()

    val loginErrMsg: LiveData<String>
        get() = _loginErrMsg

    val signUpErrMsg: LiveData<String>
        get() = _signUpErrMsg

    fun login(username: String, password: String) {
        launch({
            repo.login(username, password).process(
                // the login request is successful
                { user ->
                    _loginErrMsg.value = STRING_BLANK
                    UserConfig.logged(user)
                },
                // this error is http connect success, but obtain failed,
                // such as `the user name has been registered`
                { errMsg -> _loginErrMsg.value = errMsg }
            )

            // this error is http connect failed, such as `no network`.
        }, { _loginErrMsg.value = it.message })
    }

    fun signUp(username: String, password: String, repassword: String) {
        launch({
            repo.signUp(username, password, repassword).process(
                { _signUpErrMsg.value = STRING_BLANK },
                { errMsg -> _signUpErrMsg.value = errMsg }
            )
        }, { _signUpErrMsg.value = it.message })
    }

    fun logout() {
        launch({
            repo.logout()
        }, {})
    }

    private fun launch(block: suspend () -> Unit, error: suspend (Throwable) -> Unit) =
        viewModelScope.launch {
            try {
                isLoading.value = true
                block()
                isLoading.value = false
            } catch (e: Throwable) {
                error(e)
                isLoading.value = false
            }
        }
}

class UserViewModelFactory(
    private val repo: UserRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = UserViewModel(repo) as T

    companion object :
        SingletonHolderSingleArg<UserViewModelFactory, UserRepository>(::UserViewModelFactory)
}
