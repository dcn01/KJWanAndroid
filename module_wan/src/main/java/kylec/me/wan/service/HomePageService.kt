package kylec.me.wan.service

import kotlinx.coroutines.Deferred
import kylec.me.base.httpmodule.HttpReturnType
import kylec.me.base.httpmodule.PagedList
import kylec.me.realm.Banner
import kylec.me.wan.model.Article
import kylec.me.wan.model.ProjectType
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by KYLE on 2019/5/12 - 14:39
 */
interface HomePageService {

    @GET("banner/json")
    fun obtainBannerDataAsync(): Deferred<HttpReturnType<List<Banner>>>

    @GET("project/tree/json")
    fun obtainProjectTypeAsync(): Deferred<HttpReturnType<ArrayList<ProjectType>>>

    @GET("article/list/{page}/json")
    fun obtainArticleDataAsync(@Path("page") page: Int): Deferred<HttpReturnType<PagedList<Article>>>
}
