package com.thiennguyen.estatelisting.data.services

import com.thiennguyen.estatelisting.data.remote.response.EstatesResponse
import retrofit2.http.GET

interface ApiService {

    @GET("properties")
    suspend fun getEstates(): EstatesResponse
}