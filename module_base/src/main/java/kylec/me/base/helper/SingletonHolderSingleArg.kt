package kylec.me.base.helper

/**
 * Used to allow Singleton with arguments in Kotlin while keeping the code efficient and safe.
 *
 * See https://medium.com/@BladeCoder/kotlin-singletons-with-argument-194ef06edd9e
 *
 * out T  target need create singleton
 * int A  argument in constructor
 *
 * Created by KYLE on 2019/5/8 - 16:07
 */
open class SingletonHolderSingleArg<out T, in A>(private val creator: (A) -> T) {

    @Volatile
    private var instance: T? = null

    fun getInstance(arg: A): T =
        instance ?: synchronized(this) {
            instance ?: creator(arg).also {
                instance = it
            }
        }
}
