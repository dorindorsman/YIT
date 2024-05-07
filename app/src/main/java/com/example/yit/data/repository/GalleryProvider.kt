package com.example.yit.data.repository

import android.util.Log
import com.example.yit.domain.repository.GalleryApi
import retrofit2.converter.gson.GsonConverterFactory

object GalleryProvider {

    const val TAG = "GalleryProvider"
    const val BASE_URL = "https://pixabay.com/api/"

    fun provide(): GalleryApi {
        Log.d(TAG, "provide")
        return retrofit2.Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(GalleryApi::class.java)
    }

}