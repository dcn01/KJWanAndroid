package kylec.me.wan.ui.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import kylec.me.base.ui.BaseBindingFragment
import kylec.me.wan.R
import kylec.me.wan.databinding.FragmentHomePageBinding
import org.kodein.di.Copy
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

/**
 * APP HOME PAGE, SHOW ARTICLES FROM HOME PAGE ARTICLE LIST INTERFACE
 *
 * Created by KYLE on 2019/5/12 - 15:29
 */
class HomePageFragment : BaseBindingFragment<FragmentHomePageBinding, HomePageViewModel>() {

    override fun initViewModel() = homePageViewModel

    override fun getViewId(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = R.layout.fragment_home_page

    override val kodein: Kodein = Kodein.lazy {
        extend(parentKodein, copy = Copy.All)
        import(homePageKodeinModule)
    }

    private val homePageViewModel by instance<HomePageViewModel>()
}
