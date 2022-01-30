package com.fpadilha90.data.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class AssetInfoDTO(val id: String, val name: String, val symbol: String, val image_url: String, val color: String, val website: String)