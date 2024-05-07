package com.example.yit.domain.repository

import com.example.yit.domain.models.Gallery
import retrofit2.http.GET
import retrofit2.http.Query

interface GalleryApi {
    @GET(".")
    suspend fun getGallery(
        @Query("key") key: String,
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): Gallery
}