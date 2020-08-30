package com.sergiofierro.meli.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.sergiofierro.meli.model.Product
import java.text.NumberFormat
import java.util.Currency

@BindingAdapter("currency")
fun bindCurrency(textView: TextView, product: Product) {
  val numberFormat = NumberFormat.getCurrencyInstance()
  numberFormat.maximumFractionDigits = 0
  numberFormat.currency = Currency.getInstance(product.currency)

  textView.text = numberFormat.format(product.price)
}
