package br.com.hkmobi.mercadolivre.repository.detailproduct

import android.annotation.SuppressLint
import br.com.hkmobi.mercadolivre.data.model.Product
import br.com.hkmobi.mercadolivre.data.service.MeliInterface
import br.com.hkmobi.mercadolivre.data.service.ServiceGenerator
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers


object DetailProductRepository{

    @SuppressLint("CheckResult")
    fun getDetailsDescription(product: Product) : Single<Product>{
        return Single.zip(getProductDetails(product.id!!), getProductDescription(product.id),
            BiFunction<Product, Product, Product> { details, description ->
                teste(product, details, description)
            })
    }

    private fun teste(product: Product, details: Product, description: Product): Product {
        product.plain_text = description.plain_text
        product.pictures = details.pictures

        return product
    }

    private fun getProductDetails(id: String) : Single<Product> {
        return ServiceGenerator
            .createService(MeliInterface::class.java)
            .getProductDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getProductDescription(id: String): Single<Product> {
        return ServiceGenerator
            .createService(MeliInterface::class.java)
            .getProductDescription(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}