package com.fpadilha90.data.repository

import com.fpadilha90.data.db.AppDb
import com.fpadilha90.data.model.dbo.AssetInfoDBO
import com.fpadilha90.data.model.dto.AssetInfoResponse
import com.fpadilha90.domain.model.AssetInfo
import com.fpadilha90.domain.repository.AssetsRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.lang.Exception
import java.net.URL


class AssetsRepositoryImpl(private val db: AppDb) : AssetsRepository {

    override fun fetchAssets(): Flow<List<AssetInfo>> {
        return getAssetsFrom(db.assets().getAll())
            .also { updateAssetsCache() }
    }


    override fun fetchAssets(filter: String): Flow<List<AssetInfo>> {
        return getAssetsFrom(db.assets().get("%$filter%"))
//            .also { updateAssetsCache() }
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
            val body = getBodyFrom("https://api.coinbase.com/v2/assets/info")

            if (body.isNotEmpty()) {
                Json { ignoreUnknownKeys = true }
                    .decodeFromString<AssetInfoResponse>(body).data
                    .map { dto ->
                        dto.run { AssetInfoDBO(id, name, symbol, image_url, color, website) }
                    }.let {
                        db.runInTransaction {
                            db.assets().insert(it)
                        }
                    }
            }

        }
    }

    private suspend fun getBodyFrom(url: String): String = withContext(Dispatchers.IO) {
        try {
            URL(url).readText()
        } catch (exception: Exception) {
            ""
        }
    }
}