package kylec.me.base.user

import com.vicpin.krealmextensions.delete
import com.vicpin.krealmextensions.query
import com.vicpin.krealmextensions.save

/**
 * Created by KYLE on 2019/5/9 - 10:55
 */
object UserDao {

    fun queryUser(username: String) = query<User> { equalTo("username", username) }.firstOrNull()

    fun saveUser(user: User) = user.save()

    fun deleteUser(username: String) = delete<User> { equalTo("username", username) }
}
