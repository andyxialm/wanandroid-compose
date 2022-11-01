package cn.refactor.wancompose.arch.graphs

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import cn.refactor.wancompose.R

/**
 * Created on 2022/10/30.
 *
 * @author andy
 */
sealed class NavGraphs(val route: String) {

    interface BottomNavItem {
        @get:StringRes val textResourceId: Int
        @get:DrawableRes val iconResourceId: Int
        val route: String
    }
    // 主页 Main
    object MAIN: NavGraphs("main")

    // 首页
    object HOME: NavGraphs("home"), BottomNavItem {
        override val textResourceId: Int
            get() = R.string.home_menu_home
        override val iconResourceId: Int
            get() = R.drawable.ic_menu_home
    }
    // 广场
    object SQUARE: NavGraphs("square"), BottomNavItem {
        override val textResourceId: Int
            get() = R.string.home_menu_square
        override val iconResourceId: Int
            get() = R.drawable.ic_menu_square
    }
    // 项目
    object PROJECT: NavGraphs("project"), BottomNavItem {
        override val textResourceId: Int
            get() = R.string.home_menu_project
        override val iconResourceId: Int
            get() = R.drawable.ic_menu_project
    }
    // 博客
    object BLOG: NavGraphs("blog"), BottomNavItem {
        override val textResourceId: Int
            get() = R.string.home_menu_blog
        override val iconResourceId: Int
            get() = R.drawable.ic_menu_blog
    }
    // 个人
    object PROFILE: NavGraphs("profile"), BottomNavItem {
        override val textResourceId: Int
            get() = R.string.home_menu_profile
        override val iconResourceId: Int
            get() = R.drawable.ic_menu_profile
    }
    // 登录页
    object LOGIN: NavGraphs("login")
    // Web页
    object WEB: NavGraphs("web?url={url}")
}