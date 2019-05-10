package kylec.me.base.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * Created by KYLE on 2019/5/10 - 20:26
 */
class ControlScrollViewPager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ViewPager(context, attrs) {

    private var isCanScrolling = false

    @Suppress("RedundantOverride")
    override fun performClick() = super.performClick()

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        performClick()
        return isCanScrolling and super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?) =
        isCanScrolling and super.onInterceptTouchEvent(ev)

    override fun setCurrentItem(item: Int, smoothScroll: Boolean) {
        super.setCurrentItem(item, false)
    }
}
