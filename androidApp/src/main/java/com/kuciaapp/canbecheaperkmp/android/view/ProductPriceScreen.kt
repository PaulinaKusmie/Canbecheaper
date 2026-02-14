package com.kuciaapp.canbecheaperkmp.view

import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kuciaapp.canbecheaperkmp.model.domain.ProductPrice
import com.kuciaapp.canbecheaperkmp.ui.theme.MainColor
import com.kuciaapp.canbecheaperkmp.ui.theme.OmbreFirst
import com.kuciaapp.canbecheaperkmp.ui.theme.OmbreSecond
import com.kuciaapp.canbecheaperkmp.viewmodel.ProductPriceViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductPriceScreen(navController: NavController,
                       type: Int,
                      onBack: () -> Unit,
                      viewModel : ProductPriceViewModel = koinViewModel())
{

    LaunchedEffect(type) {
        viewModel.type = type
    }

    val products by viewModel.pproducts.collectAsState(initial = emptyList())
    val title by viewModel.title.collectAsState(initial = "")



    Scaffold(
        topBar = {
            TopAppBar( modifier = Modifier.background(MainColor),
                title = { Text(title) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MainColor
                ),
                        navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Powrót", tint = Color.Black)
                    }
                },
            )
        },
        containerColor = MainColor

    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)

        ) {
            items(count = products.size,
                key = { index -> products[index].id } )  { index  ->
                val item = products[index]
                 PProductItem (
                     item = item,
                     onPriceChange = { product, price ->
                         viewModel.updatePrice(product, price)
                     }
                )

            }
        }
    }
}



@Composable
fun PProductItem(
    item: ProductPrice,
    onPriceChange: (product: ProductPrice, price: String) -> Unit
) {
    val cardGradient = Brush.linearGradient(
        colors = listOf(OmbreFirst, OmbreSecond)
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(cardGradient),
        color = Color.Transparent,
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 18.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = item.name.toString(),
                modifier = Modifier.weight(2f),
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                color = Color.Black,
                letterSpacing = 0.2.sp,
            )

            OutlinedTextField(
                value = item.price.toString(),
                onValueChange = { newValue: String ->
                    if (newValue.isEmpty() || newValue.matches(Regex("^\\d*(\\.\\d{0,2})?$"))) {
                        val parsed = newValue.toDoubleOrNull()
                        if (parsed != null || newValue.isEmpty()) {
                            onPriceChange(item, newValue)
                        }
                    }
                },
                label = { Text("Cena") },
                modifier = Modifier.weight(1f),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                trailingIcon = {
                    Text(
                        text = "zł",
                        modifier = Modifier.padding(end = 8.dp),
                        color = Color.Gray
                    )
                }
            )

        }
    }
}
