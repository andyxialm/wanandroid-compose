package cn.refactor.wancompose.ui.blog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import kotlinx.coroutines.CoroutineScope

/**
 * Created on 2022/11/6.
 *
 * @author andy
 */
class BlogPagerViewModel(bloggerId: String) : ViewModel() {
    var uiState = BlogPagerUiState(viewModelScope, bloggerId)
        private set
}

class BlogPagerUiState(viewModelScope: CoroutineScope, bloggerId: String) {
    val list = Pager(
        config = PagingConfig(
            pageSize = 20,
            prefetchDistance = 10,
        ),
        initialKey = 1,
    ) {
        BlogPagingSource(bloggerId)
    }.flow.cachedIn(viewModelScope)
}