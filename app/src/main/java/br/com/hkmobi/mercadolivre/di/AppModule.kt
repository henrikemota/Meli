package br.com.hkmobi.mercadolivre.di

import br.com.hkmobi.mercadolivre.viewmodel.detailproduct.DetailProductViewModel
import br.com.hkmobi.mercadolivre.viewmodel.product.ProductViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {

    viewModel { ProductViewModel() }
    viewModel { DetailProductViewModel() }
}