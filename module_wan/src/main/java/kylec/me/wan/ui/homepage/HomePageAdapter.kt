package kylec.me.wan.ui.homepage

import kylec.me.base.adapter.BaseNormalAdapter
import kylec.me.base.adapter.BaseViewHolder
import kylec.me.base.common.Icon
import kylec.me.wan.R
import kylec.me.wan.model.Article
import kylec.me.wan.model.ProjectType
import kotlin.random.Random

/**
 * Created by KYLE on 2019/5/13 - 21:12
 */
class ProjectTypeAdapter(
    data: ArrayList<ProjectType>
) : BaseNormalAdapter<ProjectType>(R.layout.item_project_type, data) {

    override fun convert(holder: BaseViewHolder, item: ProjectType, position: Int) {
        holder.setText(R.id.projectItem, item.name)
            .setTextDrawableTop(R.id.projectItem, Icon.icons[Random.nextInt(Icon.icons.size)])
    }
}

/**
 * Created by KYLE on 2019/5/13 - 21:30
 */
class ArticleAdapter(
    data: ArrayList<Article>
) : BaseNormalAdapter<Article>(R.layout.item_home_page_article, data) {

    override fun convert(holder: BaseViewHolder, item: Article, position: Int) {
        with(item) {
            holder.setText(R.id.author, author)
                .setText(R.id.title, title)
                .setText(R.id.date, niceDate)
                .visibleGone(R.id.fresh, fresh)
        }
    }
}
