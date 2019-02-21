package br.com.hkmobi.mercadolivre.view.description

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import br.com.hkmobi.mercadolivre.R
import kotlinx.android.synthetic.main.activity_description.*

class DescriptionActivity : AppCompatActivity() {

    companion object {
        const val DESCRIPTION = "br.com.hkmobi.mercadolivre.view.description.DescriptionActivity.DESCRIPTION"

        fun startActivity(context: Context, description: String){
            val intent = Intent(context, DescriptionActivity::class.java)
            intent.putExtra(DESCRIPTION, description)
            context.startActivity(intent)

        }
    }

    lateinit var description: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        description = intent.extras!!.getString(DESCRIPTION) as String
        descriptionProduct.text = description
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
