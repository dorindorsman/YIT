package com.example.yit_images

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.yit.local.models.ImageEntity


@Composable
fun GridImageView(image: ImageEntity){
        Image(
            modifier = Modifier
                .width(image.previewWidth)
                .height(image.previewHeight)
                .clip(RoundedCornerShape(10.dp))
                .clickable {},
            painter = rememberAsyncImagePainter(image.previewURL),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
}