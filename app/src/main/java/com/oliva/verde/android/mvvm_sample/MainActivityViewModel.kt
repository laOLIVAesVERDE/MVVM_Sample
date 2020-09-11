package com.oliva.verde.android.mvvm_sample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel : View と Model の繋ぎの役割
 * Model（or Repository）のインスタンスを保持します。
 */
// ViewModelクラスを継承する
class MainActivityViewModel(val clientApiRepository : ClientApiRepository) : ViewModel() {
    /**
     * LiveDataの値を変更することはできないので MutableLiveDataで_userReposを作成し、
     * _userReposに対して値更新メソッド(getGitHub)を用意する
     */
    private val _userRepos : MutableLiveData<List<UserRepos>> = MutableLiveData()
    val userRepos : LiveData<List<UserRepos>> = _userRepos

    fun getGitHub(user : String) {
        clientApiRepository.getGithubRepos(user)
            .subscribe { userRepos : List<UserRepos> ->
                _userRepos.postValue(userRepos)
            }
    }
}