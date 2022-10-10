package com.noox.marvelheroes.core.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import com.noox.marvelheroes.R
import com.noox.marvelheroes.core.domain.model.Image
import com.noox.marvelheroes.databinding.ComponentImagesCarouselBinding

class ImagesCarousel(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayoutCompat(context, attrs) {

    private val binding =
        ComponentImagesCarouselBinding.inflate(LayoutInflater.from(context), this)

    var images: List<Image> = emptyList()
        set(value) {
            field = value
            binding.images.adapter = ImageAdapter(value)
        }

    init {
        attrs?.let { setUpAttrs(it) }
        orientation = VERTICAL
    }

    private fun setUpAttrs(attrs: AttributeSet) {
        val typedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.ImagesCarousel, 0, 0)

        try {
            with(typedArray) {
                getText(R.styleable.ImagesCarousel_title)?.let { binding.title.text = it }
            }
        } finally {
            typedArray.recycle()
        }
    }

}
