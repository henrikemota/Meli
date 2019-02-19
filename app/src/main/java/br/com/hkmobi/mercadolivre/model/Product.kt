package br.com.hkmobi.mercadolivre.model

import java.io.Serializable
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

data class Product(
    val id: String?,
    val title: String?,
    var plain_text: String?,
    val thumbnail: String?,
    val reviews: Review?,
    val price: Double?,
    val currency_id: String?,
    val installments: Installment?,
    var pictures: ArrayList<Picture>?): Serializable{

    fun titleFormatted(): String{
        if(title.isNullOrEmpty()) return "-"
        return title
    }

    fun currencyFormatted(): String{
        if(currency_id.isNullOrEmpty()) return "USD"
        return currency_id
    }

    fun containsImage(): Boolean{
        return !thumbnail.isNullOrEmpty()
    }

    fun urlFormatted(): String{
        if (thumbnail.isNullOrEmpty()) return "-"
        return thumbnail.replace("http", "https")

    }

    fun ratingAverage(): Float{
        if (reviews?.rating_average == null) return 0F
        return reviews.rating_average
    }

    fun reviewTotal(): Int{
        if (reviews?.total == null) return 0
        return reviews.total
    }

    fun priceFormatted(): String{
        if(price != null) return monetaryFormatted(price)
        return monetaryFormatted(0.0)
    }

    fun containsInstallments(): Boolean{
        return installments != null
    }

    fun priceAmountFormatted(): String{
        if(installments?.amount == null) return monetaryFormatted(0.0)
        return monetaryFormatted(installments.amount)
    }

    private fun monetaryFormatted(price: Double): String{
        val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
        format.currency = Currency.getInstance(currencyFormatted())
        return format.format(price)
    }

    fun plainTextFormmated(): String{
        if(plain_text.isNullOrEmpty()) return "-"
        return plain_text.toString()
    }
}