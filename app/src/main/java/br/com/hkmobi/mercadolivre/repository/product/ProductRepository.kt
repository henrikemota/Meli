package br.com.hkmobi.mercadolivre.repository.product

import br.com.hkmobi.mercadolivre.data.model.response.ProductResponse
import br.com.hkmobi.mercadolivre.data.service.MeliInterface
import br.com.hkmobi.mercadolivre.data.service.ServiceGenerator
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


object ProductRepository{

    fun getProductsService(query: String): Single<ProductResponse> {
        return ServiceGenerator
            .createService(MeliInterface::class.java)
            .getProducts(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}