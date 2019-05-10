package kylec.me.base.common

import android.content.Context
import androidx.appcompat.app.AlertDialog

/**
 * Created by KYLE on 2019/5/10 - 21:26
 */

val currentTimeMillis = System.currentTimeMillis()

fun Context.createAlertDialog(
    title: String,
    positiveBlock: () -> Unit = {},
    negativeBlock: () -> Unit = {},
    positiveContent: String = "确认",
    negativeContent: String = "取消"
) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setNegativeButton(negativeContent) { dialog, _ ->
            negativeBlock()
            dialog.cancel()
        }
        .setPositiveButton(positiveContent) { dialog, _ ->
            positiveBlock()
            dialog.cancel()
        }
        .create().show()
}
