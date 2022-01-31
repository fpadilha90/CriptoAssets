package com.fpadilha90.assetsinfo.ui.ui.assets

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fpadilha90.domain.interactor.FilterAssets
import com.fpadilha90.domain.interactor.GetAssets
import com.fpadilha90.domain.interactor.UseCase
import com.fpadilha90.domain.model.AssetInfo
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AssetsViewModel(getAssets: GetAssets, val filterAssets: FilterAssets) : ViewModel() {
    val assets = MutableLiveData<List<AssetInfo>>()

    init {
        getAssets(UseCase.None)
            .onEach { assets.setValue(it) }
            .launchIn(viewModelScope)
    }

    fun search(filter: String) = filterAssets(filter)
        .onEach { assets.setValue(it) }
        .launchIn(viewModelScope)

}