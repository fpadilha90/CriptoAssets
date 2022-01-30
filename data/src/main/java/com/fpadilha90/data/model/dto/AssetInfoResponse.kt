package com.fpadilha90.data.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class AssetInfoResponse(val data: List<AssetInfoDTO>)