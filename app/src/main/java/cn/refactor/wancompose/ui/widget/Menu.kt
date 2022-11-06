package cn.refactor.wancompose.ui.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.refactor.wancompose.R
import coil.compose.AsyncImage

/**
 * Created on 2022/11/7.
 *
 * @author andy
 */

@Composable
fun ActionMenuView(
    icon: Any?,
    text: String?,
    onClick: () -> Unit = {}
) {
    Box(modifier = Modifier
        .height(54.dp)
        .fillMaxWidth()
        .clickable { onClick() }) {
        Row(modifier = Modifier.align(Alignment.CenterStart)) {
            Spacer(modifier = Modifier.width(16.dp))
            AsyncImage(model = icon, contentDescription = null, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = text ?: "", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.Unspecified,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
        }
    }

}