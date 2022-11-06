package cn.refactor.wancompose.ui.project

import androidx.paging.PagingSource
import androidx.paging.PagingState
import cn.refactor.wancompose.arch.network.Api
import cn.refactor.wancompose.model.HomeArticleListResponse
import com.drake.net.Get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created on 2022/11/6.
 *
 * @author andy
 */
class ProjectPagingSource(private val categoryId: String) : PagingSource<Int, Any>() {

    private var page = 1

    override fun getRefreshKey(state: PagingState<Int, Any>): Int {
        return page
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Any> {
        return withContext(Dispatchers.IO) {
            val list = mutableListOf<Any>()
            try {
                // 项目列表
                val projectList = Get<HomeArticleListResponse>(Api.PROJECT_CATEGORY_LIST.format(page, categoryId)).await().datas
                list.addAll(projectList)
            } catch(e: Exception) {
                return@withContext LoadResult.Error(e)
            }
            LoadResult.Page(
                data = list,
                prevKey = null,
                nextKey = if (list.size == 0) null else (++page)
            )
        }
    }
}