package com.noox.marvelheroes.core.ui.component

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.noox.marvelheroes.core.domain.model.Image

class ImageAdapter(
    private val images: List<Image>
) : RecyclerView.Adapter<ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount() = images.size

}
