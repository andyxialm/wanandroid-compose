package cn.refactor.wancompose.model

import cn.refactor.wancompose.arch.bean.base.BaseBean
import kotlinx.serialization.Serializable


/**
 * Created on 2022/11/1.
 *
 * @author andy
 */

/**
 * 登录
 */
@Serializable
data class LoginResponse(
    var `data`: User? = null,
    var errorCode: Int = 0,
    var errorMsg: String = ""
)

@Serializable
data class User(
    var admin: Boolean = false,
    var chapterTops: List<String> = listOf(),
    var coinCount: Int = 0,
    var collectIds: List<String> = listOf(),
    var email: String = "",
    var icon: String = "",
    var id: Int = 0,
    var nickname: String = "",
    var password: String = "",
    var publicName: String = "",
    var token: String = "",
    var type: Int = 0,
    var username: String = "",
    // 积分补充字段
    var level: Int = 0,
    var rank: String = "",
    var userId: Int = 0,
): BaseBean()