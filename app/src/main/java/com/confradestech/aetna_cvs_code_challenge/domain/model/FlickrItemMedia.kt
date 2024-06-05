package com.confradestech.aetna_cvs_code_challenge.domain.model

import com.google.gson.annotations.SerializedName

data class FlickrItemMedia(
    @SerializedName("m")
    val mediaLink: String
)