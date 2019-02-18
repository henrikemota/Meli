package br.com.hkmobi.mercadolivre.model

import java.io.Serializable

class Installment(val quantity: Int,
                  val amount: Double,
                  val currency_id: String): Serializable