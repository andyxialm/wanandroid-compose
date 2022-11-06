package cn.refactor.wancompose.ui.blog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cn.refactor.wancompose.model.Blogger
import cn.refactor.wancompose.ui.widget.state.State
import cn.refactor.wancompose.ui.widget.state.WanMultiStateBox
import cn.refactor.wancompose.ui.widget.state.rememberMultiState
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


/**
 * Created on 2022/10/30.
 *
 * @author andy
 */
@Composable
fun BlogScreen(navController: NavController) {
    BloggerPage()
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BloggerPage(vm: BlogViewModel = viewModel()) {
    val uiState = vm.bloggersUiState
    val multiState = uiState.state.observeAsState().value
    val bloggers = uiState.bloggers.observeAsState().value
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    LaunchedEffect(Unit) {
        vm.fetchBloggers()
    }
    bloggers ?: return

    WanMultiStateBox(
        state = rememberMultiState(state = multiState ?: State.Content),
        onReload = { vm.fetchBloggers() }
    ) {
        Column {
            Toolbar(bloggers, pagerState.currentPage) {
                coroutineScope.launch {
                    pagerState.scrollToPage(it)
                }
            }
            HorizontalPager(
                count = bloggers.size,
                state = pagerState,
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(text = bloggers[it].name, modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}

@Composable
private fun Toolbar(
    tabs: List<Blogger>,
    selectedTabIndex: Int,
    onClickTab: (index: Int) -> Unit) {
    if (tabs.isEmpty()) return
    TopAppBar {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Bottom),
            edgePadding = 0.dp
        ) {
            tabs.forEachIndexed { index, t ->
                Text(
                    text = t.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onClickTab(index) }
                        .padding(8.dp),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}