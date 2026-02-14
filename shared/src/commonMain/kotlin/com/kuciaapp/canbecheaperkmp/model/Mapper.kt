package com.kuciaapp.canbecheaperkmp.model

import com.kuciaapp.canbecheaperkmp.model.domain.Type
import com.kuciaapp.canbecheaperkmp.model.domain.Product
import com.kuciaapp.canbecheaperkmp.model.domain.Price
import com.kuciaapp.canbecheaperkmp.model.domain.ProductPrice
import com.kuciaapp.canbecheaperkmp.model.dto.response.ExtendProductPriceResponse
import com.kuciaapp.canbecheaperkmp.model.dto.response.ProductResponse
import com.kuciaapp.canbecheaperkmp.model.dto.response.PriceResponse
import com.kuciaapp.canbecheaperkmp.model.dto.response.ProductPriceResponse
import com.kuciaapp.canbecheaperkmp.model.dto.response.TypeResponse
import kotlin.jvm.JvmName


fun TypeResponse.toDomain(): Type {
            return Type(
                id = this.id,
                name = this.name,
                createdAt = this.createdAt,
                userId = this.userId
            )
        }

        fun ProductResponse.toDomain(): Product {
            return Product(
                id = this.id,
                name = this.name
            )
        }

        fun PriceResponse.toDomain(): Price {
            return Price(
                id = this.id,
                price = this.price
            )
        }

        fun ProductPriceResponse.toDomain(): ProductPrice {
            return ProductPrice(
                id = this.id,
                productId = this.productId,
                priceId = this.priceId,
                typeId = this.typeId,
                userId = this.userId,
                createdAt = this.createdAt,
                name = null,
                price = null
            )
        }

        fun ExtendProductPriceResponse.toDomain(): ProductPrice {
            return ProductPrice(
                id = this.id,
                productId = this.productId,
                priceId = this.priceId,
                typeId = this.typeId,
                userId = this.userId,
                createdAt = this.createdAt,
                name = this.name,
                price = this.price
            )
        }

        // Dla list
        @JvmName("typeListToDomain")
        fun List<TypeResponse>.toDomain(): List<Type> {
            return this.map { it.toDomain() }
        }

        @JvmName("productListToDomain")
        fun List<ProductResponse>.toDomain(): List<Product> {
            return this.map { it.toDomain() }
        }


        @JvmName("priceListToDomain")
        fun List<PriceResponse>.toDomain(): List<Price> {
            return this.map { it.toDomain() }
        }

        @JvmName("productPriceListToDomain")
        fun List<ProductPriceResponse>.toDomain(): List<ProductPrice> {
            return this.map { it.toDomain() }
        }


        @JvmName("extendProductPriceListToDomain")
        fun List<ExtendProductPriceResponse>.toDomain(): List<ProductPrice> {
            return this.map { it.toDomain() }
        }
