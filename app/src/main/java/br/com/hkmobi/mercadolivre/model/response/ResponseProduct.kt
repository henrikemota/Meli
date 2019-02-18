package br.com.hkmobi.mercadolivre.model.response

import br.com.hkmobi.mercadolivre.model.Paging
import br.com.hkmobi.mercadolivre.model.Product
import java.io.Serializable

class ResponseProduct(val paging: Paging,
                      val results: ArrayList<Product>): Serializable