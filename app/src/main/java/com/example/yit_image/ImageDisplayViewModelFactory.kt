package com.example.yit_image

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.yit.local.repository.SearchGalleryRepository

class ImageDisplayViewModelFactory(private val context: Context, private val imageId: Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//fixme
//        val appDatabase = AppDatabaseProvider.provide(context)
//        val galleryRepository = GalleryRepository(GalleryProvider.provide())

        return ImageDisplayViewModel(
            SearchGalleryRepository,
            imageId
        ) as T
    }
}