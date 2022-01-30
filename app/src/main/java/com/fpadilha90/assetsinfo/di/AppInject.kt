package com.fpadilha90.assetsinfo.di

import com.fpadilha90.assetsinfo.ui.ui.assets.AssetsViewModel
import com.fpadilha90.data.db.AppDb
import com.fpadilha90.data.repository.AssetsRepositoryImpl
import com.fpadilha90.domain.interactor.FilterAssets
import com.fpadilha90.domain.interactor.GetAssets
import com.fpadilha90.domain.repository.AssetsRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object AppInject {

    fun modules(): List<Module> = listOf(viewModelModule, domainInject, dataInject)


    private val viewModelModule: Module = module {
        viewModel { AssetsViewModel(get(), get()) }
    }

    private val domainInject = module {
        factory { GetAssets(get()) }
        factory { FilterAssets(get()) }
    }

    private val dataInject = module {
        single<AssetsRepository> {
            AssetsRepositoryImpl(get())
        }

        single { AppDb.create(get(), false) }
    }
}