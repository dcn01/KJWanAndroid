package kylec.me.user.ui.interfaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_user_info.*
import kylec.me.base.common.createAlertDialog
import kylec.me.base.extend.find
import kylec.me.base.extend.snack
import kylec.me.base.user.UserConfig
import kylec.me.user.R
import kylec.me.user.databinding.FragmentUserInfoBinding
import kylec.me.user.ui.interfaces.about.AboutActivity
import kylec.me.user.ui.module.UserViewModel

/**
 * Created by KYLE on 2019/5/10 - 20:18
 */
class UserInfoFragment : BaseUserFragment<FragmentUserInfoBinding, UserViewModel>() {

    companion object {
        fun newInstance() = UserInfoFragment()
    }

    override fun initViewModel() = userViewModel

    override fun getViewId(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = R.layout.fragment_user_info

    private val tvUsername by lazy {
        userLeftNav.getHeaderView(0).find<TextView>(R.id.tvUsername)
    }

    override fun initDataAfterViewCreated(view: View) {
        binding.viewModel = userViewModel

        userViewModel.username.observe(this, Observer {
            tvUsername.text = it

            with(userLeftNav.menu.findItem(R.id.menu_logout)) {
                isVisible = if (null == UserConfig.currentUser) {
                    tvUsername.setOnClickListener { startActivity<UserActivity>() }
                    false
                } else {
                    tvUsername.setOnClickListener(null)
                    true
                }
            }
        })

        // logout successful
        userViewModel.logout.observe(this, Observer {
            if (it.isBlank()) {
                // clear local info
                UserConfig.logout()

                startActivity<UserActivity>()
                finish()
            } else {
                tvUsername.snack(it)
            }
        })

        // logout menu clicked
        userLeftNav.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_about -> startActivity<AboutActivity>()
                R.id.menu_logout -> {
                    mContext.createAlertDialog("确认退出？", { userViewModel.logout() })
                }
            }
            true
        }
    }
}
