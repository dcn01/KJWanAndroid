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
import kotlinx.android.synthetic.main.layout_title_view.view.*
import kylec.me.base.R
import kylec.me.base.extend.visible

/**
 * Created by KYLE on 2019/5/8 - 14:15
 */
class TitleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(
    context,
    attrs, defStyleAttr
) {

    var title: CharSequence? = ""
        set(value) {
            field = value
            titleContent.text = title
        }

    private var textColor = 0x333333
        set(value) {
            field = value
            titleContent.setTextColor(value)
        }

    private var iconTintColor: Int = 0x333333
        set(value) {
            field = value
            titleBack.apply { tintIcon(this, value) }
        }

    private var showBack: Boolean = true
        set(value) {
            field = value
            titleBack.visible(value)
        }

    private var showLine: Boolean = true
        set(value) {
            field = value
            titleDividerLine?.visible(value)
        }

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        View.inflate(context, R.layout.layout_title_view, this)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.TitleView)
            typedArray.apply {
                title = getString(R.styleable.TitleView_title)
                showBack = getBoolean(R.styleable.TitleView_showBack, true)
                showLine = getBoolean(R.styleable.TitleView_showLine, true)
                textColor =
                    getColor(
                        R.styleable.TitleView_textColor,
                        ContextCompat.getColor(context, android.R.color.black)
                    )
                iconTintColor =
                    getColor(
                        R.styleable.TitleView_iconTint,
                        ContextCompat.getColor(context, android.R.color.black)
                    )
                recycle()
            }
        }

        titleBack.setOnClickListener { (context as Activity).onBackPressed() }
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
        titleBack.apply {
            visible(showBack)
            if (showBack) {
                tintIcon(this, iconTintColor)
            }
        }
        titleContent.apply {
            text = title
            textColor = textColor
        }

        titleDividerLine.visible(showLine)
    }
}
