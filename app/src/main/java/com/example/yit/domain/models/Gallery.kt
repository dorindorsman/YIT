package com.example.yit.domain.models

import com.google.gson.annotations.SerializedName

data class Gallery(
    @SerializedName("total")
    var total: Int? = null,

    @SerializedName("totalHits")
    var totalHits: Int? = null,

    @SerializedName("hits")
    var hits: List<Image> = emptyList(),
)
