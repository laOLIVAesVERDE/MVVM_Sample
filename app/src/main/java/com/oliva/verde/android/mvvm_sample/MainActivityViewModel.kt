package com.oliva.verde.android.mvvm_sample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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

    // データの取得
    fun getGitHub(user : String) {
        // userReposの流し込みを開始する
        clientApiRepository.getGithubRepos(user)
            // 流し込まれたデータを_userReposにセットする
            .subscribe { userRepos : List<UserRepos> ->
                _userRepos.postValue(userRepos)
            }
    }
}