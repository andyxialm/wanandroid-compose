package cn.refactor.wancompose.arch.network

/**
 * Created on 2022/11/1.
 *
 * @author andy
 */
object Api {
    const val HOST = "https://www.wanandroid.com/"

    // 首页置顶文章
    const val HOME_TOP_ARTICLE_LIST = "article/top/json"

    // 首页文章列表
    const val HOME_ARTICLE_LIST = "article/list/%d/json"

    // 首页 Banner
    const val HOME_BANNER_LIST = "banner/json"

    // 公众号列表
    const val BLOGGERS_LIST = "wxarticle/chapters/json"

    // 公众号文章列表
    const val BLOGGER_ARTICLE_LIST = "wxarticle/list/%s/%d/json"

    // 广场
    const val SQUARE_USER_ARTICLE_LIST = "user_article/list/%d/json"

    // 项目分类
    const val PROJECT_CATEGORIES = "project/tree/json"

    // 项目分类下的内容列表
    const val PROJECT_CATEGORY_LIST = "project/list/%d/json?cid=%s"

    // 登录
    const val LOGIN = "user/login"

    // 登出
    const val LOGOUT = "user/logout/json"
}