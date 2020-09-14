package com.oliva.verde.android.mvvm_sample

/**
 * repositoryをインスタンス化する方法を定義
 */
object RepositoryFactory {
    fun createClientApiRepository() : ClientApiRepository {
        val clientApi = RestUtil.retrofit.create(ClientApi::class.java)
        return ClientApiRepository(clientApi)
    }
}