package br.com.hkmobi.mercadolivre.viewmodel.detailproduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.hkmobi.mercadolivre.data.model.Product
import br.com.hkmobi.mercadolivre.data.service.MeliInterface
import br.com.hkmobi.mercadolivre.data.service.ServiceGenerator
import br.com.hkmobi.mercadolivre.repository.detailproduct.DetailProductRepository
import io.reactivex.Observable
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class DetailProductViewModel(private val repo: DetailProductRepository) : ViewModel(){

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
        repo.getDetailsDescription(product).subscribe(object : SingleObserver<Product>{
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