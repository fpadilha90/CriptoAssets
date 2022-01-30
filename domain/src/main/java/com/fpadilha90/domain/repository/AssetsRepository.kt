package com.fpadilha90.domain.repository

import com.fpadilha90.domain.model.AssetInfo
import kotlinx.coroutines.flow.Flow

interface AssetsRepository {
    fun fetchAssets(): Flow<List<AssetInfo>>
    fun fetchAssets(filter: String): Flow<List<AssetInfo>>

}
