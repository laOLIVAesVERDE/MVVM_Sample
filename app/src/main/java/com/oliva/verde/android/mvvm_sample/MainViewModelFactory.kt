package com.oliva.verde.android.mvvm_sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * ViewModelをインスタンス化する方法を定義
 */
class MainViewModelFactory(private val clientApiRepository: ClientApiRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(clientApiRepository) as T
    }
}