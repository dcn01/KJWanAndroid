package kylec.me.base

import android.app.Activity

/**
 * 管理APP栈
 *
 * Created by KYLE on 2019/5/8 - 9:36
 */
class AppManager private constructor() {

    private val activityList = mutableListOf<Activity>()

    companion object {
        val instance: AppManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { AppManager() }
    }

    fun pushActivity(activity: Activity) = activityList.add(activity)

    fun popActivity(activity: Activity) =
        with(activityList) { if (contains(activity)) remove(activity) }

    fun exit() {
        process()
        // android.os.Process.killProcess(android.os.Process.myPid())
        // System.exit(0)
    }

    private fun process() {
        if (activityList.isEmpty()) return

        for (activity in activityList) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
        activityList.clear()
    }
}
