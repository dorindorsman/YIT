package com.example.utils

import android.util.Log
import androidx.compose.ui.unit.Dp
import com.example.yit.domain.models.Image
import com.example.yit.local.models.ImageEntity

object ImagesMapper {
    //fixme

    private const val TAG = "ImagesMapper"

    fun mapImageEntityToProduct(imagesEntityList: List<ImageEntity>): List<Image> {
        Log.d(TAG, "mapProductEntityToProduct")
        return imagesEntityList.map { entity ->
            refactorImageEntityToImage(entity)
        }
    }

    private fun refactorImageEntityToImage(entity: ImageEntity): Image {
        return Image(

        )
    }

    fun mapImageToImageEntity(imagesList: List<Image>): List<ImageEntity> {
        Log.d(TAG, "mapProductToProductEntity")
        return imagesList.map { image ->
            refactorImageToImageEntity(image)
        }
    }

    private fun refactorImageToImageEntity(image: Image): ImageEntity {
        return ImageEntity(
            id = image.id,
            pageURL = image.pageURL,
            description = image.description,
            type = image.type,
            tags = image.tags?.split(','),
            previewURL = image.previewURL,
            previewWidth = Dp(image.previewWidth?.toFloat() ?: 0f),
            previewHeight = Dp(image.previewHeight?.toFloat() ?: 0f),
            webformatURL = image.webformatURL,
            webformatWidth = Dp(image.webformatWidth?.toFloat() ?: 0f),
            webformatHeight = Dp(image.webformatHeight?.toFloat() ?: 0f),
            largeImageURL = image.largeImageURL,
            imageWidth = Dp(image.imageWidth?.toFloat() ?: 0f),
            imageHeight = Dp(image.imageHeight?.toFloat() ?: 0f),
            imageSize = Dp(image.imageSize?.toFloat() ?: 0f),
            views = image.views,
            downloads = image.downloads,
            collections = image.collections,
            likes = image.likes,
            comments = image.comments,
            user_id = image.user_id,
            user = image.user,
            userImageURL = image.userImageURL
        )
    }

}