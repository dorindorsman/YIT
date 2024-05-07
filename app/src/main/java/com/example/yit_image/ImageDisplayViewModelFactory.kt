package com.example.yit_image

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.yit.local.repository.SearchGalleryRepository

class ImageDisplayViewModelFactory(private val imageId: Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ImageDisplayViewModel(
            SearchGalleryRepository,
            imageId
        ) as T
    }
}