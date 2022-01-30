package com.fpadilha90.domain.interactor

import com.fpadilha90.domain.model.AssetInfo
import com.fpadilha90.domain.repository.AssetsRepository
import kotlinx.coroutines.flow.Flow

class GetAssets(private val repository: AssetsRepository) : UseCase<List<AssetInfo>, UseCase.None>() {
    override fun run(params: None): Flow<List<AssetInfo>> {
        return repository.fetchAssets()
    }

}