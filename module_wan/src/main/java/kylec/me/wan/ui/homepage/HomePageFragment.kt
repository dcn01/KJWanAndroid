package kylec.me.wan.ui.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.gcssloop.widget.PagerGridLayoutManager
import com.gcssloop.widget.PagerGridSnapHelper
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_home_page.*
import kylec.me.base.Router
import kylec.me.base.extend.d
import kylec.me.base.extend.init
import kylec.me.base.extend.loadComprehensive
import kylec.me.base.ui.BaseBindingFragment
import kylec.me.wan.R
import kylec.me.wan.databinding.FragmentHomePageBinding
import kylec.me.wan.model.Article
import kylec.me.wan.model.ProjectType
import kylec.me.wan.service.serviceKodeinModule
import org.kodein.di.Copy
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import kotlin.random.Random

/**
 * APP HOME PAGE, SHOW ARTICLES FROM HOME PAGE ARTICLE LIST INTERFACE
 *
 * Created by KYLE on 2019/5/12 - 15:29
 */
class HomePageFragment : BaseBindingFragment<FragmentHomePageBinding, HomePageViewModel>() {

    private var startX = .0F
    private var startY = .0F
    private var enableRefresh = true
    private var appBarOffset = 0

    companion object {
        fun newInstance(): Fragment = HomePageFragment()
    }

    override fun initViewModel() = homePageViewModel

    override fun getViewId(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = R.layout.fragment_home_page

    override val kodein: Kodein = Kodein.lazy {
        extend(parentKodein, copy = Copy.All)
        import(serviceKodeinModule)
        import(homePageKodeinModule)
    }

    private val homePageViewModel by instance<HomePageViewModel>()

    override fun initDataAfterViewCreated(view: View) {
        binding.viewModel = homePageViewModel

        // solve sliding conflict
        mAppBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            appBarOffset = verticalOffset
            mSwipeRefreshLayout.isEnabled = verticalOffset >= 0 && enableRefresh
        })

        mRecyclerViewProjectType.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    startX = event.x
                    startY = event.y
                }
                MotionEvent.ACTION_MOVE -> {
                    val endX = event.x
                    val endY = event.y
                    val distanceX = Math.abs(startX - endX)
                    val distanceY = Math.abs(startY - endY)
                    if (distanceX > distanceY) {
                        mSwipeRefreshLayout.isEnabled = false
                    }
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    enableRefresh = true
                    mSwipeRefreshLayout.isEnabled = appBarOffset == 0 && enableRefresh
                }
            }
            false
        }

        // handle data begin ------------------

        // simulated data 1

        // val item = arrayListOf<Banner>()
        // repeat(6) {
        //     item.add(
        //         Banner(
        //             "First",
        //             "https://eimg.smzdm.com/201806/08/5b1a71b82ce299430.png",
        //             "https://m.smzdm.com/list/2615?from=app"
        //         )
        //     )
        // }

        // val titles = arrayListOf<String>()
        // val picPaths = arrayListOf<String>()
        // item.forEach {
        //     titles.add(it.title)
        //     picPaths.add(it.imagePath)
        // }

        // mBanner.setData(picPaths, titles)

        // banner item click listener
        // mBanner.setDelegate { _, _, _, bannerPos ->
        //     toastShort("You clicked : ${item[bannerPos].url}")
        // }

        // actual banner data
        mBanner.setAdapter { _, itemView, model, _ ->
            model?.let { (itemView as ImageView).loadComprehensive(it) }
        }

        homePageViewModel.bannerData.observe(this, Observer { banners ->
            val titles = arrayListOf<String>()
            val picPaths = arrayListOf<String>()

            banners.forEach {
                titles.add(it.title)
                picPaths.add(it.imagePath)
            }

            mBanner.setData(picPaths, titles)
            mBanner.setDelegate { _, _, _, bannerPos ->
                with(banners[bannerPos]) { Router.startWebActivity(title, url) }
            }
        })

        homePageViewModel.bannerErrMsg.observe(this, Observer {
            d(msg = "load banner failed: $it")
            toastShort("load banner failed: $it")
        })

        // simulated data 2

        val data = arrayListOf<ProjectType>()
        repeat(30) {
            data.add(ProjectType(2).apply { name = "New IDEA!" })
        }

        // Horizontal Paging Layout Manager
        val pagerGridLayoutManager = PagerGridLayoutManager(2, 4, PagerGridLayoutManager.HORIZONTAL)
        val projectTypeAdapter = ProjectTypeAdapter(data)

        with(mRecyclerViewProjectType) {
            init(projectTypeAdapter, pagerGridLayoutManager)
            // Set up scrolling accessibility tools
            PagerGridSnapHelper().attachToRecyclerView(this)
        }

        projectTypeAdapter.onItemClickListener = { _, item, _ ->
            toastShort("You clicked: ${item.name}")
        }

        // simulated data 3

        val article = arrayListOf<Article>()
        repeat(36) {
            article.add(
                Article(
                    author = "KYLE", collect = true, fresh = Random.nextBoolean(), projectLink = ""
                    , envelopePic = "", link = "", niceDate = "moment ago"
                ).apply { title = "WA FD RE AD FA ER A DFD E FAD AD AER" }
            )
        }

        val articleAdapter = ArticleAdapter(article)
        mRecyclerViewArticle.init(articleAdapter)
    }
}
