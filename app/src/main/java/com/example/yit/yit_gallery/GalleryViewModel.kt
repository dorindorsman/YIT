package com.example.yit.yit_gallery

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yit.data.models.Response
import com.example.yit.data.repository.GalleryRepository
import com.example.yit.domain.models.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GalleryViewModel(
    private val galleryRepository: GalleryRepository,
) : ViewModel() {

    companion object {
        const val TAG = "GalleryViewModel"
    }

    var images: List<Image> by mutableStateOf(
        emptyList()
    )

    fun handle(event: GalleryEvent) {
        Log.d(TAG, "$event")
        when (event) {
            else -> {}
        }
    }

    fun handle(event: Lifecycle.Event) {
        Log.d(TAG, "$event")
        when (event) {
            Lifecycle.Event.ON_START -> onStart()
            else -> Unit
        }
    }

    private fun onStart() {
        Log.d(TAG, "onStart")
        getRemoteImages()
    }

//FIXME
//    private fun setImagesList() = viewModelScope.launch(Dispatchers.IO) {
//        Log.d(TAG, "setImagesList")
//        galleryRepository.isEmpty().collect { count ->
//            if (count != 0) {
//                getLocalProducts()
//            } else {
//                getRemoteProducts()
//            }
//        }
//    }

    private fun getRemoteImages() = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "getRemoteProducts")

        galleryRepository.getGallery().also { response ->
            if (response is Response.Success) {
                Log.d(TAG, "${response.data}")
                images = response.data.hits.toMutableStateList()
                Log.d(TAG, "${images}")
            } else if (response is Response.Error) {
                response.apply {
                    Log.e(TAG, "${error.message}")
                }
            }
        }
    }
}