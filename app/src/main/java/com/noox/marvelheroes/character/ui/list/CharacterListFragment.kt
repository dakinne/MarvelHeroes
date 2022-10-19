package com.noox.marvelheroes.character.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.noox.marvelheroes.R
import com.noox.marvelheroes.character.domain.model.Character
import com.noox.marvelheroes.character.ui.detail.CharacterDetailFragment
import com.noox.marvelheroes.character.ui.list.CharacterListViewModel.UiState
import com.noox.marvelheroes.core.extensions.dpToPx
import com.noox.marvelheroes.core.extensions.setOnSafeClickListener
import com.noox.marvelheroes.core.ui.DividerItemDecoration
import com.noox.marvelheroes.databinding.FragmentHeroListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private var _binding: FragmentHeroListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharacterListViewModel by viewModels()

    private val adapter by lazy {
        CharacterAdapter(
            onItemClick = { navigateToCharacter(it) },
            onLoadNextPage = { viewModel.loadNextPage() }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeroListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initViews() {
        with(binding) {
            characters.adapter = adapter
            ContextCompat.getDrawable(requireContext(), R.drawable.divider)?.let {
                characters.addItemDecoration(DividerItemDecoration(drawable = it, paddingLeft = 96.dpToPx()))
            }
        }
    }

    private fun initListeners() {
        binding.tryAgainButton.setOnSafeClickListener { viewModel.tryAgain() }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { perform(it) }
            }
        }
    }

    private fun perform(uiState: UiState) {
        with(binding) {
            loading.isVisible = uiState is UiState.Loading
            characters.isVisible = uiState is UiState.Success
            error.isVisible = uiState is UiState.Error
        }

        if (uiState is UiState.Success) {
            adapter.submitList(uiState.characters, uiState.hasMorePages)
        }
    }

    private fun navigateToCharacter(character: Character) {
        findNavController().navigate(
            R.id.action_heroListFragment_to_heroDetailFragment,
            CharacterDetailFragment.bundle(characterId = character.id)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
