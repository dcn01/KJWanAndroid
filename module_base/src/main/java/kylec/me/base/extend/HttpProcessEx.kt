package kylec.me.base.extend

import kylec.me.base.httpmodule.HttpReturnType

/**
 * Detail about `return type` see [HttpReturnType]
 *
 * Created by KYLE on 2019/5/9 - 9:49
 */

fun <T> HttpReturnType<T>.process(success: (T?) -> Unit, failed: (String) -> Unit = {}) {
    // errorCode = 0  success  else failed
    if (errorCode == 0) {
        success(data)
    } else {
        failed(errorMsg)
    }
}
