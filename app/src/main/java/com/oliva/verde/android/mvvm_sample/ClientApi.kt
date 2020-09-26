package com.oliva.verde.android.mvvm_sample

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ClientApi {
    @GET("users/{user}/repos")
    fun getGithub(@Path("user") user : String) : Single<Response<List<UserRepos>>>

    @GET("api/v1/posts")
    fun getAllPosts() : Single<Response<List<Posts>>>
}