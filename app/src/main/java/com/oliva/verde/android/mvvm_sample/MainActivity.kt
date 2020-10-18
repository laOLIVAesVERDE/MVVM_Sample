package com.oliva.verde.android.mvvm_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oliva.verde.android.mvvm_sample.databinding.ActivityMainBinding
import com.oliva.verde.android.mvvm_sample.databinding.RecyclerItemBinding

class MainActivity : AppCompatActivity() {
    private var postList : MutableList<Post> = mutableListOf()
    private lateinit var mainActivityViewModel: MainActivityViewModel

    // bindingのインスタンスを取得
    private val binding : ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                lifecycleOwner = this@MainActivity
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Viewの状態をViewModelが保持するためには、ViewModelProviderを使用する
        mainActivityViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(RepositoryFactory.createClientApiRepository())
        ).get(MainActivityViewModel::class.java) // getって何やるの？

        // getAllPostsを呼び出し、LiveDataであるallPostsへの流し込みを始める
        mainActivityViewModel.getAllPosts()

        // 流し込みが完了するたびに、画面部品に変更を反映する
        for (i in 0..10) {
            postList.add(Post("title${i}", "content${i}"))
        }
        Log.d("ConfirmPostList", postList.toString())
        binding.rvPosts.adapter = PostAdapter(postList)
        binding.rvPosts.layoutManager = LinearLayoutManager(this@MainActivity)
        /**
        mainActivityViewModel.allPosts.observe(this, Observer {
            it.forEach { post ->
                postList.add(post)
            }
            // binding.rvPosts.adapter = PostAdapter(postList)
        })
        */
    }
}