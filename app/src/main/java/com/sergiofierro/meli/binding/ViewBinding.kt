package com.sergiofierro.meli.binding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar

@BindingAdapter("hide")
fun bindGone(view: View, hide: Boolean) {
  view.visibility = if (hide) {
    View.GONE
  } else {
    View.VISIBLE
  }
}

@BindingAdapter("snackbar")
fun bindSnackbar(view: View, text: LiveData<String>) {
  text.value?.let {
    Snackbar.make(view, it, Snackbar.LENGTH_SHORT).show()
  }
}
