package com.confradestech.aetna_cvs_code_challenge.domain.useCase

import com.confradestech.aetna_cvs_code_challenge.domain.endpoint.FlickrEndpoint
import com.confradestech.aetna_cvs_code_challenge.domain.model.FlickrResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest

class GetFlickrPhotoUseCase(
    private val flickrEndpoint: FlickrEndpoint,
) {
    fun getFlickrPhotos(tags: String): Flow<FlickrResponse?> {
        return channelFlow {
            flickrEndpoint.getFlickrPhotos(tags = tags).collectLatest {
                send(it.body())
            }
        }
    }
}