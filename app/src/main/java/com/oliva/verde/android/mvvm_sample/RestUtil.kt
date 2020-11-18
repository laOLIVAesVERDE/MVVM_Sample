package com.oliva.verde.android.mvvm_sample

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * APIへのアクセス方法を指定
 * アクセス方法の指定とは、createの実行前まで
 */
object RestUtil {
    // val ENDPOINT = "https://api.github.com/"
    // val ENDPOINT = "https://infinite-river-73262.herokuapp.com/"
    val ENDPOINT = "https://newsapi.org/"
    val retrofit : Retrofit

    init {
        // HttpLoggingInterceptor : ビルド時にrequestとresponseのログを出力するために用いる
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val builder = Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)

        retrofit = builder.build()
    }
}