package com.sergiofierro.meli.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
data class ProductsResponse(
  @field:Json(name = "results") val results: List<Product>
)

@Parcelize
@JsonClass(generateAdapter = true)
data class Product(
  @field:Json(name = "id") val id: String,
  @field:Json(name = "title") val title: String,
  @field:Json(name = "price") val price: Double,
  @field:Json(name = "currency_id") val currency: String,
  @field:Json(name = "thumbnail") val thumbnail: String,
  @field:Json(name = "permalink") val permalink: String,
  @field:Json(name = "attributes") val attributes: List<ProductAttributes>
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class ProductAttributes(
  @field:Json(name = "id") val id: String,
  @field:Json(name = "name") val name: String,
  @field:Json(name = "value_id") val valueId: String?,
  @field:Json(name = "value_name") val valueName: String?
) : Parcelable
