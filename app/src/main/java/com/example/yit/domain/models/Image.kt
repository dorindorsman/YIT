package com.example.yit.domain.models

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("pageURL")
    var pageURL: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("type")
    var type: String? = null,

    @SerializedName("tags")
    var tags: String? = null,

    @SerializedName("previewURL")
    var previewURL: String? = null,

    @SerializedName("previewWidth")
    var previewWidth: Int? = null,

    @SerializedName("previewHeight")
    var previewHeight: Int? = null,

    @SerializedName("webformatURL")
    var webformatURL: String? = null,

    @SerializedName("webformatWidth")
    var webformatWidth: Int? = null,

    @SerializedName("webformatHeight")
    var webformatHeight: Int? = null,

    @SerializedName("largeImageURL")
    var largeImageURL: String? = null,

    @SerializedName("imageWidth")
    var imageWidth: Int? = null,

    @SerializedName("imageHeight")
    var imageHeight: Int? = null,

    @SerializedName("imageSize")
    var imageSize: Int? = null,

    @SerializedName("views")
    var views: Int? = null,

    @SerializedName("downloads")
    var downloads: Int? = null,

    @SerializedName("collections")
    var collections: Int? = null,

    @SerializedName("likes")
    var likes: Int? = null,

    @SerializedName("comments")
    var comments: Int? = null,

    @SerializedName("user_id")
    var user_id: Int? = null,

    @SerializedName("user")
    var user: String? = null,

    @SerializedName("userImageURL")
    var userImageURL: String? = null,
)
