package com.example.yit.data.repository

import android.util.Log
import com.example.yit.data.models.Response
import com.example.yit.domain.repository.GalleryApi

class GalleryRepository (
    private val api: GalleryApi
) {
    companion object {
        const val TAG = "GalleryRepository"
    }

    suspend fun getGallery(): Response {
        Log.d(TAG, "getGallery")

        val response = try {
            api.getGallery()
        } catch (exception: Exception) {
            return Response.Error(exception)
        }
        return Response.Success(response)
    }

}