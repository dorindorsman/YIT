package com.example.yit_image

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.yit.local.repository.SearchGalleryRepository

class ImageDisplayViewModel(
//    private val galleryRepository: GalleryRepository,
    private val searchGalleryRepository: SearchGalleryRepository,
    private val imageId: Int,
) : ViewModel() {

    companion object {
        const val TAG = "ImageDisplayViewModel"
    }

    var searchResultList = searchGalleryRepository.searchResult

    fun getInitialImageIndex() : Int {
        Log.d(TAG, "getInitialImageIndex")
        val image = SearchGalleryRepository.searchResult.firstOrNull {
            it.id==imageId
        }
        return SearchGalleryRepository.searchResult.indexOf(image)
    }

}