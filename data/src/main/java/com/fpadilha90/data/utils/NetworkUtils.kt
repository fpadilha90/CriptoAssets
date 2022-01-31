
package com.fpadilha90.data.utils

import com.fpadilha90.domain.exception.Failure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException


fun <DTO> safeApiCall(
    apiCall: suspend () -> DTO
): Flow<DTO> {
    return flow {
        try {
            emit(apiCall())
            //TODO: retry structure and network connection status awareness
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> throw Failure.NetworkConnection
                else -> {
                    throw throwable
                }
            }
        }
    }
}