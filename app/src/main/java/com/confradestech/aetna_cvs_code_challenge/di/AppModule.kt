package com.confradestech.aetna_cvs_code_challenge.di

import android.app.Application
import com.confradestech.aetna_cvs_code_challenge.commom.utils.ConnectivityChecker
import com.confradestech.aetna_cvs_code_challenge.domain.endpoint.FlickrEndpoint
import com.confradestech.aetna_cvs_code_challenge.domain.useCase.GetFlickrPhotoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //region useCases
    @Provides
    @Singleton
    fun getFlickrPhotoUseCase(flickrEndpoint: FlickrEndpoint): GetFlickrPhotoUseCase =
        GetFlickrPhotoUseCase(flickrEndpoint)
    //endregion useCases

    //region utilities
    @Provides
    @Singleton
    fun connectivityChecker(application: Application): ConnectivityChecker =
        ConnectivityChecker(application)
    //endregion utilities

}