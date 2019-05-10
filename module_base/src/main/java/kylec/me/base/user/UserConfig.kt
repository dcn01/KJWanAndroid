package kylec.me.base.user

import kylec.me.base.extend.d
import kylec.me.base.helper.ServiceCreator

/**
 * User Logic Config
 *
 * Created by KYLE on 2019/5/8 - 9:25
 */
object UserConfig {

    private const val KEY_USERNAME = "loginUserName"
    private const val KEY_TOKEN = "token_pass"

    private var currentUser: User? = null

    var isLogin: Boolean = false
        get() {
            return field and (null != currentUser)
        }

    /**
     * initialize user info every time when start app.
     */
    fun init() {
        var username: String? = null
        var token: String? = null
        val cookies = ServiceCreator.provideCookies()

        // cookie example:
        // [loginUserName_wanandroid_com=hahahaha; expires=Sat, 08 Jun 2019 04:00:56 GMT; domain=wanandroid.com; path=/, loginUserName=hahahaha; expires=Sat, 08 Jun 2019 04:00:56 GMT; path=/, token_pass=5d9b90bcb70640183e09d1e755ead823; expires=Sat, 08 Jun 2019 04:00:56 GMT; path=/, token_pass_wanandroid_com=5d9b90bcb70640183e09d1e755ead823; expires=Sat, 08 Jun 2019 04:00:56 GMT; domain=wanandroid.com; path=/]
        d(msg = "Cookie: $cookies")

        cookies.forEach {
            if (it.name() == KEY_USERNAME) {
                val value = it.value()
                if ("\"\"" != value) {
                    username = value
                }
            } else if (it.name() == KEY_TOKEN) {
                val value = it.value()
                if ("\"\"" != value) {
                    token = value
                }
            }
        }

        d(msg = "username: $username")
        d(msg = "token: $token")

        if ((null != username) and (null != token)) {
            val userFound = UserDao.queryUser(username!!)
            userFound?.let {
                currentUser = userFound
                isLogin = true
            }
        }
    }

    /**
     * user login completed.
     * save user info.
     */
    fun logged(_user: User?) {
        _user?.let {
            currentUser = _user
            isLogin = true

            UserDao.saveUser(it)
        }
    }

    /**
     * user logout.
     * clear user info.
     */
    fun logout() {
        currentUser?.let {
            UserDao.deleteUser(it.username)
        }

        currentUser = null
        isLogin = false

        // clear cookie
        ServiceCreator.apply {
            okHttpClient.dispatcher().cancelAll()
            persistentCookieJar.clear()
        }
    }
}
