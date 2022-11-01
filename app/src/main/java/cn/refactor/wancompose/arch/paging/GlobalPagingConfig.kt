package cn.refactor.wancompose.arch.paging

import androidx.paging.PagingConfig
import androidx.paging.PagingSource

/**
 * Created on 2022/11/1.
 *
 * @author andy
 */
data class GlobalPagingConfig(
    val pageSize: Int = 20,
    val prefetchDistance: Int = 10,
    val enablePlaceholders: Boolean = true,
    val initialLoadSize: Int = 20,
    val maxSize: Int = PagingConfig.MAX_SIZE_UNBOUNDED,
    val jumpThreshold: Int = PagingSource.LoadResult.Page.COUNT_UNDEFINED
)