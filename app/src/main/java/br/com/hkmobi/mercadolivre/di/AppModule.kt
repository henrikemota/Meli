package br.com.hkmobi.mercadolivre.di

import br.com.hkmobi.mercadolivre.repository.detailproduct.DetailProductRepository
import br.com.hkmobi.mercadolivre.repository.product.ProductRepository
import br.com.hkmobi.mercadolivre.viewmodel.detailproduct.DetailProductViewModel
import br.com.hkmobi.mercadolivre.viewmodel.product.ProductViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {

    viewModel { ProductViewModel(get()) }
    single { ProductRepository }

    viewModel { DetailProductViewModel(get()) }
    single { DetailProductRepository }
}