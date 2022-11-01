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

    // 登录
    const val LOGIN = "user/login"

    // 登出
    const val LOGOUT = "user/logout/json"

}