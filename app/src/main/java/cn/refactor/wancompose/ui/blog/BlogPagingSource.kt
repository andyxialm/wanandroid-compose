package cn.refactor.wancompose.ui.blog

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
class BlogPagingSource(private val bloggerId: String) : PagingSource<Int, Any>() {

    private var page = 1

    override fun getRefreshKey(state: PagingState<Int, Any>): Int {
        return page
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Any> {
        return withContext(Dispatchers.IO) {
            val list = mutableListOf<Any>()
            try {
                // 公众号列表
                val articleList = Get<HomeArticleListResponse>(Api.BLOGGER_ARTICLE_LIST.format(bloggerId, page)).await().datas
                list.addAll(articleList)
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