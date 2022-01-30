package com.fpadilha90.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fpadilha90.data.model.dbo.AssetInfoDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface AssetInfoDao {
    @Query("SELECT * FROM assetinfodbo")
    fun getAll(): Flow<List<AssetInfoDBO>>

    @Query("SELECT * FROM assetinfodbo WHERE name LIKE :filter OR symbol LIKE :filter")
    fun get(filter: String): Flow<List<AssetInfoDBO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(assets: List<AssetInfoDBO>)
}