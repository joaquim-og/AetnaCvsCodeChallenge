package com.confradestech.aetna_cvs_code_challenge.domain.model.uiStates

import com.confradestech.aetna_cvs_code_challenge.domain.model.FlickrItem

data class HomeUiState(
    val isLoading: Boolean = false,
    val flickrItems: List<FlickrItem>? = null,
    val error: Throwable? = null
)
