package kylec.me.base.user

import io.realm.RealmList
import io.realm.RealmObject
import kylec.me.base.extend.STRING_BLANK

/**
 * Created by KYLE on 2019/5/8 - 15:28
 */
open class User(
    var chapterTops: RealmList<Int> = RealmList(),
    var collectIds: RealmList<Int> = RealmList(),
    var email: String = STRING_BLANK,
    var icon: String = STRING_BLANK,
    var id: Int = 0,
    var password: String = STRING_BLANK,
    var token: String = STRING_BLANK,
    var type: Int = 0,
    var username: String = STRING_BLANK
) : RealmObject()
