package br.com.hkmobi.mercadolivre.viewmodel.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import br.com.hkmobi.mercadolivre.data.model.Product
import androidx.lifecycle.LiveData
import br.com.hkmobi.mercadolivre.data.model.response.ProductResponse
import br.com.hkmobi.mercadolivre.data.service.MeliInterface
import br.com.hkmobi.mercadolivre.data.service.ServiceGenerator
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProductViewModel: ViewModel() {

    private val mutableLiveDataProducts = MutableLiveData<ArrayList<Product>>()
    private val mutableLiveDataProductsError = MutableLiveData<String>()
    private val mutableLiveDataProductsProgress = MutableLiveData<Boolean>()
    private val mutableLiveDataQuery = MutableLiveData<String>()

    fun getProducts(): LiveData<ArrayList<Product>> {
        return mutableLiveDataProducts
    }

    fun setProducts(products: ArrayList<Product>){
        mutableLiveDataProducts.postValue(products)
    }

    fun error(): LiveData<String> {
        return mutableLiveDataProductsError
    }

    fun statusProgress(): LiveData<Boolean> {
        return mutableLiveDataProductsProgress
    }

    fun getQuery(): LiveData<String> {
        return mutableLiveDataQuery
    }

    fun setQuery(query: String) {
        return mutableLiveDataQuery.postValue(query)
    }

    fun getProducts(query: String){
        ServiceGenerator
            .createService(MeliInterface::class.java)
            .getProducts(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ProductResponse> {
                override fun onSubscribe(d: Disposable) {
                    mutableLiveDataProductsProgress.postValue(true)
                }

                override fun onError(error: Throwable) {
                    mutableLiveDataProductsProgress.postValue(false)
                    mutableLiveDataProductsError.postValue(error.message)
                }

                override fun onSuccess(response: ProductResponse) {
                    mutableLiveDataProductsProgress.postValue(false)
                    setProducts(response.results)
                }
            })
    }
}