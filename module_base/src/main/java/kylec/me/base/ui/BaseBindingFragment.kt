package kylec.me.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kylec.me.base.viewmodule.BaseViewModule

/**
 * Created by KYLE on 2019/5/10 - 15:23
 */
abstract class BaseBindingFragment<DB : ViewDataBinding, VM : BaseViewModule> : BaseFragment() {

    protected lateinit var binding: DB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            getViewId(inflater, container, savedInstanceState),
            container,
            false
        )

        binding.lifecycleOwner = this

        initViewModel().apply {
            lifecycle.addObserver(this)
        }

        return binding.root
    }

    protected abstract fun initViewModel(): VM
}
