package com.andresual.nexmedisassesment.presentation.screen.mealdetail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.andresual.nexmedisassesment.R
import com.andresual.nexmedisassesment.databinding.FragmentMealDetailBinding
import com.andresual.nexmedisassesment.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealDetailFragment : BaseFragment<FragmentMealDetailBinding>(R.layout.fragment_meal_detail) {

    override val viewModel: MealDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.initRequests(detailId)

        collectFlows(
            listOf(
                ::collectDetails,
                ::collectUiState
            )
        )


    }

    private suspend fun collectDetails() {
        viewModel.details.collect { details ->
            Log.i("collectDetails: ", details.toString())
        }
    }

    private suspend fun collectUiState() {
        viewModel.uiState.collect { state ->
            if (state.isError) showSnackbar(message = state.errorText!!, actionText = getString(R.string.button_retry)) {
                viewModel.retryConnection {
                    viewModel.initRequests(id)
                }
            }
        }
    }
}