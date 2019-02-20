package br.com.hkmobi.mercadolivre.data.service

import br.com.hkmobi.mercadolivre.data.model.Product
import br.com.hkmobi.mercadolivre.data.model.response.ProductResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MeliInterface {

    @GET("sites/MLU/search")
    fun  getProducts(@Query("q") query: String): Single<ProductResponse>

    @GET("items/{id}/description")
    fun  getProductDescription(@Path("id") id: String): Observable<Product>

    @GET("items/{id}")
    fun  getProductDetails(@Path("id") id: String): Observable<Product>
}