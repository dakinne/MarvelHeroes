package com.noox.marvelheroes.character.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import coil.load
import com.noox.marvelheroes.R
import com.noox.marvelheroes.character.domain.model.Character
import com.noox.marvelheroes.character.ui.detail.CharacterDetailViewModel.UiState
import com.noox.marvelheroes.core.extensions.setOnSafeClickListener
import com.noox.marvelheroes.databinding.FragmentHeroDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

const val ARG_CHARACTER_ID = "characterId"

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    companion object {
        fun bundle(characterId: Int) = bundleOf(ARG_CHARACTER_ID to characterId)
    }

    private var _binding: FragmentHeroDetailBinding? = null
    private val binding get() = _binding!!

   private val viewModel: CharacterDetailViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeroDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        binding.toolbar.setOnSafeClickListener { findNavController().navigateUp() }
        binding.tryAgainButton.setOnSafeClickListener { viewModel.loadCharacter() }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { perform(it) }
            }
        }
    }

    private fun perform(uiState: UiState) {
        with(binding) {
            loading.isVisible = uiState is UiState.Loading
            content.isVisible = uiState is UiState.Success
            error.isVisible = uiState is UiState.Error
        }

        if (uiState is UiState.Success) {
            showCharacterData(uiState.character)
        }
    }

    private fun showCharacterData(character: Character) {
        with(binding) {
            image.load(character.image.url) {
                crossfade(true)
                placeholder(R.drawable.image_placeholder)
                error(R.drawable.image_placeholder)
            }
            toolbar.title = character.name

            comics.isVisible = character.comics.isNotEmpty()
            comics.images = character.comics.map { it.image }

            series.isVisible = character.series.isNotEmpty()
            series.images = character.series.map { it.image }

            events.isVisible = character.events.isNotEmpty()
            events.images = character.events.map { it.image }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
