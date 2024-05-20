package com.example.nixfit.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nixfit.data.remote.FoodsResponse
import com.example.nixfit.data.remote.Nutriments
import com.example.nixfit.data.remote.Product
import com.example.nixfit.ui.theme.NixFitTheme
import com.example.nixfit.ui.theme.WhiteGray


@Composable
fun FoodsCard(
    modifier: Modifier = Modifier,
    food: FoodsResponse,
    onClick: (() -> Unit)? = null
) {

    val context = LocalContext.current
    Row(
        modifier = modifier.clickable { onClick?.invoke() },

        ) {
        Text(
            text = food.product.nutriments.energy,
            style = MaterialTheme.typography.labelSmall,
            color = WhiteGray
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = 3.dp)
                .height(96.dp)
        ) {
            Text(
                text = food.product.productName,
                style = MaterialTheme.typography.bodyMedium.copy(),
                color = WhiteGray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = food.product.servingSize,
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = WhiteGray
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = food.product.nutriments.energy,
                    style = MaterialTheme.typography.labelSmall,
                    color = WhiteGray
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = food.product.nutriments.energy,
                    style = MaterialTheme.typography.labelSmall,
                    color = WhiteGray
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun FoodsCardPreview() {
    NixFitTheme {
        FoodsCard(food = FoodsResponse(
            product = Product(
                productName = "Red bull",
                servingSize = "16",
                servingQuantity = "oz",
                nutriments = Nutriments(
                    carbohydrates = "10",
                    energy = "100",
                    fat = "10",
                    protein = "10",
                    sugar = "10",
                    sodium = "10"
                )
            )
        ))
    }
}