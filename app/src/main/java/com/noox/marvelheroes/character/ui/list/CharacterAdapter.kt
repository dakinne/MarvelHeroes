package com.noox.marvelheroes.character.ui.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.noox.marvelheroes.character.domain.model.Character

private const val THRESHOLD = 30

class CharacterAdapter(
    private val onItemClick: (Character) -> Unit,
    private val onLoadNextPage: () -> Unit
) : ListAdapter<Character, RecyclerView.ViewHolder>(COMPARATOR) {

    private var itemCount = 0
    private var hasMorePages: Boolean = true
    private var lastItemPosition = 0

    fun submitList(list: List<Character>, hasMorePages: Boolean = true) {
        this.itemCount = list.size + if (hasMorePages) 1 else 0
        this.hasMorePages = hasMorePages
        this.lastItemPosition = itemCount - 1
        super.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LOADING -> LoadingViewHolder.create(parent)
            else -> CharacterViewHolder.create(parent) { onItemClick(it) }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CharacterViewHolder) {
            getItem(position)?.let { holder.bind(it) }
        }
        if (hasMorePages && position + THRESHOLD > itemCount) {
            onLoadNextPage()
        }
    }

    override fun getItemCount() = itemCount

    override fun getItemViewType(position: Int): Int {
        return if (hasMorePages && isLastItem(position)) LOADING else ITEM
    }

    private fun isLastItem(position: Int) = position == lastItemPosition

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem == newItem
        }

        private const val ITEM = 0
        private const val LOADING = 1
    }
}
