package com.oliva.verde.android.mvvm_sample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oliva.verde.android.mvvm_sample.databinding.RecyclerItemBinding


class PostAdapter(var postList : MutableList<Post>) : RecyclerView.Adapter<PostAdapter.BindingHolder>() {
    // ビューホルダ : DataBindingの場合は、レイアウトファイルのrootを保持する
    // root : 一番外側の要素(この場合はlayoutタグ)
    inner class BindingHolder(var binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    // 各アイテムの画面部品が記述されたレイアウトファイルを元にビューホルダオブジェクトを生成する
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val layoutInflater = LayoutInflater.from(parent!!.context)
        //
        val binding = RecyclerItemBinding.inflate(layoutInflater, parent, false)
        return BindingHolder(binding)
    }

    // ビューホルダ内のBinding対象データに、データを割り当てる
    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val post = postList[position]
        holder.binding.post = post
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}