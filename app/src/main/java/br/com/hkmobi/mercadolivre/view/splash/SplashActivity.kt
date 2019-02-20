package br.com.hkmobi.mercadolivre.view.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import br.com.hkmobi.mercadolivre.R
import br.com.hkmobi.mercadolivre.view.product.ProductsActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            ProductsActivity().startActivity(this)
            finish()
        }, 2500)
    }
}
