package com.oliva.verde.android.mvvm_sample

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.oliva.verde.android.mvvm_sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // private var postList : MutableList<Post> = mutableListOf()
    private var articleList : MutableList<Article> = mutableListOf()
    private lateinit var mainActivityViewModel: MainActivityViewModel

    // bindingのインスタンスを取得
    // by lazy :    一度だけ値の初期化を行う
    //              値はキャッシュされ、二回目以降は最初の値を常に返す
    /*
    private val binding : ActivityMainBinding by lazy {
        // DataBindingUtil : Utility class to create ViewDataBinding from layouts.
        // ViewDataBinding : Base class for generated data binding classes.
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                // バインドするデータにLiveDataを使用する場合、作成したbindingに
                // 適切なlifecycleOwnerを渡す必要がある。
                // これを行わないとLiveDataの値変更がレイアウトにうまく通知されない。
                lifecycleOwner = this@MainActivity
            }
    }
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ConfirmLifecycle", "onCreate")

        // Viewの状態をViewModelが保持するためには、ViewModelProviderを使用する
        mainActivityViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(RepositoryFactory.createClientApiRepository())
        ).get(MainActivityViewModel::class.java) // getって何やるの？

        // getAllPostsを呼び出し、LiveDataであるallPostsへの流し込みを始める
        val apiKey = "413005df5f58476c868396878a752fb8"
        val searchWord = "ダイバーシティ"
        mainActivityViewModel.getArticles(apiKey, searchWord)

        val jsonToText = findViewById<TextView>(R.id.jsonToText)
        // 流し込みが完了するたびに、画面部品に変更を反映する
        mainActivityViewModel.allArticles.observe(this@MainActivity, Observer {
                jsonToText.text = it.toString()

            // binding.jsonToText.text = articleList.toString()
            /**
            binding.rvArticles.adapter = Adapter(articleList)
            binding.rvArticles.layoutManager = LinearLayoutManager(this@MainActivity)
            Adapter(articleList).notifyDataSetChanged()
            */
        })
    }

    override fun onResume() {
        super.onResume()
        Log.d("ConfirmLifecycle", "onResume")
    }
}