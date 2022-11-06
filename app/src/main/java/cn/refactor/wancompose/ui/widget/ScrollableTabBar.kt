package cn.refactor.wancompose.ui.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Created on 2022/11/6.
 *
 * @author andy
 */

@Composable
fun <T: Any> ScrollableTabBar(
    modifier: Modifier = Modifier,
    tabs: List<T>,
    selectedTabIndex: Int,
    onClickTab: (index: Int) -> Unit = {},
    tabContent: @Composable BoxScope.(index: Int, data: T) -> Unit
) {
    if (tabs.isEmpty()) return
    TopAppBar {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.Bottom),
            edgePadding = 0.dp
        ) {
            tabs.forEachIndexed { index, t ->
                Box(modifier = Modifier
                    .clickable { onClickTab(index) }) {
                    tabContent(index, t)
                }
            }
        }
    }
}