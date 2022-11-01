package cn.refactor.wancompose.ui.widget.list

/**
 * Created on 2022/10/31.
 *
 * @author andy
 */
sealed class LoadState {
    object Refresh: LoadState()
    object LoadMore: LoadState()
}
