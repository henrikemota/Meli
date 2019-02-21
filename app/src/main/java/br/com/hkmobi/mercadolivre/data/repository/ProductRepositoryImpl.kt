package br.com.hkmobi.mercadolivre.data.repository

import br.com.hkmobi.mercadolivre.data.model.Product
import br.com.hkmobi.mercadolivre.data.model.response.ProductResponse
import br.com.hkmobi.mercadolivre.data.source.ProductRemoteDataSource
import io.reactivex.Single


class ProductRepositoryImpl(private val productRemoteDataSource: ProductRemoteDataSource): ProductRepository{

    override fun getProducts(query: String): Single<ProductResponse> {
        return productRemoteDataSource.getProducts(query)
    }

    override fun getDetails(product: Product): Single<Product> {
        return productRemoteDataSource.getDetails(product)
    }
}