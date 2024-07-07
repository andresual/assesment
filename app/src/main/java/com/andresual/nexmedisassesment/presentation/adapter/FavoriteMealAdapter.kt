package com.andresual.nexmedisassesment.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andresual.nexmedisassesment.R
import com.andresual.nexmedisassesment.databinding.ItemFavoriteMealBinding
import com.andresual.nexmedisassesment.domain.model.FavoriteMeal

class FavoriteMealAdapter(
    private val onItemClicked: (item: FavoriteMeal) -> Unit
) : ListAdapter<FavoriteMeal, FavoriteMealAdapter.ViewHolder>(DiffCallback) {

    inner class ViewHolder(val view: ItemFavoriteMealBinding) : RecyclerView.ViewHolder(view.root) {
        init {
            view.ivRemove.setOnClickListener { onItemClicked(getItem(adapterPosition)) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_favorite_meal, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.meal = getItem(position)
    }

    object DiffCallback : DiffUtil.ItemCallback<FavoriteMeal>() {
        override fun areItemsTheSame(oldItem: FavoriteMeal, newItem: FavoriteMeal): Boolean = oldItem.idMeal == newItem.idMeal
        override fun areContentsTheSame(oldItem: FavoriteMeal, newItem: FavoriteMeal): Boolean = oldItem == newItem
    }
}