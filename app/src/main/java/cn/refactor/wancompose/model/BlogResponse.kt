package cn.refactor.wancompose.model

import cn.refactor.wancompose.arch.bean.base.BaseBean
import kotlinx.serialization.Serializable

/**
 * Created on 2022/11/6.
 *
 * @author andy
 */
@Serializable
data class Blogger(
    val articleList: List<Article>,
    val author: String,
    val courseId: Int,
    val cover: String,
    val desc: String,
    val id: Int,
    val lisense: String,
    val lisenseLink: String,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val type: Int,
    val userControlSetTop: Boolean,
    val visible: Int
): BaseBean()