package com.example.fakestoreactivity.services

import com.example.fakestoreactivity.models.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {

    @GET("products")
    suspend fun getProducts(): List<Product>

    @GET("products/{id}")
    suspend fun getProductsById(@Path("id") id: Int): Product
}