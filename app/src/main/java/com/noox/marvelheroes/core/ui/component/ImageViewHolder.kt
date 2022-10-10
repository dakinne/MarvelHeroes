package com.noox.marvelheroes.core.ui.component

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.noox.marvelheroes.R
import com.noox.marvelheroes.core.domain.model.Image
import com.noox.marvelheroes.core.extensions.dpToPx
import com.noox.marvelheroes.databinding.ItemImageBinding

class ImageViewHolder(
    private val binding: ItemImageBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) = ImageViewHolder(
            binding = ItemImageBinding.inflate( LayoutInflater.from(parent.context), parent, false)
        )
    }

    fun bind(image: Image) {
        binding.image.load(image.url) {
            crossfade(true)
            placeholder(R.drawable.image_placeholder)
            error(R.drawable.image_placeholder)
            transformations(RoundedCornersTransformation(4f.dpToPx()))
        }
    }

}
