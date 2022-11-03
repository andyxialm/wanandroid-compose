package cn.refactor.wancompose.ui.widget.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import cn.refactor.wancompose.R
import cn.refactor.wancompose.ui.widget.state.State
import cn.refactor.wancompose.ui.widget.state.WanMultiStateBox
import cn.refactor.wancompose.ui.widget.state.rememberMultiState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

/**
 * Created on 2022/10/31.
 *
 * @author andy
 */

/**
 * @param onRefresh 默认 return false
 */
@Composable
fun <T : Any> RefreshStateLazyColumn(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
    isRefreshing: Boolean = false,
    onRefresh: () -> Unit = {},
    lazyPagingItems: LazyPagingItems<T>,
    content: LazyListScope.() -> Unit
) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)
    val multiState = rememberMultiState(state = State.Content)

    setStateIfNeeded(lazyPagingItems) {
        multiState.state = it
    }
    swipeRefreshState.isRefreshing = lazyPagingItems.loadState.refresh is LoadState.Loading
            && multiState.state != State.Loading

    SwipeRefresh(modifier = modifier, state = swipeRefreshState, onRefresh = {
        onRefresh()
        lazyPagingItems.refresh()
    }) {
        WanMultiStateBox(
            modifier = modifier,
            onReload = {
                multiState.state = State.Loading
                lazyPagingItems.refresh()
            }, state = multiState
        ) {
            LazyColumn(
                modifier = modifier,
                state = lazyListState,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                content()
                if (multiState.state != State.Content) return@LazyColumn
                item {
                    when (lazyPagingItems.loadState.append) {
                        is LoadState.Loading -> LoadingMoreItem()
                        is LoadState.Error -> ErrorItem {
                            lazyPagingItems.retry()
                        }

                        is LoadState.NotLoading -> {
                            if (lazyPagingItems.loadState.append.endOfPaginationReached) {
                                LoadMoreCompleteItem()
                            }
                        }
                    }
                }
            }
        }
    }
}

fun <T: Any> setStateIfNeeded(
    lazyPagingItems: LazyPagingItems<T>,
    onStateChanged: (state: State) -> Unit
) {
    val loadState = lazyPagingItems.loadState
    // 下拉刷新-刷新中
    // 下拉刷新-错误
    // 下拉刷新-刷新完成
    when (loadState.refresh) {
        is LoadState.Loading -> {
            if (lazyPagingItems.itemCount == 0) {
                onStateChanged(State.Loading)
            }
        }
        is LoadState.NotLoading -> {
            if (lazyPagingItems.itemCount == 0) {
                onStateChanged(State.Empty)
            } else {
                onStateChanged(State.Content)
            }
        }
        is LoadState.Error -> {
            onStateChanged(State.NetworkError)
        }
    }
}

@Composable
fun LoadingMoreItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.Center)
                .width(24.dp)
                .height(24.dp), strokeWidth = 2.dp
        )
    }
}

@Composable
fun ErrorItem(onRetry: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { onRetry() }
        ) {
            Text(stringResource(R.string.refresh_load_more_error_retry))
        }
    }
}

@Composable
fun LoadMoreCompleteItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Text(
            text = stringResource(R.string.refresh_load_complete),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}