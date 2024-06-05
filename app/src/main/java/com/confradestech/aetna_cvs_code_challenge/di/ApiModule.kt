package com.confradestech.aetna_cvs_code_challenge.di

import android.app.Application
import com.confradestech.aetna_cvs_code_challenge.commom.utils.NullOnEmptyConverterFactory
import com.confradestech.aetna_cvs_code_challenge.domain.endpoint.FlickrEndpoint
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tech.thdev.network.flowcalladapterfactory.FlowCallAdapterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideRetrofit(context: Application): Retrofit {
        val clientBuilder = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .followRedirects(false)
            .followSslRedirects(false)

        setClientBuilder(clientBuilder)

        val gson = GsonBuilder()
            .setPrettyPrinting()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()

        return Retrofit.Builder()
            .baseUrl("https://api.flickr.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(clientBuilder.build())
            .addCallAdapterFactory(FlowCallAdapterFactory())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .build()
    }

    private fun setClientBuilder(clientBuilder: OkHttpClient.Builder) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        clientBuilder.addInterceptor(httpLoggingInterceptor)

        val interceptor = Interceptor { chain ->
            val request = chain.request()
            val response = chain.proceed(request)
            response
        }
        clientBuilder.addInterceptor(interceptor)
    }

    @Provides
    @Singleton
    fun provideFlickrEndpointApi(retrofit: Retrofit): FlickrEndpoint =
        retrofit.create(FlickrEndpoint::class.java)
}
