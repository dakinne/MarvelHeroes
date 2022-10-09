package com.noox.marvelheroes.character.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.noox.marvelheroes.databinding.ItemLoadingBinding

class LoadingViewHolder(
    private val binding: ItemLoadingBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) = LoadingViewHolder(
            binding = ItemLoadingBinding.inflate( LayoutInflater.from(parent.context), parent, false)
        )
    }

}
