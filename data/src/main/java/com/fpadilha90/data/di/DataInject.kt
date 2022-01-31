package com.fpadilha90.data.di

import com.fpadilha90.data.BuildConfig
import com.fpadilha90.data.api.AssetsService
import com.fpadilha90.data.api.RestApi
import com.fpadilha90.data.db.AppDb
import com.fpadilha90.data.repository.AssetsRepositoryImpl
import com.fpadilha90.domain.repository.AssetsRepository
import org.koin.dsl.module
import retrofit2.Retrofit

object DataInject {

    val modules = module {
        single<AssetsRepository> {
            AssetsRepositoryImpl(get(), get())
        }

        single {
            get<Retrofit>().create(AssetsService::class.java)
        }

        single { AppDb.create(get(), false) }

        single { RestApi.createNetworkClient(BuildConfig.SERVER_URL, false) }
    }
}