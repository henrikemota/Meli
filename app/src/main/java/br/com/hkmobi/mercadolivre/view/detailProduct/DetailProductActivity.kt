package br.com.hkmobi.mercadolivre.view.detailProduct


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import br.com.hkmobi.mercadolivre.R
import br.com.hkmobi.mercadolivre.model.Product
import br.com.hkmobi.mercadolivre.viewmodel.DetailProductViewModel
import kotlinx.android.synthetic.main.activity_detail_product.*
import kotlinx.android.synthetic.main.content_detail_product.*
import org.koin.android.viewmodel.ext.android.viewModel


class DetailProductActivity : AppCompatActivity() {

    private val detailProductViewModel: DetailProductViewModel by viewModel()

    lateinit var product: Product

    companion object {
        const val PRODUCT = "br.com.hkmobi.mercadolivre.view.detailProduct.DetailProductActivity.PRODUCT"
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
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        product = intent.extras!!.getSerializable(PRODUCT) as Product

        detailProductViewModel.zipDetailsDescription(product)

        setValues()
        setListeners()
    }

    private fun setValues(){
        detailProductViewModel.getProduct().observe(this, Observer { product ->
            titleProduct.text = product.titleFormatted()
            priceProduct.text = product.priceFormatted()
            plainText.text = product.plainTextFormmated()
        })
    }

    private fun setListeners(){
        fab.setOnClickListener {
            snackBar(it)
        }

        buttonBuy.setOnClickListener {
            snackBar(it)
        }
    }

    private fun snackBar(view: View){
        Snackbar.make(view, "O item foi adicionado no carrinho", Snackbar.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail_product, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
