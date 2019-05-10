package kylec.me.user.repo

import kylec.me.user.service.UserService
import kylec.me.base.helper.ServiceCreator

/**
 * Created by KYLE on 2019/5/8 - 15:36
 */
class UserRepositoryImpl : UserRepository {

    private val loginService = ServiceCreator.create(UserService::class.java)

    override suspend fun login(
        username: String, password: String
    ) = loginService.loginAsync(username, password).await()

    override suspend fun signUp(
        username: String,
        password: String,
        repassword: String
    ) = loginService.signUpAsync(username, password, repassword).await()

    override suspend fun logout() {
        loginService.logoutAsync().await()
    }
}
