package com.oliva.verde.android.mvvm_sample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oliva.verde.android.mvvm_sample.databinding.RecyclerItemBinding


class PostAdapter(var postList : MutableList<Post>) :
    //
    RecyclerView.Adapter<PostAdapter.BindingHolder>() {

    inner class BindingHolder(var binding: RecyclerItemBinding) :
        //
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val layoutInflater = LayoutInflater.from(parent!!.context)
        val binding = RecyclerItemBinding.inflate(layoutInflater, parent, false)
        return BindingHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val post = postList[position]
        holder.binding.post = post
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}