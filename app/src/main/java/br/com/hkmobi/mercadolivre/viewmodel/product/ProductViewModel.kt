package br.com.hkmobi.mercadolivre.viewmodel.product

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import br.com.hkmobi.mercadolivre.data.model.Product
import androidx.lifecycle.LiveData
import br.com.hkmobi.mercadolivre.data.model.response.ProductResponse
import br.com.hkmobi.mercadolivre.data.repository.ProductRepositoryImpl
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class ProductViewModel(private val repo : ProductRepositoryImpl): ViewModel() {

    private val mutableLiveDataProducts = MutableLiveData<ArrayList<Product>>()
    private val mutableLiveDataProductsError = MutableLiveData<String>()
    private val mutableLiveDataProductsProgress = MutableLiveData<Int>()
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

    fun statusProgress(): LiveData<Int> {
        return mutableLiveDataProductsProgress
    }

    fun getQuery(): LiveData<String> {
        return mutableLiveDataQuery
    }

    fun setQuery(query: String) {
        return mutableLiveDataQuery.postValue(query)
    }

    fun products(query: String){
        repo.getProducts(query).subscribe(object : SingleObserver<ProductResponse> {
            override fun onSubscribe(d: Disposable) {
                mutableLiveDataProductsProgress.postValue(View.VISIBLE)
            }

            override fun onSuccess(response: ProductResponse) {
                mutableLiveDataProductsProgress.postValue(View.GONE)
                setProducts(response.results)
            }

            override fun onError(error: Throwable) {
                mutableLiveDataProductsProgress.postValue(View.GONE)
                mutableLiveDataProductsError.postValue(error.message)
            }
        })
    }
}