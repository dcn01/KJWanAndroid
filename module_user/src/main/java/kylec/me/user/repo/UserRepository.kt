package kylec.me.user.repo

import kylec.me.base.httpmodule.HttpReturnType
import kylec.me.base.user.User

/**
 * Created by KYLE on 2019/5/8 - 15:34
 */
interface UserRepository {

    suspend fun login(username: String, password: String): HttpReturnType<User>

    suspend fun signUp(username: String, password: String, rePassword: String): HttpReturnType<User>

    suspend fun logout(): HttpReturnType<User>
}
