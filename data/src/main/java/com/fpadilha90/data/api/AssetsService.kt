package com.fpadilha90.data.api

import com.fpadilha90.data.model.dto.AssetInfoResponse
import retrofit2.http.GET

interface AssetsService {

    @GET("assets/info")
    suspend fun assetsInfo(): AssetInfoResponse
}