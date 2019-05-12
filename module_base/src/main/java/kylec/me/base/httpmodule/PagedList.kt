package kylec.me.base.httpmodule

/**
 * Created by KYLE on 2019/5/12 - 15:24
 */
data class PagedList<T>(
    var curPage: Int,
    var pageCount: Int,
    var size: Int,
    var total: Int,
    var datas: ArrayList<T>
)
