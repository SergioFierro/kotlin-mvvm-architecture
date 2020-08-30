package com.sergiofierro.meli.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.requestFocus(showKeyboard: Boolean) {
  this.requestFocus()
  if (showKeyboard) {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
  }
}

fun View.clearFocus(hideKeyboard: Boolean) {
  this.clearFocus()
  if (hideKeyboard) {
    val imm =
      this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm?.hideSoftInputFromWindow(this.windowToken, 0)
  }
}
