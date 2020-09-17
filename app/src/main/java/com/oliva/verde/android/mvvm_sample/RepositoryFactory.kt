package com.oliva.verde.android.mvvm_sample

/**
 * repositoryをインスタンス化する方法を定義
 * インスタンス化対象クラスのクラスメソッドの呼び出しは行わない
 */
object RepositoryFactory {
    // Repositoryオブジェクトを返す
    fun createClientApiRepository() : ClientApiRepository {
        // Retrofitオブジェクトに、APIサービスインスタンスによって定義されたAPIエンドポイントを実装する
        val clientApi = RestUtil.retrofit.create(ClientApi::class.java)
        return ClientApiRepository(clientApi)
    }
}