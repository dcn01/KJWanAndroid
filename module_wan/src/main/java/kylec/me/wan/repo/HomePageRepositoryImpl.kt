package kylec.me.wan.repo

import kylec.me.base.httpmodule.HttpReturnType
import kylec.me.base.repo.ILocalDataSource
import kylec.me.base.repo.IRemoteDataSource
import kylec.me.wan.model.Banner
import kylec.me.wan.model.HomePageArticle

/**
 * Created by KYLE on 2019/5/12 - 17:37
 */
class HomePageRepositoryImpl(
    remoteDataSource: HomeRemoteDataSource,
    localDataSource: HomeLocalDataSource
) : HomePageRepository {

    override suspend fun obtainBannerData(): HttpReturnType<Banner> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun obtainArticleData(): HttpReturnType<HomePageArticle> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class HomeRemoteDataSource() : IRemoteDataSource {}

class HomeLocalDataSource() : ILocalDataSource {}
