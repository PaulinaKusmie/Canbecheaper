package com.kuciaapp.canbecheaperkmp.viewmodel


import kotlinx.coroutines.launch
import com.kuciaapp.canbecheaperkmp.model.domain.ProductPrice
import com.kuciaapp.canbecheaperkmp.model.repository.PriceRepository
import com.kuciaapp.canbecheaperkmp.model.repository.ProductPriceRepository
import com.kuciaapp.canbecheaperkmp.utility.UserSession

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.core.component.KoinComponent

class ProductPriceViewModel(private val repoProductPrice: ProductPriceRepository,
                                                 private val repoPrice: PriceRepository,
                                                private val userSession: UserSession) : BaseViewModel()  {
    private val viewModelScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val _pproducts = MutableStateFlow<List<ProductPrice>>(emptyList())
    val pproducts: StateFlow<List<ProductPrice>> = _pproducts


    private val _title = Channel<String>(Channel.BUFFERED)
    val title = _title.receiveAsFlow()

    private var _type = MutableStateFlow(0)
    var type: Int
        get() = _type.value
        set(value) {
            _type.value = value
        }


    private var userId: Int = 0


    init {
        viewModelScope.launch {
            userId = userSession.getUserIdOnce()!!
            setTitle(type)
            fetchPproductsFromApi()
        }
    }

    suspend fun setTitle(type: Int){
        when(type) {
            0 -> _title.send("")
            1 -> _title.send("Spożywcze")
            2 -> _title.send("Dom i zdrowie")
            3 -> _title.send("Elektronika i AGD")
        }
    }


     fun fetchPproductsFromApi()  {
        viewModelScope.launch {
            repoProductPrice.getProductPriceByUser(userId,type)
                .collect { products ->
                    _pproducts.value = products
                }
        }
    }

    fun updatePrice(item: ProductPrice, newPrice: String) {
        repoPrice.updatePrice(item.priceId, newPrice.toDouble())
    }


    fun mockup(){
        val sampleProducts = listOf(
            ProductPrice(
                id = 1,
                priceId = 101,
                productId = 1001,
                typeId = 1,
                userId = 10,
                createdAt = "2026-02-12",
                price = 12.99,
                name = "Mleko"
            ),
            ProductPrice(
                id = 2,
                priceId = 102,
                productId = 1002,
                typeId = 1,
                userId = 10,
                createdAt = "2026-02-12",
                price = 5.49,
                name = "Chleb"
            ),
            ProductPrice(
                id = 3,
                priceId = 103,
                productId = 1003,
                typeId = 2,
                userId = 10,
                createdAt = "2026-02-12",
                price = 8.75,
                name = "Masło"
            )
        )
        _pproducts.value = sampleProducts
    }



}