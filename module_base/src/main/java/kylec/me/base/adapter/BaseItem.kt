package kylec.me.base.adapter

/**
 * Created by KYLE on 2019/5/11 - 23:06
 */
open class BaseItem {

    var id: Int = 0

    override fun equals(other: Any?): Boolean {
        if (other !is BaseItem) {
            return false
        }
        return other.id == this.id
    }

    override fun hashCode() = id * 3008
}
