package kylec.me.wan.service

import kotlinx.coroutines.Deferred
import kylec.me.base.httpmodule.HttpReturnType
import kylec.me.base.httpmodule.PagedList
import kylec.me.wan.model.Banner
import kylec.me.wan.model.HomePageArticle
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by KYLE on 2019/5/12 - 14:39
 */
interface HomePageService {

    @GET("banner/json")
    fun obtainBannerData(): Deferred<HttpReturnType<Banner>>

    @GET("article/list/{page}/json")
    fun obtainHomePageData(@Path("page") page: Int): Deferred<HttpReturnType<PagedList<HomePageArticle>>>
}
