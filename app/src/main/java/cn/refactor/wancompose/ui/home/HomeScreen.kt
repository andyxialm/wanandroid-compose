package cn.refactor.wancompose.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import cn.refactor.wancompose.R
import cn.refactor.wancompose.arch.graphs.NavGraphs
import cn.refactor.wancompose.model.Article
import cn.refactor.wancompose.model.BannerData
import cn.refactor.wancompose.ui.widget.list.RefreshStateLazyColumn
import coil.compose.AsyncImage
import com.lt.compose_views.image_banner.ImageBanner


/**
 * Created on 2022/10/30.
 *
 * @author andy
 */
@Composable
fun HomeScreen(navController: NavController) {
    Column {
        Toolbar(onClickSearch = { navController.navigate(NavGraphs.PROFILE.route) })
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
private fun Toolbar(onClickSearch: () -> Unit) {
    TopAppBar(
        title = { Text(stringResource(R.string.home_menu_home)) },
        actions = {
            IconButton(onClick = { onClickSearch() }) {
                Icon(
                    painter = painterResource(R.drawable.ic_toolbar_search),
                    contentDescription = stringResource(R.string.accessibility_toolbar_search)
                )
            }
        }
    )
}

@Composable
private fun ListContent(
    vm: HomeViewModel = viewModel(),
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
                is BannerData -> BannerItem(data) { url -> onClick(url) }
                is Article -> ArticleItem(model = data) { url -> onClick(url) }
            }
        }
    }
}

@Composable
fun BannerItem(model: BannerData, onClick: (url: String) -> Unit) {
    if (model.data.isEmpty()) return
    ImageBanner(
        imageSize = model.data.size,
        imageContent = {
            val data = model.data[index]
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .aspectRatio(16 / 9F)
                    .clickable { onClick(data.url) }) {
                AsyncImage(
                    model = data.imagePath, contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        },
        indicatorItem = {
            Column {
                Spacer(
                    modifier = Modifier
                        .size(10.dp, 4.dp)
                        .background(Color.Gray, RoundedCornerShape(4.dp))
                )
                Spacer(Modifier.height(8.dp))
            }
        },
        selectIndicatorItem = {
            Column {
                Spacer(
                    modifier = Modifier
                        .size(10.dp, 4.dp)
                        .background(Color.Green, RoundedCornerShape(4.dp))
                )
                Spacer(Modifier.height(8.dp))
            }
        },
        autoScrollTime = 3000L
    )
}

@Composable
fun ArticleItem(
    model: Article,
    extensionContent: @Composable () -> Unit = {},
    onClick: (url: String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick(model.link) }
        ) {
            Column(Modifier.padding(8.dp)) {
                // 标签 置顶/首标签
                if (model.isTop() || model.hasTags()) {
                    Row {
                        if (model.isTop()) {
                            Text(
                                text = stringResource(R.string.article_tag_top),
                                style = TextStyle(color = Color.Red, fontSize = 12.sp),
                                modifier = Modifier.padding(end = 8.dp)
                            )
                        }
                        model.optGetFirstTag()?.let { tag ->
                            Text(
                                text = tag.name, style = TextStyle(
                                    color = colorResource(android.R.color.holo_green_dark),
                                    fontSize = 12.sp
                                )
                            )
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                }
                // 标题
                Text(text = model.title, style = TextStyle(fontSize = 16.sp))
                Spacer(Modifier.height(8.dp))

                // 扩展内容，可自定义
                extensionContent()

                // 作者 时间
                Row {
                    if (model.author.isNotEmpty()) {
                        Text(
                            text = model.author, style = TextStyle(
                                colorResource(android.R.color.darker_gray), fontSize = 12.sp
                            ), modifier = Modifier.padding(end = 8.dp)
                        )
                    }
                    if (model.niceDate.isNotEmpty()) {
                        Text(
                            text = model.niceDate, style = TextStyle(
                                colorResource(android.R.color.darker_gray), fontSize = 12.sp
                            )
                        )
                    }
                }
            }
        }
    }
}