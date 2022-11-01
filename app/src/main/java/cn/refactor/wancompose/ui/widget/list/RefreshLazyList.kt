package cn.refactor.wancompose.ui.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cn.refactor.wancompose.ui.widget.state.MultiState
import cn.refactor.wancompose.ui.widget.state.WanMultiStateBox
import cn.refactor.wancompose.ui.widget.state.rememberMultiState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

/**
 * Created on 2022/10/31.
 *
 * @author andy
 */
@Composable
fun RefreshListContainer(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean = false,
    onRefresh: () -> Unit,
    lazyListState: LazyListState = rememberLazyListState(),
    content: LazyListScope.() -> Unit
) {
    val refreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)
    val multiState = rememberMultiState(state = MultiState.Content)

    SwipeRefresh(
        state = refreshState,
        onRefresh = { onRefresh() },
        modifier = modifier
    ) {
        WanMultiStateBox(
            state = multiState,
            modifier = modifier.fillMaxSize(),
            onReload = { onRefresh() }
        ) {
            LazyColumn(
                modifier = modifier,
                state = lazyListState,
            ) {
                content()
                item {
                    LoadMore()
                }
            }
        }
    }
}

@Composable
fun LoadMore() {
    Box(modifier = Modifier.fillMaxWidth()) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.Center)
                .width(24.dp)
                .height(24.dp),
            strokeWidth = 2.dp
        )
    }
}