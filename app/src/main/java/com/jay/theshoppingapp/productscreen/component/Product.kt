package com.jay.theshoppingapp.productscreen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jay.theshoppingapp.R
import com.jay.theshoppingapp.core.compose.MobilePreview
import com.jay.theshoppingapp.model.Product

@Composable
fun Product(
    modifier: Modifier = Modifier,
    product: Product
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.thumbnail)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_launcher_background),
                contentDescription = product.title,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(width = 150.dp, height = 150.dp)
                    .clip(AbsoluteRoundedCornerShape(size = 8.dp))
            )
            Text(text = product.title ?: "")
            Text(
                text = "$${product.price}",
            )
        }
    }
}

@MobilePreview
@Composable
fun ProductPreview() {
    Product(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 4.dp),
        product = Product(
            id = 1,
            title = "iPhone 9",
            description = "An apple mobile which is nothing like apple",
            price = 549,
            brand = "Apple",
            category = "smartphones",
            thumbnail = "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
            images = listOf(
                "https://cdn.dummyjson.com/product-images/1/1.jpg",
                "https://cdn.dummyjson.com/product-images/1/2.jpg",
                "https://cdn.dummyjson.com/product-images/1/3.jpg",
                "https://cdn.dummyjson.com/product-images/1/4.jpg",
                "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg"
            )
        )
    )
}