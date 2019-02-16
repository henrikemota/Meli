package br.com.hkmobi.mercadolivre.utils

import br.com.hkmobi.mercadolivre.model.Product
import br.com.hkmobi.mercadolivre.model.response.ResponseProduct
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MeliInterface {

    @GET("search")
    fun  getProducts(@Query("q") query: String, @Query("offset") page: Int, @Query("limit") limit: Int): Single<ResponseProduct>
}