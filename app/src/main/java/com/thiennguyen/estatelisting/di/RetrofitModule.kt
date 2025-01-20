package com.thiennguyen.estatelisting.di

import com.squareup.moshi.Moshi
import com.thiennguyen.estatelisting.BuildConfig
import com.thiennguyen.estatelisting.data.remote.providers.ApiServiceProvider
import com.thiennguyen.estatelisting.data.remote.providers.ConverterFactoryProvider
import com.thiennguyen.estatelisting.data.remote.providers.RetrofitProvider
import com.thiennguyen.estatelisting.data.services.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    fun provideBaseApiUrl() = BuildConfig.BASE_API_URL

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(moshi: Moshi): Converter.Factory =
        ConverterFactoryProvider.getMoshiConverterFactory(moshi)

    @Provides
    @Singleton
    fun provideRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit = RetrofitProvider
        .getRetrofitBuilder(baseUrl, okHttpClient, converterFactory)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        ApiServiceProvider.getApiService(retrofit)
}
