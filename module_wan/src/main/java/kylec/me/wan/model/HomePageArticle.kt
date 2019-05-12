package kylec.me.wan.model

import kylec.me.base.adapter.BaseItem
import kylec.me.base.extend.STRING_BLANK

/**
 * Created by KYLE on 2019/5/12 - 15:12
 */
data class HomePageArticle(
    // article author
    var author: String,
    // user has been collection?
    var collect: Boolean,
    // is new coming?
    var fresh: Boolean,
    // project address
    var projectLink: String,
    // pic show on the web
    var envelopePic: String,
    // article address
    var link: String,
    // article publication date
    var niceDate: String
) : BaseItem() {
    // article des
    var title: String = ""
        get() {
            return if (field.isBlank()) {
                STRING_BLANK
            } else {
                field.replace("amp;", STRING_BLANK)
            }
        }
}
