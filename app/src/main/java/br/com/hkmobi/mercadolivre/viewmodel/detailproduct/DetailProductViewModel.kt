package br.com.hkmobi.mercadolivre.viewmodel.detailproduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.hkmobi.mercadolivre.data.model.Product
import br.com.hkmobi.mercadolivre.data.repository.ProductRepositoryImpl
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class DetailProductViewModel(private val repo: ProductRepositoryImpl) : ViewModel(){

    private val mutableLiveProduct = MutableLiveData<Product>()
    private val mutableLiveError = MutableLiveData<String>()

    fun getProduct(): LiveData<Product> {
        return mutableLiveProduct
    }

    fun error(): LiveData<String> {
        return mutableLiveError
    }

    private fun setProduct(product: Product){
        mutableLiveProduct.postValue(product)
    }

    fun zipDetailsDescription(product: Product){
        repo.getDetails(product).subscribe(object : SingleObserver<Product>{
            override fun onSuccess(product: Product) {
                setProduct(product)
            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {
                mutableLiveError.value = e.message
            }
        })
    }


}