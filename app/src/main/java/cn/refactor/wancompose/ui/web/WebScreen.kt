package cn.refactor.wancompose.ui.web

import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import cn.refactor.wancompose.R

/**
 * Created on 2022/11/1.
 *
 * @author andy
 */
@Composable
fun WebScreen(
    navController: NavController,
    url: String?
) {
    Column {
        Toolbar {
            navController.popBackStack()
        }
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { context ->
                FrameLayout(context).apply {
                    layoutParams = FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT
                    )

                    val webView = WebView(context).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        webChromeClient = WebChromeClient()
                        webViewClient = WebViewClient()
                        url?.let { loadUrl(it) }
                    }
                    addView(webView)
                }
            },
            update = {}
        )
    }
}

@Composable
private fun Toolbar(onClickBack: () -> Unit) {
    TopAppBar(
        title = { Text(stringResource(R.string.web_title)) },
        navigationIcon = {
            IconButton(onClick = { onClickBack() }) {
                Icon(painter = painterResource(R.drawable.ic_toolbar_back), contentDescription = stringResource(R.string.accessibility_toolbar_back))
            }
        }
    )
}