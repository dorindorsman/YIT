package com.example.yit.yit_gallery

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.yit.data.repository.GalleryProvider
import com.example.yit.data.repository.GalleryRepository
import com.example.yit.local.repository.SearchGalleryRepository

class GalleryViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//fixme
//        val appDatabase = AppDatabaseProvider.provide(context)
        val galleryRepository = GalleryRepository(GalleryProvider.provide())

        return GalleryViewModel(
            galleryRepository,
            SearchGalleryRepository
        ) as T
    }
}