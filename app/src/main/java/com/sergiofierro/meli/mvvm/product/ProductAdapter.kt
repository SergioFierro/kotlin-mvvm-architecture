package com.sergiofierro.meli.mvvm.product

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sergiofierro.meli.R
import com.sergiofierro.meli.databinding.ItemProductBinding
import com.sergiofierro.meli.model.Product
import com.sergiofierro.meli.mvvm.detail.DetailActivity

class ProductAdapter(private val activity: AppCompatActivity) : ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiff()) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val binding = DataBindingUtil.inflate<ItemProductBinding>(inflater, R.layout.item_product, parent, false)
    return ProductViewHolder(binding)
  }

  override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
    val item = getItem(position)
    holder.binding.apply {
      product = item
      executePendingBindings()
      root.setOnClickListener {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.PRODUCT, product)
        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
          activity,
          Pair(
            root.findViewById(R.id.productImage),
            DetailActivity.TITLE
          ),
          Pair(
            root.findViewById(R.id.productName),
            DetailActivity.IMAGE
          ),
          Pair(
            root.findViewById(R.id.price),
            DetailActivity.PRICE
          )
        )
        ActivityCompat.startActivity(activity, intent, activityOptions.toBundle())
      }
    }
  }

  fun addProducts(products: List<Product>) {
    submitList(products)
  }

  inner class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

  private class ProductDiff : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem
  }
}
