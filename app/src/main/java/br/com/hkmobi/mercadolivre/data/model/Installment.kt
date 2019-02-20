package br.com.hkmobi.mercadolivre.data.model

import java.io.Serializable

data class Installment(val quantity: Int?,
                  val amount: Double?,
                  val currency_id: String?): Serializable