package com.andresual.nexmedisassesment.presentation.screen.home

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import com.andresual.nexmedisassesment.R
import com.andresual.nexmedisassesment.databinding.FragmentHomeBinding
import com.andresual.nexmedisassesment.presentation.base.BaseFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.andresual.nexmedisassesment.presentation.adapter.MealAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override val viewModel: HomeViewModel by viewModels()

    val mealAdapter = MealAdapter(isGrid = true)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        manageRecyclerViewAdapterLifecycle(
            binding.rvMeal
        )

        binding.rvMeal.layoutManager = GridLayoutManager(
            requireContext(), 2
        )

        setupSearchView()

        collectFlows(
            listOf(
                ::collectMeals,
                ::collectUiState
            )
        )
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.rvMeal.scrollToPosition(0)

                if (!query.isNullOrEmpty()) viewModel.fetchInitialSearch(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })
    }

    fun clearSearch() {
        viewModel.clearSearchResults()
        mealAdapter.submitList(null)
    }

    private suspend fun collectMeals() {
        viewModel.meals.collect { meals ->
            mealAdapter.submitList(meals)
        }
    }

    private suspend fun collectUiState() {
        viewModel.uiState.collect { state ->
            if (state.isError) showSnackbar(
                message = state.errorText!!,
                actionText = getString(R.string.button_retry),
                anchor = true
            ) {
                viewModel.retryConnection {
                    viewModel.initRequest()
                }
            }
        }
    }
}