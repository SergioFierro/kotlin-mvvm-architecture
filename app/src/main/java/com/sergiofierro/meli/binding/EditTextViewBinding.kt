package com.sergiofierro.meli.binding

import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("onEditorActionListener")
fun bindOnEditorActionListener(editText: EditText, editorActionListener: TextView.OnEditorActionListener) {
  editText.setOnEditorActionListener(editorActionListener)
}

@BindingAdapter("addTextChangedListener")
fun bindAddTextChangedListener(editText: EditText, textWatcher: TextWatcher) {
  editText.addTextChangedListener(textWatcher)
}
