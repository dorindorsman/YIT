package com.example.yit.yit_gallery

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.utils.ImagesMapper
import com.example.yit.data.models.Response
import com.example.yit.data.repository.GalleryRepository
import com.example.yit.local.repository.SearchGalleryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GalleryViewModel(
    private val galleryRepository: GalleryRepository,
    private val searchGalleryRepository: SearchGalleryRepository,
) : ViewModel() {

    companion object {
        const val TAG = "GalleryViewModel"
    }

    private var currentPage by mutableIntStateOf(1)
    var currentQuery by mutableStateOf("")
    var currentActive by mutableStateOf(false)
    var history by mutableStateOf<List<String>>(
        emptyList()
    )
    var searchImages by mutableStateOf(
        searchGalleryRepository.searchResult
    )

    fun handle(event: GalleryEvent) {
        Log.d(TAG, "$event")
        when (event) {
            is GalleryEvent.SetSearchActive -> setSearchActive(event.active)
            is GalleryEvent.SetSearchQuery -> setSearchQuery(event.query)
            is GalleryEvent.AddHistorySearch -> addHistorySearch(event.query)
            is GalleryEvent.SearchImages -> searchImages(event.query)
            GalleryEvent.LoadNextPage -> loadNextPage()
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
        loadImagesList()
    }


    //fixme
    private fun loadImagesList() = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "setImagesList")
        if (searchGalleryRepository.searchResult.isEmpty()) {
             //getRemoteImages()
        } else {
//            getRemoteProducts()
        }
    }

    private fun getRemoteImages(
        query: String,
        page: Int,
        resetList: Boolean = true,
    ) = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "getRemoteImages")

        galleryRepository.getGallery(
            query = query,
            page = page
        ).also { response ->
            if (response is Response.Success) {
                Log.d(TAG, "${response.data}")
                val result = ImagesMapper.mapImageToImageEntity(response.data.hits.toMutableStateList())
                Log.d(TAG, "${searchImages}")
                if (resetList) {
                    searchGalleryRepository.searchResult = result
                    searchImages = result
                } else {
                    val updatedList = searchGalleryRepository.searchResult.toMutableList()
                    updatedList.addAll(result)
                    searchGalleryRepository.searchResult = updatedList
                    searchImages = updatedList
                }
            } else if (response is Response.Error) {
                response.apply {
                    Log.e(TAG, "${error.message}")
                }
            }
        }
    }

    private fun setSearchActive(active: Boolean) {
        Log.d(TAG, "setSearchActive $active")
        currentActive = active
    }

    private fun setSearchQuery(query: String) {
        Log.d(TAG, "setSearchText $query")
        currentQuery = query
    }

    private fun addHistorySearch(query: String) {
        Log.d(TAG, "addHistorySearch $query")
        history.toMutableList().add(0, query)
        viewModelScope.launch {
            // Update search history
            val updatedHistory = history.toMutableList()
            updatedHistory.remove(query)
            updatedHistory.add(0, query)
            history = updatedHistory
        }
        Log.d(TAG, "HistorySearch: ${history}")
    }

    //fixme
    private fun searchImages(query: String) {
        Log.d(TAG, "searchImages for query:$query")
        setSearchQuery(query)
        if (query.isBlank()) {
            return
        }
        currentPage = 1
        getRemoteImages(currentQuery, currentPage)
        Log.d(TAG, "${searchGalleryRepository.searchResult}")
    }

    private fun loadNextPage() {
        Log.d(TAG, "loadNextPage")
        currentPage++
        getRemoteImages(currentQuery, currentPage, false)
    }

}