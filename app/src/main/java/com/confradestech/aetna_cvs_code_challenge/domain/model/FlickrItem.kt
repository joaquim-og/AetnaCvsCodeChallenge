package com.confradestech.aetna_cvs_code_challenge.domain.model

import com.google.gson.annotations.SerializedName

data class FlickrItem(
    val author: String,
    @SerializedName("author_id")
    val authorId: String,
    @SerializedName("date_taken")
    val dateTaken: String,
    val description: String,
    val link: String,
    @SerializedName("media")
    val flickrItemMedia: FlickrItemMedia,
    val published: String,
    val tags: String,
    val title: String
)