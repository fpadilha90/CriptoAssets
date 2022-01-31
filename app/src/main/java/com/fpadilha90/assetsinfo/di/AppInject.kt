package com.fpadilha90.assetsinfo.di

import com.fpadilha90.assetsinfo.ui.ui.assets.AssetsViewModel
import com.fpadilha90.data.di.DataInject
import com.fpadilha90.domain.interactor.FilterAssets
import com.fpadilha90.domain.interactor.GetAssets
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object AppInject {

    fun modules(): List<Module> = listOf(viewModelModule, domainInject, DataInject.modules)


    private val viewModelModule: Module = module {
        viewModel { AssetsViewModel(get(), get()) }
    }

    private val domainInject = module {
        factory { GetAssets(get()) }
        factory { FilterAssets(get()) }
    }

}