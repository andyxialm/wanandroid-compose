package cn.refactor.wancompose.ui.square

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import cn.refactor.wancompose.R
import cn.refactor.wancompose.arch.graphs.NavGraphs
import cn.refactor.wancompose.model.Article
import cn.refactor.wancompose.ui.home.ArticleItem
import cn.refactor.wancompose.ui.widget.list.RefreshStateLazyColumn


/**
 * Created on 2022/10/30.
 *
 * @author andy
 */
@Composable
fun SquareScreen(navController: NavController) {
    Column {
        TopAppBar(
            title = { Text(stringResource(R.string.home_menu_square)) },
        )
        ListContent { url ->
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

@Composable
private fun ListContent(
    vm: SquareViewModel = viewModel(),
    onClick: (url: String) -> Unit
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