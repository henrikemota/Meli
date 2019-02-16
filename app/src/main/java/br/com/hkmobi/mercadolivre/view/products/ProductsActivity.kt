package br.com.hkmobi.mercadolivre.view.products

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import br.com.hkmobi.mercadolivre.R
import br.com.hkmobi.mercadolivre.utils.BaseActivity
import br.com.hkmobi.mercadolivre.viewmodel.ProductsViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.hkmobi.mercadolivre.model.Product
import br.com.hkmobi.mercadolivre.utils.ViewHelper
import kotlinx.android.synthetic.main.activity_products.*
import androidx.recyclerview.widget.DividerItemDecoration
import io.reactivex.processors.PublishProcessor


class ProductsActivity : BaseActivity() {

    lateinit var viewModel: ProductsViewModel

    lateinit var searchView: SearchView

    lateinit var products: ArrayList<Product>
    lateinit var productAdapter: ProductAdapter
    lateinit var layoutManager: LinearLayoutManager

    var page = 0
    var query = ""
    private var loading = false
    private var lastVisibleItem = 0
    private var totalItemCount = 0
    private var totalItens = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        viewModel = ViewModelProviders.of(this).get(ProductsViewModel::class.java)

        setAdapter()
        setValues()
    }

    private fun setAdapter(){
        products = ArrayList()
        layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        productAdapter = ProductAdapter(products)
        recyclerView.adapter = productAdapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, (recyclerView.layoutManager as LinearLayoutManager).orientation))
        setUpLoadMoreListener()
    }

    private fun setUpLoadMoreListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                totalItemCount = layoutManager.itemCount
                lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                if (!loading && totalItemCount <= lastVisibleItem + 1 && totalItemCount < totalItens) {
                    page++
                    viewModel.getProducts(query, page)
                    loading = true
                }
            }
        })
    }

    @SuppressLint("StringFormatMatches")
    private fun setValues() {
        viewModel.getProducts().observe(this, Observer { products ->
            this.products.addAll(products)
            loading = false
            productAdapter.notifyDataSetChanged()
        })

        viewModel.error().observe(this, Observer { error ->
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        })

        viewModel.statusProgress().observe(this, Observer { status ->
            progressBar.visibility = ViewHelper().show(status)
        })

        viewModel.countProducts().observe(this, Observer { total ->
            totalItens = total
            productTotal.text = getString(R.string.msg_product_total, totalItens)
        })
    }

    @SuppressLint("PrivateResource")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        searchView = menu!!.findItem(R.id.action_search_list_activity).actionView as SearchView
        searchView.queryHint = getString(R.string.search_menu_title)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                products.clear()
                productAdapter.notifyDataSetChanged()
                this@ProductsActivity.query = query
                viewModel.getProducts(query, page)
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }
}
