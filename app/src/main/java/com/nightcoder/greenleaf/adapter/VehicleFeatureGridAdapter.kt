package com.nightcoder.greenleaf.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nightcoder.greenleaf.R
import com.nightcoder.greenleaf.databinding.ItemVehicleInfoBinding
import com.nightcoder.greenleaf.domain.model.VehicleFeatureItem

class VehicleFeatureGridAdapter : RecyclerView.Adapter<VehicleFeatureGridAdapter.ViewHolder>() {
    private val items = mutableListOf<VehicleFeatureItem>()

    class ViewHolder(private val binding: ItemVehicleInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: VehicleFeatureItem) {
            binding.title.text = item.title
            binding.value.text = item.value
            if (item.isHighlighted) {
                binding.value.setTextColor(ContextCompat.getColor(binding.root.context,R.color.green))
            } else {
                binding.value.setTextColor(ContextCompat.getColor(binding.root.context,R.color.white))
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(data: Iterable<VehicleFeatureItem>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    private var inflater: LayoutInflater? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        inflater = inflater ?: LayoutInflater.from(parent.context)
        return ViewHolder(ItemVehicleInfoBinding.inflate(inflater!!, parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}