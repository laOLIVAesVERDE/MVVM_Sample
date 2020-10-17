package com.oliva.verde.android.mvvm_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.oliva.verde.android.mvvm_sample.databinding.ActivityMainBinding
import com.oliva.verde.android.mvvm_sample.databinding.RecyclerItemBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // bindingのインスタンスを取得
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        // Viewの状態をViewModelが保持するためには、ViewModelProviderを使用する
        mainActivityViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(RepositoryFactory.createClientApiRepository())
        ).get(MainActivityViewModel::class.java) // getって何やるの？

        // getGitHubを呼び出し、LiveDataであるuserReposの流し込みを始める
        // mainActivityViewModel.getGitHub("google")
        mainActivityViewModel.getAllPosts()
        Log.d("LogOfJson", mainActivityViewModel.getAllPosts().toString())

        // 流し込みが完了するたびに、画面部品に変更を反映する
        val postList : MutableList<Post> = mutableListOf()
        mainActivityViewModel.allPosts.observe(this, Observer {
            Log.d("ConfirmIt", it.javaClass.toString())
            it.forEach { post ->
                Log.d("ConfirmPost", post.toString())
            }
        })
        Log.d("ConfirmPostList", postList.toString())
        val postAdapter = PostAdapter(postList)
        binding.rvPosts.adapter = postAdapter
    }
}