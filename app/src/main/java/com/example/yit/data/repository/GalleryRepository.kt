package com.example.yit.data.repository

import android.util.Log
import com.example.yit.data.models.Response
import com.example.yit.domain.repository.GalleryApi

class GalleryRepository (
    private val api: GalleryApi
) {
    companion object {
        const val TAG = "GalleryRepository"
        private val KEY = "6814610-cd083c066ad38bb511337fb2b"
        private val PER_PAGE = 30
    }

    suspend fun getGallery(query: String, page: Int): Response {
        Log.d(TAG, "getGallery")

        val response = try {
            api.getGallery(query = query, page = page, key = KEY, perPage = PER_PAGE)
        } catch (exception: Exception) {
            return Response.Error(exception)
        }
        return Response.Success(response)
    }

}