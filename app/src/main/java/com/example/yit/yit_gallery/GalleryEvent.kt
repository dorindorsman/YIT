package com.example.yit.yit_gallery

sealed class GalleryEvent {
    data class SetSearchQuery(val query: String): GalleryEvent()
    data class SetSearchActive(val active: Boolean): GalleryEvent()
    data class AddHistorySearch(val query: String): GalleryEvent()
    data class SearchImages(val query: String) : GalleryEvent()
    data object LoadNextPage : GalleryEvent()
}