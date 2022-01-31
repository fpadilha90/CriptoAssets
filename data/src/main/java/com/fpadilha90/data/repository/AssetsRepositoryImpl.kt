package com.fpadilha90.data.repository

import com.fpadilha90.data.api.AssetsService
import com.fpadilha90.data.db.AppDb
import com.fpadilha90.data.model.dbo.AssetInfoDBO
import com.fpadilha90.data.utils.safeApiCall
import com.fpadilha90.domain.model.AssetInfo
import com.fpadilha90.domain.repository.AssetsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class AssetsRepositoryImpl(private val db: AppDb, private val service: AssetsService) :
    AssetsRepository {

    override fun fetchAssets(): Flow<List<AssetInfo>> {
        return getAssetsFrom(db.assets().getAll())
            .also { updateAssetsCache() }
    }


    override fun fetchAssets(filter: String): Flow<List<AssetInfo>> {
        return getAssetsFrom(db.assets().get("%$filter%"))
    }

    private fun getAssetsFrom(query: Flow<List<AssetInfoDBO>>): Flow<List<AssetInfo>> {
        return query.distinctUntilChanged().filterNotNull().map {
            it.map { dbo ->
                dbo.run { AssetInfo(name, symbol, image_url, color, website) }
            }
        }
    }

    private fun updateAssetsCache() {
        CoroutineScope(Dispatchers.IO).launch {
            safeApiCall { service.assetsInfo() }.map { it.data }
                .map {
                    it.map { dto ->
                        dto.run { AssetInfoDBO(id, name, symbol, image_url, color, website) }
                    }
                }.catch {
                    //TODO: generic error structure for NetworkConnection
                }.collect {
                    db.runInTransaction {
                        db.assets().insert(it)
                    }
                }
        }
    }
}