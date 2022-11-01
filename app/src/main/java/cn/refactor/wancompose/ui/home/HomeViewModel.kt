package cn.refactor.wancompose.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import cn.refactor.wancompose.arch.livedata.BooleanLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch

/**
 * Created on 2022/10/31.
 *
 * @author andy
 */
class HomeViewModel : ViewModel() {
    var uiState = HomeUiState(viewModelScope)
        private set

    fun fetchData(clear: Boolean = false) {
        uiState.refreshing.value = true
    }
}

class HomeUiState(viewModelScope: CoroutineScope) {
    val refreshing = BooleanLiveData()
    val list = Pager(
        config = PagingConfig(
            pageSize = 20,
            prefetchDistance = 10,
        ),
        initialKey = 0,
    ) {
        HomePagingSource()
    }.flow.cachedIn(viewModelScope)
        .catch {
            refreshing.value = false
        }
}