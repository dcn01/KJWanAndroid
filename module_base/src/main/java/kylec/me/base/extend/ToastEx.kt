@file:Suppress("unused")

package kylec.me.base.extend

import kylec.me.base.R

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast

/**
 * Toast Ex Func
 *
 * Created by KYLE on 2019/5/8 - 9:32
 */

private var toast: Toast? = null

@SuppressLint("ShowToast")
fun Context.toastShort(message: CharSequence?, gravity: Int = Gravity.BOTTOM) {
    message?.let {
        if (null == toast) {
            toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
            setToastStyle()
        } else {
            toast!!.setText(message)
        }
        toast!!.setGravity(gravity, 0, if (gravity == Gravity.BOTTOM) 36 else 0)
        toast!!.show()
    }
}

@SuppressLint("ShowToast")
fun Context.toastLong(message: CharSequence?, gravity: Int = Gravity.BOTTOM) {
    message?.let {
        if (null == toast) {
            toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
            setToastStyle()
        } else {
            toast!!.setText(message)
        }
        toast!!.setGravity(gravity, 0, if (gravity == Gravity.BOTTOM) 36 else 0)
        toast!!.show()
    }
}

@SuppressLint("ShowToast")
fun Context.toastShowDiy(message: CharSequence?, duration: Int, gravity: Int = Gravity.BOTTOM) {
    message?.let {
        if (null == toast) {
            toast = Toast.makeText(this, message, duration)
            setToastStyle()
        } else {
            toast!!.setText(message)
        }
        toast!!.setGravity(gravity, 0, if (gravity == Gravity.BOTTOM) 36 else 0)
        toast!!.show()
    }
}

private fun setToastStyle() {
    val view = toast!!.view
    if (null != view) {
        view.setBackgroundResource(R.drawable.bg_toast)
        val msgTextView = view.findViewById<TextView>(android.R.id.message)
        msgTextView.setTextColor(Color.WHITE)
        msgTextView.paint.isFakeBoldText = true
        msgTextView.gravity = Gravity.CENTER
        msgTextView.setPadding(36, 12, 36, 12)
    }
}

/* Toast Layout Style:
*
* <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
*   android:layout_width="match_parent"
*   android:layout_height="match_parent"
*   android:orientation="vertical"
*   android:background="?android:attr/toastFrameBackground">
*
*       <TextView
*           android:id="@android:id/message"
*           android:layout_width="wrap_content"
*           android:layout_height="wrap_content"
*           android:layout_weight="1"
*           android:layout_gravity="center_horizontal"
*           android:textAppearance="@style/TextAppearance.Toast"
*           android:textColor="@color/bright_foreground_dark"
*           android:shadowColor="#BB000000"
*           android:shadowRadius="2.75"/>
*
*   </LinearLayout>
*/
