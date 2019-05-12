package kylec.me.wan.ui.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kylec.me.base.helper.SingletonHolderSinglePara
import kylec.me.base.viewmodule.BaseViewModel
import kylec.me.wan.repo.HomePageRepository

/**
 * Created by KYLE on 2019/5/12 - 15:28
 */
class HomePageViewModel(private val repo: HomePageRepository) : BaseViewModel() {
    // TODO: Implement the ViewModel
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
