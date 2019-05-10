@file:Suppress("ConstantConditionIf", "unused")

package kylec.me.base.extend

import kylec.me.base.BuildConfig

import android.util.Log

/**
 * 日志打印KT函数
 *
 * Created by KYLE on 2019/3/13 - 13:47
 */

private const val TAG: String = "Test"

/**
 * 自定义TAG,打印重要数据,info级别
 */
fun i(tag: String = TAG, msg: String) {
    if (BuildConfig.DEBUG) Log.i(tag, msg)
}

/**
 * 自定义TAG,打印调试信息,debug级别
 */
fun d(tag: String = TAG, msg: String) {
    if (BuildConfig.DEBUG) Log.d(tag, msg)
}

/**
 * 自定义TAG,打印错误信息,error级别
 */
fun e(tag: String = TAG, msg: String) {
    if (BuildConfig.DEBUG) {
        Log.e(tag, msg)
    }
}

/**
 * 自定义TAG,打印意义最小的信息,verbose级别
 */
fun v(tag: String = TAG, msg: String) {
    if (BuildConfig.DEBUG) Log.v(tag, msg)
}

/**
 * 自定义TAG,打印警告信息,warn级别
 */
fun w(tag: String = TAG, msg: String) {
    if (BuildConfig.DEBUG) Log.w(tag, msg)
}
