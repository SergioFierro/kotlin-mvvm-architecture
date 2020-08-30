package com.sergiofierro.meli.network.api

import com.sergiofierro.meli.model.ProductsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsService {

  @GET("sites/{site}/{path}")
  suspend fun fetchProducts(
    @Path("site") site: String = "MLA",
    @Path("path") path: String = "search",
    @Query("q") query: String
  ): ProductsResponse
}
