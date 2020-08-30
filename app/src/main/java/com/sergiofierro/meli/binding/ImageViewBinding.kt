package com.sergiofierro.meli.binding

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image")
fun bindLoadImagePalette(view: AppCompatImageView, url: String) {
  Glide.with(view.context).load(url).into(view)
}
