package cn.refactor.wancompose.model

import cn.refactor.wancompose.arch.bean.base.BaseBean
import cn.refactor.wancompose.arch.ext.optGet
import kotlinx.serialization.Serializable


/**
 * Created on 2022/11/1.
 *
 * @author andy
 */
// 首页文章列表
@Serializable
data class HomeArticleListResponse(
    var curPage: Int = 0,
    var datas: List<Article> = listOf(),
    var offset: Int = 0,
    var over: Boolean = false,
    var pageCount: Int = 0,
    var size: Int = 0,
    var total: Int = 0
)

@Serializable
data class Article(
    var apkLink: String = "",
    var audit: Int = 0,
    var author: String = "",
    var canEdit: Boolean = false,
    var chapterId: Int = 0,
    var chapterName: String = "",
    var collect: Boolean = false,
    var courseId: Int = 0,
    var desc: String = "",
    var descMd: String = "",
    var envelopePic: String = "",
    var fresh: Boolean = false,
    var host: String = "",
    var id: Int = 0,
    var isAdminAdd: Boolean = false,
    var link: String = "",
    var niceDate: String = "",
    var niceShareDate: String = "",
    var origin: String = "",
    var prefix: String = "",
    var projectLink: String = "",
    var publishTime: Long = 0,
    var realSuperChapterId: Int = 0,
    var selfVisible: Int = 0,
    var shareDate: Long = 0,
    var shareUser: String = "",
    var superChapterId: Int = 0,
    var superChapterName: String = "",
    var tags: List<Tag> = listOf(),
    var title: String = "",
    var type: Int = 0,
    var userId: Int = 0,
    var visible: Int = 0,
    var zan: Int = 0
) {
    fun isTop() = type == 1
    fun hasTags() = tags.isNotEmpty()
    fun optGetFirstTag(): Tag? = tags.optGet(0)
}

@Serializable
data class Tag(
    var name: String,
    var url: String
) : BaseBean()