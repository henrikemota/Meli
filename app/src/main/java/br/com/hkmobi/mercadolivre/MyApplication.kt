package br.com.hkmobi.mercadolivre

import android.app.Application
import br.com.hkmobi.mercadolivre.di.appModule
import com.facebook.drawee.backends.pipeline.Fresco
import com.squareup.leakcanary.LeakCanary
import org.koin.android.ext.android.startKoin


class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin(this@MyApplication, listOf(appModule))
        Fresco.initialize(this)

        when {
            LeakCanary.isInAnalyzerProcess(this) -> return
            else -> LeakCanary.install(this)
        }
    }
}