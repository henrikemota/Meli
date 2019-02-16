package br.com.hkmobi.mercadolivre.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import br.com.hkmobi.mercadolivre.R
import br.com.hkmobi.mercadolivre.model.Product
import br.com.hkmobi.mercadolivre.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_detail_product.*


//https://api.mercadolibre.com/items/MLU460267000/description

class DetailProductActivity : BaseActivity() {

    lateinit var product: Product

    companion object {
        const val PRODUCT = "br.com.hkmobi.mercadolivre.view.DetailProductActivity.PRODUCT"
    }

    fun startActivity(context: Context, product: Product){
        val intent = Intent(context, DetailProductActivity::class.java)
        intent.putExtra(PRODUCT, product)
        context.startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)
        setSupportActionBar(toolbar)

        product = intent.extras!!.getSerializable(PRODUCT) as Product

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }


}
