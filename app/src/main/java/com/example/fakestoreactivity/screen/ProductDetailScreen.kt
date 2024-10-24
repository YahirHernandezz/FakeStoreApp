package com.example.fakestoreactivity.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fakestoreactivity.models.Product
import com.example.fakestoreactivity.models.Rating
import com.example.fakestoreactivity.services.ProductService
import com.example.fakestoreactivity.ui.theme.FakeStoreActivityTheme
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun ProductDetailScreen(id:Int,innerPaddingValues: PaddingValues){
    val scope = rememberCoroutineScope()
    var isLoading by remember {
        mutableStateOf(false)
    }
    var product by remember {
        mutableStateOf(Product(
            id = 0,
            title = "",
            description = "",
            price = 0.0,
            image = "",
            rating = Rating(count = 0, rate = 0.0),
            category = ""
        ))
    }
    LaunchedEffect(key1 = true) {
        scope.launch {
            val BASE_URL = "https://fakestoreapi.com/"
            val productService = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ProductService::class.java)
            isLoading = true
            product = productService.getProductsById(id)
            isLoading = false
        }
    }
    if(isLoading){
        Box(
            modifier = Modifier
                .padding(innerPaddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    }
    else{
        Column (
            modifier = Modifier.padding(innerPaddingValues).fillMaxSize()
        ){
            Text(product.title)
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ProductDetailScreenPreview(){
    FakeStoreActivityTheme {
        ProductDetailScreen(id= 1,innerPaddingValues = PaddingValues(0.dp))
    }
}