@file:Suppress("ConstantConditionIf", "unused")

package kylec.me.base.extend

import kylec.me.base.BuildConfig

import android.util.Log

/**
 * Log Ex Func
 *
 * Created by KYLE on 2019/3/13 - 13:47
 */

private const val TAG: String = "Test"

fun i(tag: String = TAG, msg: String) {
    if (BuildConfig.DEBUG) Log.i(tag, msg)
}

fun d(tag: String = TAG, msg: String) {
    if (BuildConfig.DEBUG) Log.d(tag, msg)
}

fun e(tag: String = TAG, msg: String) {
    if (BuildConfig.DEBUG) Log.e(tag, msg)
}

fun v(tag: String = TAG, msg: String) {
    if (BuildConfig.DEBUG) Log.v(tag, msg)
}

fun w(tag: String = TAG, msg: String) {
    if (BuildConfig.DEBUG) Log.w(tag, msg)
}
