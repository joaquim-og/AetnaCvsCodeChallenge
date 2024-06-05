package com.confradestech.aetna_cvs_code_challenge.domain.endpoint

import com.confradestech.aetna_cvs_code_challenge.domain.model.FlickrResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Query

interface FlickrEndpoint {

    @GET("services/feeds/photos_public.gne")
    fun getFlickrPhotos(
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 1,
        @Query("tags") tags: String
    ): Flow<Response<FlickrResponse?>>

}