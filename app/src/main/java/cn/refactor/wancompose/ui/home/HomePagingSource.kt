package cn.refactor.wancompose.ui.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import cn.refactor.wancompose.arch.network.Api
import cn.refactor.wancompose.model.Article
import cn.refactor.wancompose.model.Banner
import cn.refactor.wancompose.model.BannerData
import cn.refactor.wancompose.model.HomeArticleListResponse
import com.drake.net.Get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created on 2022/11/1.
 *
 * @author andy
 */
class HomePagingSource : PagingSource<Int, Any>() {

    private var page = 0

    override fun getRefreshKey(state: PagingState<Int, Any>): Int {
        return page
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Any> {
        return withContext(Dispatchers.IO) {
            val list = mutableListOf<Any>()
            try {
                if (params.key == 0) {
                    // 顶部 Banner
                    val bannerResponse = Get<List<Banner>>(Api.HOME_BANNER_LIST).await()
                    list.add(BannerData(bannerResponse))

                    // 首页置顶文章
                    list.addAll(Get<List<Article>>(Api.HOME_TOP_ARTICLE_LIST).await())
                }

                // 首页文章列表
                val articleList = Get<HomeArticleListResponse>(Api.HOME_ARTICLE_LIST.format(page)).await().datas
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