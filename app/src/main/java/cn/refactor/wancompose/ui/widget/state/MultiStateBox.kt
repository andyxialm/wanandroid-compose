package cn.refactor.wancompose.ui.widget.state

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
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
sealed class State {
    object Content : State()
    object Empty : State()
    object Loading : State()
    object Error : State()
    object NetworkError : State()
    object UserDefined : State()
}

@Composable
fun MultiStateBox(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    propagateMinConstraints: Boolean = false,
    state: MultiState = MultiState(State.Content),
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
            State.Content -> content()
            State.Empty -> empty()
            State.Loading -> loading()
            State.Error -> error()
            State.NetworkError -> networkError()
            State.UserDefined -> userDefined()
        }
    }
}


class MultiState(state: State) {
    var state: State by mutableStateOf(state)
}

@Composable
fun rememberMultiState(state: State): MultiState {
    return remember { MultiState(state) }
}