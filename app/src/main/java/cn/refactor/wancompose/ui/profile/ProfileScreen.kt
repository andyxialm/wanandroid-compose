package cn.refactor.wancompose.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cn.refactor.wancompose.R
import cn.refactor.wancompose.arch.graphs.NavGraphs
import cn.refactor.wancompose.ui.widget.ActionMenuView
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

/**
 * Created on 2022/10/30.
 *
 * @author andy
 */
@Composable
fun ProfileScreen(
    navController: NavController,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val coroutineScope = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxSize()) {
        UserCard {
            navController.navigate(NavGraphs.LOGIN.route)
        }

        val snackBarMessage = stringResource(id = R.string.snack_under_construction)
        val snackBarActionLabel = stringResource(id = R.string.snack_confirm_ok)
        SettingsCard {
            coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = snackBarMessage,
                    actionLabel = snackBarActionLabel
                )
            }
        }
    }
}

@Composable
fun SettingsCard(vm: ProfileViewModel = viewModel(), onClick: () -> Unit) {
    val logged = vm.logged.collectAsState(false).value
    Column {
        Spacer(modifier = Modifier.height(160.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                ),
        ) {
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                ActionMenuView(
                    icon = R.drawable.ic_star,
                    text = stringResource(R.string.profile_settings_collect)
                ) { onClick() }
                ActionMenuView(
                    icon = R.drawable.ic_article,
                    text = stringResource(R.string.profile_settings_article)
                ) { onClick() }
                ActionMenuView(
                    icon = R.drawable.ic_settings,
                    text = stringResource(R.string.profile_settings_system)
                ) { onClick() }
                ActionMenuView(
                    icon = R.drawable.ic_website,
                    text = stringResource(R.string.profile_settings_website)
                ) { onClick() }
                ActionMenuView(
                    icon = R.drawable.ic_join,
                    text = stringResource(R.string.profile_settings_join)
                ) { onClick() }
                ActionMenuView(
                    icon = R.drawable.ic_about,
                    text = stringResource(R.string.profile_settings_about)
                ) { onClick() }

                if (logged) {
                    Box(modifier = Modifier.padding(16.dp)) {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { vm.logout() }
                        ) {
                            Text(
                                text = stringResource(id = R.string.profile_logout),
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun UserCard(
    vm: ProfileViewModel = viewModel(),
    onGotoLogin: () -> Unit = {}
) {
    val icon = R.drawable.ic_launcher_foreground
    val logged = vm.logged.collectAsState(false).value
    val username = vm.usernameFlow
        .collectAsState(initial = null)
        .value ?: stringResource(id = R.string.profile_user_card_logout)
    val coinCount = vm.coinCount.value

    if (logged) {
        LaunchedEffect(Unit) {
            vm.fetchUserInfo()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(color = colorResource(R.color.purple_500))
            .clickable(enabled = !logged) { onGotoLogin() }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = icon,
                contentDescription = stringResource(R.string.accessibility_toolbar_avatar),
                modifier = Modifier
                    .size(140.dp)
            )
            Spacer(modifier = Modifier.width(24.dp))
            Column {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = username, fontSize = 28.sp,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(R.string.profile_user_card_coil)
                        .format(if (coinCount == 0) "-" else coinCount),
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
        }
    }
}