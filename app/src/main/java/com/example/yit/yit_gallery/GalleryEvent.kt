package com.example.yit.yit_gallery

sealed class GalleryEvent {
    data class SetSearchText(val text: String): GalleryEvent()
    data class SetSearchActive(val active: Boolean): GalleryEvent()
    data class AddHistorySearch(val text: String): GalleryEvent()
}