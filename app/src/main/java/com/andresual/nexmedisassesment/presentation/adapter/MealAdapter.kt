package com.andresual.nexmedisassesment.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andresual.nexmedisassesment.databinding.ItemMealBinding
import com.andresual.nexmedisassesment.domain.model.Meal

class MealAdapter(
    private val isGrid: Boolean = true,
) : ListAdapter<Meal, RecyclerView.ViewHolder>(DiffCallback) {

    inner class MealViewHolder private constructor(val view: ItemMealBinding) : RecyclerView.ViewHolder(view.root) {
        constructor(parent: ViewGroup) : this(ItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MealViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MealViewHolder -> {
                holder.view.apply {
                    isGrid = this@MealAdapter.isGrid
                    meal = getItem(position)
                }
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Meal>() {
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean = oldItem.idMeal == newItem.idMeal
        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean = oldItem == newItem
    }
}