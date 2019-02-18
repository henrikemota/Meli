package br.com.hkmobi.mercadolivre.view.products

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import br.com.hkmobi.mercadolivre.R
import br.com.hkmobi.mercadolivre.utils.BaseActivity
import br.com.hkmobi.mercadolivre.viewmodel.ProductsViewModel
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.hkmobi.mercadolivre.model.Product
import br.com.hkmobi.mercadolivre.utils.ViewHelper
import kotlinx.android.synthetic.main.activity_products.*
import androidx.recyclerview.widget.DividerItemDecoration
import org.koin.android.viewmodel.ext.android.viewModel

class ProductsActivity : BaseActivity() {

    val productViewModel: ProductsViewModel by viewModel()

    lateinit var searchView: SearchView

    lateinit var products: ArrayList<Product>
    lateinit var productAdapter: ProductAdapter
    lateinit var layoutManager: LinearLayoutManager

    var query = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        setAdapter()
        setValues()
        setListener()
    }

    private fun setAdapter(){
        products = ArrayList()
        layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        productAdapter = ProductAdapter(products)
        recyclerView.adapter = productAdapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, (recyclerView.layoutManager as LinearLayoutManager).orientation))
    }

    private fun setListener(){
        swipeRefreshLayout.setOnRefreshListener {
            getProducts()
        }

        animationSearch.setOnClickListener { searchView.isIconified = false }
    }

    @SuppressLint("StringFormatMatches")
    private fun setValues() {
        productViewModel.getProducts().observe(this, Observer { products ->
            this.products.clear()
            this.products.addAll(products)
            productAdapter.notifyDataSetChanged()
            animationSearch.cancelAnimation()
            animationSearch.visibility = View.GONE
            animationError.visibility = View.GONE
            swipeRefreshLayout.isRefreshing = false
        })

        productViewModel.error().observe(this, Observer {
            animationError.visibility = View.VISIBLE
            animationSearch.visibility = View.GONE
            swipeRefreshLayout.isRefreshing = false
        })

        productViewModel.statusProgress().observe(this, Observer { status ->
            progressBar.visibility = ViewHelper().show(status)
        })
    }

    private fun getProducts(){
        animationSearch.visibility = View.GONE
        productViewModel.getProducts(query)
    }

    @SuppressLint("PrivateResource")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        searchView = menu!!.findItem(R.id.action_search_list_activity).actionView as SearchView
        searchView.queryHint = getString(R.string.search_menu_title)

        productViewModel.getQuery().observe(this, Observer { query ->
            this.query = query
            searchView.setQuery(query, false)
            if(!query.isNullOrEmpty()) searchView.isIconified = false
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                productViewModel.setQuery(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                this@ProductsActivity.query = query
                getProducts()
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }
}
