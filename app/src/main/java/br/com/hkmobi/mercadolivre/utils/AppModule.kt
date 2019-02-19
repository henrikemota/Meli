package br.com.hkmobi.mercadolivre.utils

import br.com.hkmobi.mercadolivre.viewmodel.DetailProductViewModel
import br.com.hkmobi.mercadolivre.viewmodel.ProductsViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {

    viewModel { ProductsViewModel() }
    viewModel { DetailProductViewModel() }
}