package com.fpadilha90.data.model.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AssetInfoDBO(
    @PrimaryKey val id: String,
    val name: String,
    val symbol: String,
    val image_url: String,
    val color: String,
    val website: String

)