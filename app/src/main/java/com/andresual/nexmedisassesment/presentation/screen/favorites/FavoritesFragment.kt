package com.andresual.nexmedisassesment.presentation.screen.favorites

import android.os.Bundle
import android.view.View
import com.andresual.nexmedisassesment.R
import com.andresual.nexmedisassesment.databinding.FragmentFavoritesBinding
import com.andresual.nexmedisassesment.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import com.andresual.nexmedisassesment.domain.model.FavoriteMeal
import com.andresual.nexmedisassesment.presentation.adapter.FavoriteMealAdapter

@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>(R.layout.fragment_favorites) {

    override val viewModel: FavoriteViewModel by viewModels()

    val adapterFavorites = FavoriteMealAdapter { removeMeal(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        manageRecyclerViewAdapterLifecycle(binding.rvFavoriteMeal)
        collectFlows(
            listOf(
                ::collectFavoriteMeal
            )
        )
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchFavoriteMeal()
    }

    private fun removeMeal(meal: FavoriteMeal) {
        viewModel.removeMealFromFavorites(meal)
        showSnackbar(
            message = getString(R.string.snackbar_removed_item),
            actionText = getString(R.string.snackbar_action_undo),
            anchor = true
        ) {
            viewModel.addMealToFavorites(meal)
        }
    }

    private suspend fun collectFavoriteMeal() {
        viewModel.favoriteMeal.collect { favoriteMeal ->
            adapterFavorites.submitList(favoriteMeal)
        }
    }
}