package com.example.yit.yit_gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.yit.data.repository.GalleryProvider
import com.example.yit.data.repository.GalleryRepository
import com.example.yit.local.repository.PreferencesManager
import com.example.yit.local.repository.SearchGalleryRepository

class GalleryViewModelFactory(private val preferencesManager: PreferencesManager) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val galleryRepository = GalleryRepository(GalleryProvider.provide())
        return GalleryViewModel(
            galleryRepository,
            SearchGalleryRepository,
            preferencesManager
        ) as T
    }
}