package com.example.yit.yit_gallery

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.yit.local.models.ImageEntity

@Composable
fun GridImageView(image: ImageEntity, navigateToImage: (id: Int) -> Unit) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    var orientation by remember { mutableIntStateOf(Configuration.ORIENTATION_PORTRAIT) }

    LaunchedEffect(configuration) {
        // Save any changes to the orientation value on the configuration object
        snapshotFlow { configuration.orientation }
            .collect { orientation = it }
    }

    var itemHeight = if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        screenHeight / 8
    } else {
        screenHeight / 12
    }

    Image(
        modifier = Modifier
            .height(itemHeight)
            .wrapContentWidth()
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                navigateToImage(image.id)
            },
        painter = rememberAsyncImagePainter(image.previewURL),
        contentDescription = null,
        contentScale = ContentScale.FillHeight
    )
}
