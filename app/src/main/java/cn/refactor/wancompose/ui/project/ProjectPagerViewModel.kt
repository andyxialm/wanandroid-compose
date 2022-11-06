package cn.refactor.wancompose.ui.project

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
class ProjectPagerViewModel(categoryId: String) : ViewModel() {
    var uiState = ProjectPagerUiState(viewModelScope, categoryId)
        private set
}

class ProjectPagerUiState(viewModelScope: CoroutineScope, categoryId: String) {
    val list = Pager(
        config = PagingConfig(
            pageSize = 20,
            prefetchDistance = 10,
        ),
        initialKey = 1,
    ) {
        ProjectPagingSource(categoryId)
    }.flow.cachedIn(viewModelScope)
}