package com.example.mstats

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class FilterAdapter(
    private val context: Context,
    private val filters: MutableList<FilterModel>,
    private val onFilterSelected: (FilterModel) -> Unit
) : RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {

    private var selectedPosition = 0

    inner class FilterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val button: Button = itemView.findViewById(R.id.filterButton)

        fun bind(filter: FilterModel, position: Int) {
            button.text = filter.name

            button.alpha = if (position == selectedPosition) 1.0f else 0.5f

            button.setOnClickListener {

                if (selectedPosition != position) {
                    val previouslySelectedPosition = selectedPosition
                    selectedPosition = position
                    notifyItemChanged(previouslySelectedPosition)
                    notifyItemChanged(selectedPosition)
                    onFilterSelected(filter)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_filter, parent, false)
        return FilterViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.bind(filters[position], position)
    }

    override fun getItemCount(): Int = filters.size
}