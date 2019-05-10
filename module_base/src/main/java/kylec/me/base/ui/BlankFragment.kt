package kylec.me.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import kylec.me.base.R
import org.kodein.di.android.x.kodein

/**
 * TEST.
 *
 * Created by KYLE on 2019/5/10 - 21:37
 */
class BlankFragment : BaseFragment() {

    override fun getViewId(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = R.layout.fragment_blank

    override val kodein by kodein()
}
