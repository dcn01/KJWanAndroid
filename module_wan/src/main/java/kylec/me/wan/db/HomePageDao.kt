package kylec.me.wan.db

import com.vicpin.krealmextensions.deleteAll
import com.vicpin.krealmextensions.queryAll
import com.vicpin.krealmextensions.saveAll
import kylec.me.realm.Banner

/**
 * Created by KYLE on 2019/5/12 - 18:16
 */
class HomePageDao {

    // ----------------------- Banner ----------------------------

    fun fetchBannerData() = queryAll<kylec.me.realm.Banner>()

    fun saveNewBanners(banners: List<kylec.me.realm.Banner>) {
        val previousData = fetchBannerData()
        if (previousData.isNullOrEmpty()) deleteAll<kylec.me.realm.Banner>()

        banners.saveAll()
    }
}
