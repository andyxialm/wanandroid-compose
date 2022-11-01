package cn.refactor.wancompose.ui.widget.state

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Created on 2022/10/31.
 *
 * @author andy
 */
sealed class MultiState {
    object Content : MultiState()
    object Empty : MultiState()
    object Loading : MultiState()
    object Error : MultiState()
    object NetworkError : MultiState()
    object UserDefined : MultiState()
}

@Composable
fun MultiStateBox(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    propagateMinConstraints: Boolean = false,
    state: State = State(MultiState.Content),
    empty: @Composable () -> Unit = {},
    loading: @Composable () -> Unit = {},
    error: @Composable () -> Unit = {},
    networkError: @Composable () -> Unit = {},
    userDefined: @Composable () -> Unit = {},
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = contentAlignment,
        propagateMinConstraints = propagateMinConstraints,
    ) {
        when (state.state) {
            MultiState.Content -> content()
            MultiState.Empty -> empty()
            MultiState.Loading -> loading()
            MultiState.Error -> error()
            MultiState.NetworkError -> networkError()
            MultiState.UserDefined -> userDefined()
        }
    }
}

@Stable
class State(state: MultiState) {
    var state: MultiState by mutableStateOf(state)
}

@Composable
fun rememberMultiState(
    state: MultiState
): State {
    return remember { State(state) }.apply {
        this.state = state
    }
}