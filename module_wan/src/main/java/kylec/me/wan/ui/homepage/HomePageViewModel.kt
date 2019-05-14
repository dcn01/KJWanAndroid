package kylec.me.wan.ui.homepage

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kylec.me.base.helper.SingletonHolderSinglePara
import kylec.me.base.viewmodule.BaseViewModel
import kylec.me.realm.Banner
import kylec.me.wan.repo.HomePageRepository

/**
 * Created by KYLE on 2019/5/12 - 15:28
 */
class HomePageViewModel(private val repo: HomePageRepository) : BaseViewModel() {

    var isRefreshing = MutableLiveData<Boolean>()

    private val _bannerErrMsg = MutableLiveData<String>()
    val bannerErrMsg: LiveData<String>
        get() = _bannerErrMsg

    private var _bannerData = MutableLiveData<List<Banner>>()
    val bannerData: LiveData<List<Banner>>
        get() = _bannerData

    init {
        obtainBannerData()
    }

    private fun obtainBannerData() {
        launch(
            { _bannerData.value = repo.obtainBannerData() },
            { _bannerErrMsg.value = it.message })
    }

    fun obtainProjectTypeData() {
        launch({ repo.obtainProjectTypeData() })
    }

    fun refreshArticle() {
        applyState(true)
        launch({
            delay(2000)
            // repo.obtainArticleData().process({ applyState(false) }, {
            //     applyState(false)
            // })
            applyState(false)
        }, {
            applyState(false)
        })
    }

    private fun applyState(value: Boolean) {
        isRefreshing.value = value
    }

    private fun launch(block: suspend () -> Unit, error: suspend (Throwable) -> Unit = {}) =
        viewModelScope.launch {
            try {
                block()
            } catch (e: Throwable) {
                error(e)
            }
        }

    companion object {
        operator fun invoke(fragment: Fragment, repo: HomePageRepository) =
            ViewModelProviders.of(fragment, HomePageViewModelFactory(repo)).get(
                HomePageViewModel::class.java
            )
    }
}

class HomePageViewModelFactory(
    private val repo: HomePageRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        HomePageViewModel(repo) as T

    companion object :
        SingletonHolderSinglePara<HomePageViewModelFactory, HomePageRepository>(::HomePageViewModelFactory)
}
