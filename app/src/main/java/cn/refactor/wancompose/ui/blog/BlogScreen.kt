package cn.refactor.wancompose.ui.blog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import cn.refactor.wancompose.arch.graphs.NavGraphs
import cn.refactor.wancompose.model.Article
import cn.refactor.wancompose.model.Category
import cn.refactor.wancompose.ui.home.ArticleItem
import cn.refactor.wancompose.ui.widget.ScrollableTabBar
import cn.refactor.wancompose.ui.widget.list.RefreshStateLazyColumn
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
@OptIn(ExperimentalPagerApi::class)
@Composable
fun BlogScreen(navController: NavController) {
    val vm: BlogViewModel = viewModel()
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
                BloggerPage(
                    category = bloggers[it]
                ) { url ->
                    navController.navigate(NavGraphs.WEB.route.replace("{url}", url)) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
@Composable
fun BloggerPage(
    category: Category,
    vm: BlogPagerViewModel = viewModel(
        key = category.id.toString(),
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return BlogPagerViewModel(category.id.toString()) as T
            }
        }
    ),
    onClick: (url: String) -> Unit = {},
) {
    val uiState = vm.uiState
    val lazyPagingItems = uiState.list.collectAsLazyPagingItems()

    RefreshStateLazyColumn(
        modifier = Modifier.fillMaxSize(),
        lazyPagingItems = lazyPagingItems,
    ) {
        items(items = lazyPagingItems) { data ->
            when (data) {
                is Article -> ArticleItem(model = data) { url -> onClick(url) }
            }
        }
    }
}

@Composable
fun Toolbar(
    tabs: List<Category>,
    selectedTabIndex: Int,
    onClickTab: (index: Int) -> Unit) {
    ScrollableTabBar(
        tabs = tabs,
        selectedTabIndex = selectedTabIndex,
        onClickTab = onClickTab
    ) { index, data ->
        val textColor = if (selectedTabIndex == index) Color.White else Color.Unspecified
        Text(
            text = data.name,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(8.dp),
            textAlign = TextAlign.Center,
            color = textColor
        )
    }
}
