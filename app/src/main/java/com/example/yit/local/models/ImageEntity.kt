package com.example.yit.local.models

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ImageEntity(
    var id: Int = 0,
    var pageURL: String? = null,
    var description: String? = null,
    var type: String? = null,
    var tags: List<String?>? = null,
    var previewURL: String? = null,
    var previewWidth: Dp = 0.dp,
    var previewHeight: Dp = 0.dp,
    var webformatURL: String? = null,
    var webformatWidth: Dp = 0.dp,
    var webformatHeight: Dp = 0.dp,
    var largeImageURL: String? = null,
    var imageWidth: Dp = 0.dp,
    var imageHeight: Dp = 0.dp,
    var imageSize: Dp = 0.dp,
    var views: Int? = null,
    var downloads: Int? = null,
    var collections: Int? = null,
    var likes: Int? = null,
    var comments: Int? = null,
    var user_id: Int? = null,
    var user: String? = null,
    var userImageURL: String? = null,
)
