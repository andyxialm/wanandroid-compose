package cn.refactor.wancompose.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import kotlinx.coroutines.CoroutineScope

/**
 * Created on 2022/10/31.
 *
 * @author andy
 */
class HomeViewModel : ViewModel() {
    var uiState = HomeUiState(viewModelScope)
        private set
}

class HomeUiState(viewModelScope: CoroutineScope) {
    val list = Pager(
        config = PagingConfig(
            pageSize = 20,
            prefetchDistance = 10,
        ),
        initialKey = 0,
    ) {
        HomePagingSource()
    }.flow.cachedIn(viewModelScope)
}