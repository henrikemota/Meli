package br.com.hkmobi.mercadolivre.utils

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import org.koin.android.ext.android.startKoin


class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        startKoin(this@MyApplication, listOf(appModule))
    }
}