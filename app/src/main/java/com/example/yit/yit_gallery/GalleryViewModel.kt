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
import com.example.yit.local.models.ImageEntity
import com.example.yit_images.ImagesMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GalleryViewModel(
    private val galleryRepository: GalleryRepository,
) : ViewModel() {

    companion object {
        const val TAG = "GalleryViewModel"
    }

    var images: List<ImageEntity> by mutableStateOf(
        emptyList()
    )
    var searchText by mutableStateOf("")
    var searchActive by mutableStateOf(false)
    var history: List<String> by mutableStateOf(
        emptyList()
    )

    fun handle(event: GalleryEvent) {
        Log.d(TAG, "$event")
        when (event) {
            is GalleryEvent.SetSearchActive -> setSearchActive(event.active)
            is GalleryEvent.SetSearchText -> setSearchText(event.text)
            is GalleryEvent.AddHistorySearch -> addHistorySearch(event.text)
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
                images = ImagesMapper.mapImageToImageEntity(response.data.hits.toMutableStateList())
                Log.d(TAG, "${images}")
            } else if (response is Response.Error) {
                response.apply {
                    Log.e(TAG, "${error.message}")
                }
            }
        }
    }

    private fun setSearchActive(active: Boolean) {
        searchActive = active
    }

    private fun setSearchText(text: String) {
        searchText = text
    }

    private fun addHistorySearch(text: String) {
        history.toMutableStateList().add(0,text)
    }


}