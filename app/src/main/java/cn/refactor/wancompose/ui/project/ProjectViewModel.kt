package cn.refactor.wancompose.ui.project

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.scopeLife
import cn.refactor.wancompose.arch.livedata.ListLiveData
import cn.refactor.wancompose.arch.network.Api
import cn.refactor.wancompose.model.Category
import cn.refactor.wancompose.ui.widget.state.State
import com.drake.net.Get

/**
 * Created on 2022/11/6.
 *
 * @author andy
 */
class ProjectViewModel : ViewModel() {
    var uiState = ProjectUiState()
        private set

    fun fetchCategories() {
        uiState.apply {
            if (categories.value.isNotEmpty()) return
            state.value = State.Loading
            scopeLife {
                try {
                    categories.value =
                        Get<List<Category>>(Api.PROJECT_CATEGORIES).await().toMutableList()
                    state.value = State.Content
                } catch (e: Exception) {
                    state.value = State.NetworkError
                }
            }
        }
    }
}

class ProjectUiState {
    val categories = ListLiveData<Category>()
    val state = MutableLiveData<State>(State.Content)
}