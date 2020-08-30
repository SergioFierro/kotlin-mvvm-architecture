package com.sergiofierro.meli.mvvm.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sergiofierro.meli.R
import com.sergiofierro.meli.databinding.ItemAttributeBinding
import com.sergiofierro.meli.model.ProductAttributes

class AttributesAdapter : ListAdapter<ProductAttributes, AttributesAdapter.AttributesViewHolder>(AttributesDiff()) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttributesViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val binding = DataBindingUtil.inflate<ItemAttributeBinding>(inflater, R.layout.item_attribute, parent, false)
    return AttributesViewHolder(binding)
  }

  override fun onBindViewHolder(holder: AttributesViewHolder, position: Int) {
    val item = getItem(position)
    holder.binding.apply {
      attribute = item
      executePendingBindings()
    }
  }

  inner class AttributesViewHolder(val binding: ItemAttributeBinding) : RecyclerView.ViewHolder(binding.root)

  private class AttributesDiff : DiffUtil.ItemCallback<ProductAttributes>() {
    override fun areItemsTheSame(oldItem: ProductAttributes, newItem: ProductAttributes) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ProductAttributes, newItem: ProductAttributes) = oldItem == newItem
  }
}
