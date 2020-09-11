package com.oliva.verde.android.mvvm_sample

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.IOException


/**
 * リポジトリは ViewModel と Model の間に入っており、両者を疎結合にする。
 * データの取得や保存といったデータにアクセスするためのクラスをここで定義する。
 */
class ClientApiRepository(val clientApi: ClientApi) {
    fun getGithubRepos(user : String) : Single<List<UserRepos>> {
        return clientApi.getGithub(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            // ストリームに流れてくるアイテムを変換する
            // レスポンスにbodyが入っていた場合bodyを返す
            .map {
                val body = it.body()
                    ?: throw IOException("failed to fetch")
                return@map body
            }
    }
}