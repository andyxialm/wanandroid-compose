package cn.refactor.wancompose.ui.square

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
class SquareViewModel : ViewModel() {
    var uiState = SquareUiState(viewModelScope)
        private set
}

class SquareUiState(viewModelScope: CoroutineScope) {
    val list = Pager(
        config = PagingConfig(
            pageSize = 20,
            prefetchDistance = 10,
        ),
        initialKey = 0,
    ) {
        SquarePagingSource()
    }.flow.cachedIn(viewModelScope)
}