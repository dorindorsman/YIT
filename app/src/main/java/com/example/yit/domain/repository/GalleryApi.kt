package com.example.yit.domain.repository

import com.example.yit.domain.models.Gallery
import retrofit2.http.GET

interface GalleryApi {
    @GET(" ")
    suspend fun getGallery(): Gallery
}