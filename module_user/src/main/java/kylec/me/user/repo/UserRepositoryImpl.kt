package kylec.me.user.repo

import kylec.me.user.service.UserService

/**
 * Created by KYLE on 2019/5/8 - 15:36
 */
class UserRepositoryImpl(
    private val userService: UserService
) : UserRepository {

    // private val userService = ServiceCreator.create(UserService::class.java)

    override suspend fun login(
        username: String, password: String
    ) = userService.loginAsync(username, password).await()

    override suspend fun signUp(
        username: String,
        password: String,
        rePassword: String
    ) = userService.signUpAsync(username, password, rePassword).await()

    override suspend fun logout() = userService.logoutAsync().await()
}
