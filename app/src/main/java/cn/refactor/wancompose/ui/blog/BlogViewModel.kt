package cn.refactor.wancompose.ui.blog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.scopeLife
import cn.refactor.wancompose.arch.livedata.ListLiveData
import cn.refactor.wancompose.arch.network.Api
import cn.refactor.wancompose.model.Blogger
import cn.refactor.wancompose.ui.widget.state.State
import com.drake.net.Get

/**
 * Created on 2022/11/6.
 *
 * @author andy
 */
class BlogViewModel : ViewModel() {
    var bloggersUiState = BloggerUiState()
        private set

    fun fetchBloggers() {
        bloggersUiState.apply {
            state.value = State.Loading
            scopeLife {
                try {
                    bloggers.value =
                        Get<List<Blogger>>(Api.BLOGGERS_LIST).await().toMutableList()
                    state.value = State.Content
                } catch (e: Exception) {
                    state.value = State.NetworkError
                }
            }
        }
    }
}

class BloggerUiState {
    val bloggers = ListLiveData<Blogger>()
    val state = MutableLiveData<State>(State.Content)
}