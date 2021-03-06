package com.oliva.verde.android.mvvm_sample

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * ViewModel : View と Model（or Repository）の繋ぎの役割
 * Model（or Repository）のインスタンスを保持する。
 */
// ViewModelクラスを継承し、Repositoryオブジェクトを引数にとる
class MainActivityViewModel(val clientApiRepository : ClientApiRepository) : ViewModel() {
    /**
     * LiveDataの値を変更することはできないので MutableLiveDataで_userReposを作成し、
     * _userReposに対して値更新メソッド(getGitHub)を用意する
     * MainActivityでgetGitHubメソッドを呼び出し、リポジトリのリストを取得できる
     */
    private val _userRepos : MutableLiveData<List<UserRepos>> = MutableLiveData()
    val userRepos : LiveData<List<UserRepos>> = _userRepos

    private val _allPosts : MutableLiveData<List<Post>> = MutableLiveData()
    val allPosts : LiveData<List<Post>> = _allPosts

    private val _allArticles : MutableLiveData<List<Article>> = MutableLiveData()
    val allArticles : LiveData<List<Article>> = _allArticles

    // データの取得
    fun getGitHub(user : String) {
            // userReposの流し込みを開始する
            clientApiRepository.getGithubRepos(user)
                // 流し込まれたデータを_userReposにセットする
                .subscribe { userRepos : List<UserRepos> ->
                    _userRepos.postValue(userRepos)
        }
    }

    fun  getAllPosts() {
            clientApiRepository.getAllPosts()
                .subscribe { allPosts : List<Post> ->
                    _allPosts.postValue(allPosts)
                    Log.d("ConfirmAllPosts", _allPosts.postValue(allPosts).toString())
        }
    }

    fun getArticles(apiKey : String, searchWord : String) {
        clientApiRepository.getArticles(apiKey, searchWord)
            .subscribe { allArticles : List<Article> ->
                _allArticles.postValue(allArticles)
            }
    }
}