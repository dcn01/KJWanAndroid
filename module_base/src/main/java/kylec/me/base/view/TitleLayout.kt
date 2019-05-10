package kylec.me.base.view

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import kotlinx.android.synthetic.main.layout_title.view.*
import kylec.me.base.R
import kylec.me.base.extend.visible

/**
 * Created by KYLE on 2019/5/8 - 14:15
 */
class TitleLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(
    context,
    attrs, defStyleAttr
) {

    private var title: CharSequence? = ""
        set(value) {
            field = value
            toolbarTitle.text = title
        }

    private var textColor = 0x333333
        set(value) {
            field = value
            toolbarTitle.setTextColor(value)
        }

    private var iconTintColor: Int = 0x333333
        set(value) {
            field = value
            toolbarBack.apply { tintIcon(this, value) }
        }

    private var showBack: Boolean = true
        set(value) {
            field = value
            toolbarBack.visible(value)
        }

    private var showLine: Boolean = true
        set(value) {
            field = value
            toolbarLine?.visible(value)
        }

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        View.inflate(context, R.layout.layout_title, this)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.TitleLayout)
            typedArray.apply {
                title = getString(R.styleable.TitleLayout_title)
                showBack = getBoolean(R.styleable.TitleLayout_showBack, true)
                showLine = getBoolean(R.styleable.TitleLayout_showLine, true)
                textColor =
                    getColor(
                        R.styleable.TitleLayout_textColor,
                        ContextCompat.getColor(context, android.R.color.black)
                    )
                iconTintColor =
                    getColor(
                        R.styleable.TitleLayout_iconTint,
                        ContextCompat.getColor(context, android.R.color.black)
                    )
                recycle()
            }
        }

        toolbarBack.setOnClickListener { (context as Activity).onBackPressed() }
    }

    private fun tintIcon(imageView: ImageView, colors: Int) {
        imageView.apply {
            val wrappedDrawable = DrawableCompat.wrap(drawable)
            DrawableCompat.setTintList(wrappedDrawable, ColorStateList.valueOf(colors))
            setImageDrawable(wrappedDrawable)
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        toolbarBack.apply {
            visible(showBack)
            if (showBack) {
                tintIcon(this, iconTintColor)
            }
        }
        toolbarTitle.apply {
            text = title
            textColor = textColor
        }

        toolbarLine.visible(showLine)
    }
}
