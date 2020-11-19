package com.oliva.verde.android.mvvm_sample

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ClientApi {
    @GET("users/{user}/repos")
    fun getGithub(@Path("user") user : String) : Single<Response<List<UserRepos>>>

    @GET("api/v1/posts")
    fun getPosts() : Single<Response<List<Post>>>

    @GET("api/v1/posts/{id}")
    fun getSinglePost(@Path("id") id : String) : Single<Response<Post>>

    @GET("v2/everything/") // アノテーション : ソースコード中に登場する要素（クラスやメソッドなど）に対して、コンパイラや実行環境に伝達したい付加的な情報（メタデータ）を注記する仕組み
    fun getNews(@Query("apiKey") apiKey: String,
                @Query("q") searchWord : String) : Single<Response<List<Article>>> // 返り値をSingle型とする(参照：https://qiita.com/takahirom/items/f3e576e91b219c7239e7)
}