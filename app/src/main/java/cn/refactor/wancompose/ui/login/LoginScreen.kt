package cn.refactor.wancompose.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cn.refactor.wancompose.R
import com.lt.compose_views.other.VerticalSpace
import com.lt.compose_views.text_field.GoodTextField
import com.lt.compose_views.text_field.HintComposeWithTextField
import com.lt.compose_views.text_field.PasswordTextField

/**
 * Created on 2022/11/7.
 *
 * @author andy
 */
@Composable
fun LoginScreen(navController: NavController) {
    val vm: LoginViewModel = viewModel()
    val logged = vm.logged.observeAsState().value == true
    if (logged) {
        LaunchedEffect(Unit) {
            navController.popBackStack()
        }
        return
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Toolbar {
            navController.popBackStack()
        }
        Column(
            Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            val username = remember { mutableStateOf("") }
            val password = remember { mutableStateOf("") }
            val isShowPassword = remember { mutableStateOf(false) }
            val hintInputUsername = stringResource(R.string.login_hint_input_account)
            val hintInputPassword = stringResource(R.string.login_hint_input_password)

            VerticalSpace(dp = 16)
            GoodTextField(
                value = username.value,
                onValueChange = { username.value = it },
                hint = remember {
                    HintComposeWithTextField.createTextHintCompose(hintInputUsername)
                },
                modifier = Modifier.height(40.dp)
            )
            VerticalSpace(dp = 16)
            PasswordTextField(
                value = password.value,
                onValueChange = { password.value = it },
                passwordIsShow = isShowPassword.value,
                hint = remember {
                    HintComposeWithTextField.createTextHintCompose(hintInputPassword)
                },
                onPasswordIsShowChange = { isShowPassword.value = it },
                modifier = Modifier.height(40.dp)
            )
            VerticalSpace(dp = 16)
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { vm.login(username = username.value, password = password.value) },
                enabled = username.value.isNotEmpty() && password.value.isNotEmpty()
            ) {
                Text(text = stringResource(R.string.login_button_text), fontSize = 16.sp)
            }
        }
    }
}

@Composable
private fun Toolbar(onClickBack: () -> Unit) {
    TopAppBar(
        title = { Text(stringResource(R.string.login_title)) },
        navigationIcon = {
            IconButton(onClick = { onClickBack() }) {
                Icon(painter = painterResource(R.drawable.ic_toolbar_back), contentDescription = stringResource(R.string.accessibility_toolbar_back))
            }
        }
    )
}