package com.oliva.verde.android.mvvm_sample

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


class PostAdapter : RecyclerView.Adapter<PostAdapter.ItemViewHolder> {
    private lateinit var holder : ItemViewHolder

    inner class ItemViewHolder : RecyclerView.ViewHolder {
        private lateinit var mBinding : ViewDataBinding

        fun ItemViewHolder(v: View?) {
            super(v)
            // Bind処理
            mBinding = DataBindingUtil.bind<ViewDataBinding>(v)
        }

        fun getBinding(): ViewDataBinding? {
            return mBinding
        }
    }
}