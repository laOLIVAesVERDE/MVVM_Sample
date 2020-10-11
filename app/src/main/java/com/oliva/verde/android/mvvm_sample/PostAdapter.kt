package com.oliva.verde.android.mvvm_sample

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView


class PostAdapter(var postList : LiveData<List<Post>>) : RecyclerView.Adapter<PostAdapter.BindingHolder>() {
    inner class BindingHolder(var binding: )
}