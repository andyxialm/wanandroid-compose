package cn.refactor.wancompose.model

import cn.refactor.wancompose.arch.bean.base.BaseBean
import kotlinx.serialization.Serializable


/**
 * Created on 2022/11/1.
 *
 * @author andy
 */
@Serializable
data class Banner(
    var desc: String = "",
    var id: Int = 0,
    var imagePath: String = "",
    var isVisible: Int = 0,
    var order: Int = 0,
    var title: String = "",
    var type: Int = 0,
    var url: String = ""
) : BaseBean()

@Serializable
data class BannerData(
    var data: List<Banner> = listOf()
) : BaseBean()