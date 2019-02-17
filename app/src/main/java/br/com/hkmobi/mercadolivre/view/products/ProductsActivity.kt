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

    var page = 0
    var query = ""
    private var loading = false
    private var lastVisibleItem = 0
    private var totalItemCount = 0
    private var totalItems = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

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
                if (!loading && totalItemCount <= lastVisibleItem + 1 && totalItemCount < totalItems) {
                    if(page != 0)getProducts()
                    loading = true
                }
            }
        })
    }

    @SuppressLint("StringFormatMatches")
    private fun setValues() {

        swipeRefreshLayout.setOnRefreshListener {
            page = 0
            getProducts()
        }

        animationSearch.setOnClickListener { searchView.isIconified = false }

        productViewModel.getProducts().observe(this, Observer { products ->
            this.products.addAll(products)
            loading = false
            page++
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

        productViewModel.countProducts().observe(this, Observer { total ->
            totalItems = total
            productTotal.text = getString(R.string.msg_product_total, totalItems)
            productTotal.visibility = View.VISIBLE
        })
    }

    private fun getProducts(){
        animationSearch.visibility = View.GONE
        productViewModel.getProducts(query, page)
    }

    @SuppressLint("PrivateResource")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        searchView = menu!!.findItem(R.id.action_search_list_activity).actionView as SearchView
        searchView.queryHint = getString(R.string.search_menu_title)

        productViewModel.getQuery().observe(this, Observer { query ->
            searchView.setQuery(query, false)
            if(!query.isNullOrEmpty()) searchView.isIconified = false
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                productViewModel.setQuery(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                products.clear()
                page = 0
                productAdapter.notifyDataSetChanged()
                this@ProductsActivity.query = query
                getProducts()
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }
}
