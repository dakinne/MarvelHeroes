package com.noox.marvelheroes.character.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.noox.marvelheroes.R
import com.noox.marvelheroes.character.domain.model.Character
import com.noox.marvelheroes.character.ui.detail.CharacterDetailFragment
import com.noox.marvelheroes.databinding.FragmentHeroListBinding

class CharacterListFragment : Fragment() {

    private var _binding: FragmentHeroListBinding? = null
    private val binding get() = _binding!!

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
        // TODO: This code is only for manual test purpose. Delete it.
        binding.button.setOnClickListener {
            navigateToCharacter(Character(id = 1011005, image = "", name = ""))
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
