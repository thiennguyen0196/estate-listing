package com.thiennguyen.estatelisting.data.remote.providers

import com.thiennguyen.estatelisting.data.services.ApiService
import retrofit2.Retrofit

object ApiServiceProvider {

    fun getApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
