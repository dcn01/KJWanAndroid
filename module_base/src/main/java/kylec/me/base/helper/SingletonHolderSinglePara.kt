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
open class SingletonHolderSinglePara<out TARGET, in PARA>(private val creator: (PARA) -> TARGET) {

    @Volatile
    private var instance: TARGET? = null

    fun getInstance(arg: PARA): TARGET =
        instance ?: synchronized(this) {
            instance ?: creator(arg).also {
                instance = it
            }
        }
}
