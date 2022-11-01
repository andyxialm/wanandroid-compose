package cn.refactor.wancompose.ui.widget.state

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.refactor.wancompose.R

/**
 * Created on 2022/10/31.
 *
 * @author andy
 */
@Composable
fun WanMultiStateBox(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    propagateMinConstraints: Boolean = false,
    state: State = State(MultiState.Content),
    onReload: () -> Unit,
    userDefined: @Composable () -> Unit = {},
    content: @Composable BoxScope.() -> Unit
) {
    MultiStateBox(
        modifier = modifier,
        contentAlignment = contentAlignment,
        propagateMinConstraints = propagateMinConstraints,
        state = state,
        empty = { EmptyComposable() },
        loading = { LoadingComposable() },
        error = { ErrorComposable() },
        networkError = { NetworkErrorComposable(onReload) },
        userDefined = userDefined,
        content = content
    )
}

@Composable
private fun LoadingComposable() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .width(50.dp)
                .height(50.dp)
        )
    }
}

@Composable
private fun EmptyComposable() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(
                text = stringResource(R.string.multi_state_text_face),
                fontSize = 60.sp
            )
            Spacer(Modifier.height(14.dp))
            Text(text = stringResource(R.string.multi_state_text_empty))
        }
    }
}

@Composable
private fun ErrorComposable() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(
                text = stringResource(R.string.multi_state_text_face),
                fontSize = 60.sp
            )
            Spacer(Modifier.height(14.dp))
            Text(text = stringResource(R.string.multi_state_text_error))
        }
    }
}

@Composable
private fun NetworkErrorComposable(reload: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(
                text = stringResource(R.string.multi_state_text_face),
                fontSize = 60.sp
            )
            Spacer(Modifier.height(14.dp))
            Text(text = stringResource(R.string.multi_state_text_network_error))
            Spacer(Modifier.height(14.dp))
            Button(onClick = { reload() }) {
                Text(text = stringResource(R.string.multi_state_text_network_error_retry))
            }
        }
    }
}