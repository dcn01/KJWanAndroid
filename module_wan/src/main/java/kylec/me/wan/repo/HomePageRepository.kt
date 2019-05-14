package kylec.me.wan.repo

import kylec.me.base.httpmodule.HttpReturnType
import kylec.me.realm.Banner
import kylec.me.wan.model.Article
import kylec.me.wan.model.ProjectType

/**
 * Created by KYLE on 2019/5/12 - 17:35
 */
interface HomePageRepository {

    suspend fun obtainBannerData(): List<Banner>

    suspend fun obtainProjectTypeData(): HttpReturnType<ProjectType>

    suspend fun obtainArticleData(): HttpReturnType<Article>
}
