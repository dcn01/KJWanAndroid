package kylec.me.wan.repo

import kylec.me.base.extend.i
import kylec.me.base.extend.process
import kylec.me.base.httpmodule.HttpReturnType
import kylec.me.base.repo.ILocalDataSource
import kylec.me.base.repo.IRemoteDataSource
import kylec.me.wan.db.HomePageDao
import kylec.me.realm.Banner
import kylec.me.wan.model.Article
import kylec.me.wan.model.ProjectType
import kylec.me.wan.service.HomePageService

/**
 * Created by KYLE on 2019/5/12 - 17:37
 */
class HomePageRepositoryImpl(
    private val remoteDataSource: HomeRemoteDataSource,
    private val localDataSource: HomeLocalDataSource
) : HomePageRepository {

    override suspend fun obtainBannerData(): List<Banner> {
        var banners = localDataSource.fetchBannersFromDb()
        i(msg = "banner data from db: $banners")

        if (banners.isNullOrEmpty()) {
            remoteDataSource.fetchBannerData().process({ bannerFetched ->
                i(msg = "banner data fetched: $bannerFetched")
                // banner data fetched: [kylec.me.realm.Banner@468cfca, kylec.me.realm.Banner@2c353b, kylec.me.realm.Banner@1acd458, kylec.me.realm.Banner@35969b1, kylec.me.realm.Banner@b2c1e96, kylec.me.realm.Banner@11eea17, kylec.me.realm.Banner@a46de04, kylec.me.realm.Banner@2e9efed]
                bannerFetched?.apply { banners = this }
            })
            localDataSource.insertNewBanners(banners)
        }

        return banners
    }

    override suspend fun obtainProjectTypeData(): HttpReturnType<ProjectType> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun obtainArticleData(): HttpReturnType<Article> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class HomeRemoteDataSource(private val homepageService: HomePageService) : IRemoteDataSource {

    suspend fun fetchBannerData() = homepageService.obtainBannerDataAsync().await()

    fun fetchProjectTypeData() {
        val result = homepageService.obtainProjectTypeAsync()
    }

    fun fetchArticleDataByPage(page: Int) {
        val result = homepageService.obtainArticleDataAsync(page)
    }
}

class HomeLocalDataSource(private val dao: HomePageDao) : ILocalDataSource {

    fun fetchBannersFromDb() = dao.fetchBannerData()

    fun insertNewBanners(newData: List<Banner>) {
        dao.saveNewBanners(newData)
    }
}
