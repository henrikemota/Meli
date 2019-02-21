package br.com.hkmobi.mercadolivre.view.product

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.hkmobi.mercadolivre.R
import br.com.hkmobi.mercadolivre.data.model.Product
import br.com.hkmobi.mercadolivre.view.detailProduct.DetailProductActivity
import kotlinx.android.synthetic.main.item_product.view.*


class ProductAdapter(private val products: List<Product>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    @SuppressLint("StringFormatMatches")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.titleProduct.text = product.titleFormatted()
        if(product.containsImage()) holder.imageProduct.setImageURI(product.urlFormatted())
        holder.evaluation.rating = product.ratingAverage()
        holder.evaluationTotal.text = context.getString(R.string.msg_evaluation, product.reviewTotal())
        holder.productPrice.text = product.priceFormatted()

        if(product.containsInstallments()){
            holder.productInstallment.visibility = View.VISIBLE
            holder.productInstallment.text = context.getString(R.string.msg_installment, product.installments!!.quantity.toString(), product.priceAmountFormatted())
        }

        holder.itemView.setOnClickListener { DetailProductActivity
            .startActivity(context, product) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleProduct = itemView.titleProduct!!
        val imageProduct = itemView.imageProduct!!
        val evaluation = itemView.evaluation!!
        val evaluationTotal = itemView.evaluationTotal!!
        val productPrice = itemView.productPrice!!
        val productInstallment = itemView.productInstallment!!

    }
}