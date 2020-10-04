package com.oliva.verde.android.mvvm_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Viewの状態をViewModelが保持するためには、ViewModelProviderを使用する
        mainActivityViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(RepositoryFactory.createClientApiRepository())
        ).get(MainActivityViewModel::class.java) // getって何やるの？

        // getGitHubを呼び出し、LiveDataであるuserReposの流し込みを始める
        // mainActivityViewModel.getGitHub("google")
        mainActivityViewModel.getAllPosts()
        Log.d("LogOfJson", mainActivityViewModel.getAllPosts().toString())
        val main = findViewById<TextView>(R.id.main)
        // 流し込みが完了するたびに、画面部品に変更を反映する
        mainActivityViewModel.allPosts.observe(this, Observer {
            main.text = it.toString()
        })
    }
}