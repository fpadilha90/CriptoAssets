package com.fpadilha90.domain.interactor

import com.fpadilha90.domain.model.AssetInfo
import com.fpadilha90.domain.repository.AssetsRepository
import kotlinx.coroutines.flow.Flow

class FilterAssets(private val repository: AssetsRepository) : UseCase<List<AssetInfo>, String>() {
    override fun run(params: String): Flow<List<AssetInfo>> {
        return repository.fetchAssets(params)
    }

}