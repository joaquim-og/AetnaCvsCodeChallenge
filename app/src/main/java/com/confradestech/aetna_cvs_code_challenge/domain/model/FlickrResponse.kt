package com.confradestech.aetna_cvs_code_challenge.domain.model

import com.google.gson.annotations.SerializedName

data class FlickrResponse(
    val description: String,
    val generator: String,
    @SerializedName("items")
    val flickrItems: List<FlickrItem>,
    val link: String,
    val modified: String,
    val title: String
)