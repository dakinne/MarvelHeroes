package com.noox.marvelheroes.character.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.noox.marvelheroes.R
import com.noox.marvelheroes.character.domain.model.Character
import com.noox.marvelheroes.core.extensions.setOnSafeClickListener
import com.noox.marvelheroes.databinding.ItemCharacterBinding

class CharacterViewHolder(
    private val binding: ItemCharacterBinding,
    private val onItemClick: (Character) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup, onItemClick: (Character) -> Unit) = CharacterViewHolder(
            binding = ItemCharacterBinding.inflate( LayoutInflater.from(parent.context), parent, false),
            onItemClick = onItemClick
        )
    }

    fun bind(character: Character) {
        with(binding) {
            image.load(character.thumbnail.url) {
                crossfade(true)
                placeholder(R.drawable.image_placeholder)
                error(R.drawable.image_placeholder)
            }
            name.text = character.name
            comics.text = character.totalComics.toString()
            events.text = character.totalEvents.toString()
            series.text = character.totalSeries.toString()
        }

        itemView.setOnSafeClickListener { onItemClick(character) }
    }

}
