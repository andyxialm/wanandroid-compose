package cn.refactor.wancompose.ui.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cn.refactor.wancompose.R
import cn.refactor.wancompose.ui.widget.state.State
import cn.refactor.wancompose.ui.widget.state.WanMultiStateBox
import cn.refactor.wancompose.ui.widget.state.rememberMultiState

/**
 * Created on 2022/11/7.
 *
 * @author andy
 */
@Composable
fun SearchScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Toolbar {
            navController.popBackStack()
        }
        WanMultiStateBox(
            onReload = {},
            userDefined = { UnderConstruction() },
            state = rememberMultiState(state = State.UserDefined)
        ) {}
    }
}

@Composable
private fun UnderConstruction() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = "👷👷‍👷", fontSize = 60.sp,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(R.string.snack_under_construction), fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
private fun Toolbar(onClickBack: () -> Unit) {
    TopAppBar(
        title = { Text(stringResource(R.string.home_menu_search)) },
        navigationIcon = {
            IconButton(onClick = { onClickBack() }) {
                Icon(painter = painterResource(R.drawable.ic_toolbar_back), contentDescription = stringResource(R.string.accessibility_toolbar_back))
            }
        }
    )
}