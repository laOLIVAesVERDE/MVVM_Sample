package com.oliva.verde.android.mvvm_sample

import android.util.Log
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.IOException


/**
 * リポジトリはViewModelとModelの間に入っており、両者を疎結合にする。
 * データの取得や保存といったデータにアクセスするためのクラスをここで定義する。
 */
class ClientApiRepository(val clientApi: ClientApi) {
    fun getGithubRepos(user : String) : Single<List<UserRepos>> {
        return clientApi.getGithub(user)
            .subscribeOn(Schedulers.io())
            // observeOn : subscribeOn以降のオペレータの実行スレッドを切り替える
            .observeOn(AndroidSchedulers.mainThread())
            // ストリームに流れてくるアイテムを変換する
            // レスポンスにbodyが入っていた場合bodyを返す
            .map {
                val body = it.body()
                    ?: throw IOException("failed to fetch")
                return@map body // viewModelのuserReposにセットされる
            }
    }

    fun getAllPosts() : Single<List<Post>> {
        return clientApi.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                val body = it.body()
                    ?: throw IOException("failed to fetch")
                Log.d("ConfirmItOfGetAllPosts", it.toString())
                return@map body
            }
    }

    fun getArticles(apiKey : String, searchWord : String) : Single<List<Article>> {
        return clientApi.getNews(apiKey, searchWord)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                val body = it.body()
                    ?: throw  IOException("failed to fetch")
                return@map body
            }
    }
}