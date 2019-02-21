package br.com.hkmobi.mercadolivre.data.repository

import br.com.hkmobi.mercadolivre.data.model.Product
import br.com.hkmobi.mercadolivre.data.model.response.ProductResponse
import io.reactivex.Single


interface ProductRepository {

    fun getProducts(query: String): Single<ProductResponse>
    fun getDetails(product: Product): Single<Product>
}