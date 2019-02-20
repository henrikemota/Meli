package br.com.hkmobi.mercadolivre.data.model.response

import br.com.hkmobi.mercadolivre.data.model.Paging
import br.com.hkmobi.mercadolivre.data.model.Product
import java.io.Serializable

class ProductResponse(val paging: Paging,
                      val results: ArrayList<Product>): Serializable