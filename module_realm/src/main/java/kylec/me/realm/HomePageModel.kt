package kylec.me.realm

import io.realm.RealmObject
import io.realm.annotations.RealmModule

/**
 * 此库专门用来存放Realm的Model
 *
 * Realm在库中使用一定要加@RealmModule(library = true, allClasses = true)
 * 并在构建RealmConfiguration时添加modules:
 * ...
 * .modules(Realm.getDefaultModule(), UserModule(), HomePageModule())
 *
 * 否则会出现 ..model is not part of the schema for this Realm.错
 *
 * 注意：添加了@RealmModule(library = true, allClasses = true)注解的类即使没有添加到RealmConfiguration中也会有影响
 * 具体原因未知？
 * 对于RealmModule创建的`顺序`对结果也会有影响？原因未知
 *
 * 这次遇到问题及解决（此时只做了登录UserModule和HomePageModule的banner块 `2019/5/14 21:07` ）：
 * 先正常编译一次，UserModule无误可正常使用，登陆后提示banner is not part of the schema for this Realm.
 * 在RealmConfiguration中添加modules(...., HomePageModule())后再次编译，提示Banner是新添加进的，需要migration，
 * 这也就说明了Banner模块到此时才真正被添加作为RealmModel，卸载APK重新安装后即可正常使用UserModule和HomePageModule
 *
 * 在所有使用到Realm的模块库都需要在build.gradle下添加：apply plugin: 'realm-android'
 *
 * 这又是一个关于Realm使用的坑，而且是大坑，要记录！！！
 */

/**
 * Home page banner entity.
 *
 * Created by KYLE on 2019/5/12 - 14:44
 */
open class Banner(
    var title: String = "",
    var imagePath: String = "",
    var url: String = ""
) : RealmObject()

/**
 * library 将此设置为true会将此模块标记为库模块
 * allClasses 不是手动将所有Realm类添加到模块，而是将此boolean设置为true以自动包含此项目中的所有Realm类
 */
@RealmModule(library = true, allClasses = true)
class HomePageModule
