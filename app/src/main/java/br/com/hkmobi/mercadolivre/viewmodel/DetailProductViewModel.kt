package br.com.hkmobi.mercadolivre.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.hkmobi.mercadolivre.model.Product
import br.com.hkmobi.mercadolivre.utils.MeliInterface
import br.com.hkmobi.mercadolivre.utils.ServiceGenerator
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class DetailProductViewModel : ViewModel(){

    private val mutableLiveProduct = MutableLiveData<Product>()

    fun getProduct(): LiveData<Product> {
        return mutableLiveProduct
    }

    private fun setProduct(product: Product){
        mutableLiveProduct.postValue(product)
    }

    fun zipDetailsDescription(product: Product){
        Observable.zip(getProductDetails(product.id!!), getProductDescription(product.id),
            BiFunction<Product, Product, Any> { details, description ->
                product.plain_text = description.plain_text
                product.pictures = details.pictures
                setProduct(product)
            }).subscribe()
    }

    private fun getProductDetails(id: String) : Observable<Product> {
        return ServiceGenerator
            .createService(MeliInterface::class.java)
            .getProductDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getProductDescription(id: String): Observable<Product> {
        return ServiceGenerator
            .createService(MeliInterface::class.java)
            .getProductDescription(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}