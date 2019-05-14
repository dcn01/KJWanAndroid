package kylec.me.wan.model

import kylec.me.base.extend.STRING_BLANK

/**
 * Created by KYLE on 2019/5/13 - 15:42
 */
data class ProjectType(var id: Int) {

    var name: String = STRING_BLANK
        get() = if (field == STRING_BLANK) STRING_BLANK else field.replace("amp;", STRING_BLANK)
}
