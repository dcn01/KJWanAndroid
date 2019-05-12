package kylec.me.wan.repo

import kylec.me.base.httpmodule.HttpReturnType
import kylec.me.wan.model.Banner
import kylec.me.wan.model.HomePageArticle

/**
 * Created by KYLE on 2019/5/12 - 17:35
 */
interface HomePageRepository {

    suspend fun obtainBannerData(): HttpReturnType<Banner>

    suspend fun obtainArticleData(): HttpReturnType<HomePageArticle>
}
