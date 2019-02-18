package br.com.hkmobi.mercadolivre.model

import java.io.Serializable
import java.text.NumberFormat
import java.util.*

data class Product(val id: String,
                   val title: String,
                   val thumbnail: String,
                   val reviews: Review,
                   val price: Double,
                   val currency_id: String,
                   val installments: Installment): Serializable{

    fun priceFormatted(price: Double): String{
        val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
        format.currency = Currency.getInstance(currency_id)
        return format.format(price)
    }
}