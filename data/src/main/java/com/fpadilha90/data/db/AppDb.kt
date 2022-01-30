package com.fpadilha90.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fpadilha90.data.model.dbo.AssetInfoDBO

private const val APP_DB_VERSION = 1
private const val APP_DB_NAME = "app.db"

@Database(
    entities = [AssetInfoDBO::class],
    version = APP_DB_VERSION,
    exportSchema = false,
)

abstract class AppDb : RoomDatabase() {
    companion object {
        fun create(context: Context, useInMemory: Boolean): AppDb {
            val databaseBuilder = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(context, AppDb::class.java)
            } else {
                Room.databaseBuilder(context, AppDb::class.java, APP_DB_NAME)
            }

            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }


    abstract fun assets(): AssetInfoDao
}